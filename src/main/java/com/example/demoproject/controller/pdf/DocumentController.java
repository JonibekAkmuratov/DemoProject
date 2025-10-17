package com.example.demoproject.controller.pdf;

import com.example.demoproject.dto.pdf.DocumentRequest;
import com.example.demoproject.service.pdf.DocumentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Controller
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentProcessingService documentProcessingService;

    @GetMapping("/convert")
    public ResponseEntity<byte[]> convertWordToPdf() {

        try {
            DocumentRequest request = new DocumentRequest();
            byte[] pdfBytes = documentProcessingService.processWordToPdf(request);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "document.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
