package com.example.demoproject.controller.het;

import com.example.demoproject.annotation.RateLimit;
import com.example.demoproject.controller.AbstractController;
import com.example.demoproject.criteria.het.TransformerCriteria;
import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.het.TransformerRegionsDTO;
import com.example.demoproject.dto.main.category.CategoryCreateDTO;
import com.example.demoproject.dto.main.category.CategoryDTO;
import com.example.demoproject.dto.main.category.CategoryUpdateDTO;
import com.example.demoproject.entity.het.Transformer;
import com.example.demoproject.service.het.TransformerService;
import com.example.demoproject.service.main.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demoproject.controller.AbstractController.PATH_DEMO;

@RestController
@RequestMapping(PATH_DEMO+"/transformer")
public class TransformerController extends AbstractController<TransformerService> {


    protected TransformerController(TransformerService service) {
        super(service);
    }

    @GetMapping("/regions")
    ResponseEntity<Data<List<TransformerRegionsDTO>>> getRegions(@Valid TransformerCriteria criteria) {
        Data<List<TransformerRegionsDTO>> regions = service.getRegions(criteria);
        return ResponseEntity.ok(regions);
    }



}