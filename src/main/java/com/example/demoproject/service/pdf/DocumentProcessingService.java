package com.example.demoproject.service.pdf;

import com.example.demoproject.dto.pdf.DocumentRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentProcessingService {

    private final ResourceLoader resourceLoader;

    public byte[] processWordToPdf(DocumentRequest request) throws Exception {
        // 1. Word faylini o'qish
        Resource resource = resourceLoader.getResource("classpath:pdf/applicationTemp.docx");
        InputStream fis = resource.getInputStream();

        XWPFDocument document = new XWPFDocument(fis);

        // 2. Keylarni replace qilish
        replaceTextInDocument(document, request.getReplacements());

        // 3. QR kod generatsiya qilish
        byte[] qrCodeImage = generateQRCode(request.getQrCodeText());

        // 4. QR kodni Word dokumentga qo'shish
        addQRCodeToDocument(document, qrCodeImage);

        // 5. Word faylini PDF ga aylantirish
        byte[] pdfBytes = convertWordToPdf(document);

        document.close();
        fis.close();

        return pdfBytes;
    }

    private void replaceTextInDocument(XWPFDocument document, Map<String, String> replacements) {
        // Paragraphlardagi textni replace qilish
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                replaceTextInParagraph(paragraph, entry.getKey(), entry.getValue());
            }
        }

        // Tablalardagi textni replace qilish
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        for (Map.Entry<String, String> entry : replacements.entrySet()) {
                            replaceTextInParagraph(paragraph, entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        }
    }

    private void replaceTextInParagraph(XWPFParagraph paragraph, String find, String replace) {
        List<XWPFRun> runs = paragraph.getRuns();

        if (runs.isEmpty()) return;

        // Barcha runlarni bitta stringga yig'ish
        StringBuilder fullText = new StringBuilder();
        for (XWPFRun run : runs) {
            fullText.append(run.getText(0) != null ? run.getText(0) : "");
        }

        String text = fullText.toString();

        // Agar find topilsa, barcha runlarni o'chirish va yangi text qo'shish
        if (text.contains(find)) {
            String newText = text.replace(find, replace);



            // Yangi run qo'shish
            if (!newText.isEmpty()) {
                XWPFRun newRun = paragraph.createRun();
                newRun.setText(newText);
            }
        }
    }

    private byte[] generateQRCode(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    private void addQRCodeToDocument(XWPFDocument document, byte[] qrCodeImage) throws Exception {
        XWPFParagraph qrParagraph = document.createParagraph();
        qrParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = qrParagraph.createRun();
        run.addPicture(
                new ByteArrayInputStream(qrCodeImage),
                Document.PICTURE_TYPE_PNG,
                "qrcode.png",
                Units.toEMU(100),
                Units.toEMU(100)
        );
    }

    private byte[] convertWordToPdf(XWPFDocument document) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Word faylni temp faylga saqlab olish
        File tempWordFile = File.createTempFile("temp", ".docx");
        try (FileOutputStream fos = new FileOutputStream(tempWordFile)) {
            document.write(fos);
        }

        // LibreOffice orqali PDF ga aylantirish (server-side conversion)
        // Agar LibreOffice o'rnatilgan bo'lsa
        ProcessBuilder pb = new ProcessBuilder(
                "libreoffice",
                "--headless",
                "--convert-to", "pdf",
                "--outdir", tempWordFile.getParent(),
                tempWordFile.getAbsolutePath()
        );

        try {
            int exitCode = pb.start().waitFor();

            if (exitCode == 0) {
                File pdfFile = new File(tempWordFile.getParent(),
                        tempWordFile.getName().replace(".docx", ".pdf"));

                try (FileInputStream fis = new FileInputStream(pdfFile)) {
                    byte[] pdfBytes = fis.readAllBytes();
                    pdfFile.delete();
                    tempWordFile.delete();
                    return pdfBytes;
                }
            }
        } catch (Exception e) {
            System.out.println("LibreOffice topilmadi, soddiq conversion ishlatilmoqda");
        }

        // Agar LibreOffice bo'lmasa - iText orqali soddiq conversion
        return convertWordToPdfWithIText(document);
    }

    private byte[] convertWordToPdfWithIText(XWPFDocument document) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        com.itextpdf.layout.Document doc = new com.itextpdf.layout.Document(pdfDoc);

        // Word paragraphlarini PDF ga qo'shish (rasmlar bilan)
        for (XWPFParagraph para : document.getParagraphs()) {
            // Rasmlarni qo'shish
            for (XWPFRun run : para.getRuns()) {
                for (XWPFPicture picture : run.getEmbeddedPictures()) {
                    try {
                        byte[] imageBytes = picture.getPictureData().getData();
                        com.itextpdf.io.image.ImageData imageData =
                                com.itextpdf.io.image.ImageDataFactory.create(imageBytes);
                        com.itextpdf.layout.element.Image image =
                                new com.itextpdf.layout.element.Image(imageData);
                        image.setMaxWidth(400);
                        doc.add(image);
                    } catch (Exception e) {
                        System.err.println("Rasm qo'shishda xato: " + e.getMessage());
                    }
                }
            }

            // Textni qo'shish
            String text = para.getText();
            if (!text.isEmpty()) {
                com.itextpdf.layout.element.Paragraph paragraph =
                        new com.itextpdf.layout.element.Paragraph(text);
                doc.add(paragraph);
            }
        }

        // Word tablalarini PDF ga qo'shish (rasm va text bilan)
        for (XWPFTable table : document.getTables()) {
            if (table.getRows().isEmpty()) continue;

            int colCount = table.getRows().get(0).getTableCells().size();
            com.itextpdf.layout.element.Table pdfTable =
                    new com.itextpdf.layout.element.Table(UnitValue.createPercentArray(colCount));

            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    com.itextpdf.layout.element.Cell pdfCell = new com.itextpdf.layout.element.Cell();

                    // Cell ichidagi rasmlarni qo'shish
                    for (XWPFParagraph cellPara : cell.getParagraphs()) {
                        for (XWPFRun run : cellPara.getRuns()) {
                            for (XWPFPicture picture : run.getEmbeddedPictures()) {
                                try {
                                    byte[] imageBytes = picture.getPictureData().getData();
                                    com.itextpdf.io.image.ImageData imageData =
                                            com.itextpdf.io.image.ImageDataFactory.create(imageBytes);
                                    com.itextpdf.layout.element.Image image =
                                            new com.itextpdf.layout.element.Image(imageData);
                                    image.setMaxWidth(100);
                                    pdfCell.add(image);
                                } catch (Exception e) {
                                    System.err.println("Tabledagi rasm qo'shishda xato: " + e.getMessage());
                                }
                            }
                        }
                    }

                    // Cell ichidagi textni qo'shish
                    String cellText = cell.getText();
                    if (!cellText.isEmpty()) {
                        pdfCell.add(new com.itextpdf.layout.element.Paragraph(cellText));
                    }

                    pdfTable.addCell(pdfCell);
                }
            }

            doc.add(pdfTable);
        }

        doc.close();
        return outputStream.toByteArray();
    }


}
