package com.example.demoproject.service.het;

import com.example.demoproject.criteria.het.TransformerCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.het.TransformerRegionsDTO;
import com.example.demoproject.service.GenericService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TransformerService extends GenericService {

    Data<List<TransformerRegionsDTO>> getRegions(@Valid TransformerCriteria criteria);

}
