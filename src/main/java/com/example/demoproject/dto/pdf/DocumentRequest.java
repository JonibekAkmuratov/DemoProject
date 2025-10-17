package com.example.demoproject.dto.pdf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest {
    private Map<String, String> replacements=new HashMap<>(); // "{key}" -> "value"
    private String qrCodeText="I am Jonibek";
}
