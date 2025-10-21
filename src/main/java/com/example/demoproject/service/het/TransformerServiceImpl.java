package com.example.demoproject.service.het;

import com.example.demoproject.criteria.het.TransformerCriteria;
import com.example.demoproject.dto.Data;
import com.example.demoproject.dto.het.TransformerRegionsDTO;
import com.example.demoproject.entity.het.Transformer;
import com.example.demoproject.mapper.het.TransformerMapper;
import com.example.demoproject.repository.het.transformer.TransformerRepository;
import com.example.demoproject.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TransformerServiceImpl extends AbstractService<TransformerRepository, TransformerMapper> implements TransformerService {

    protected TransformerServiceImpl(TransformerRepository repository, TransformerMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Data<List<TransformerRegionsDTO>> getRegions(TransformerCriteria criteria) {

        List<Transformer> transformerList = repository.findAll(criteria);
        Long totalCount = repository.getTotalCount(criteria);

        List<TransformerRegionsDTO> result = mapper.toRegionDTO(transformerList);

        return new Data<>(result, totalCount);
    }
}
