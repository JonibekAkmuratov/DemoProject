package com.example.demoproject.criteria.het;

import com.example.demoproject.criteria.GenericCriteria;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class TransformerCriteria extends GenericCriteria {

    private LocalDate collectionDate;

    private BigDecimal level1;

    private Long regionId;

    private Long districtId;


    @Override
    public boolean isIgnoreDeletion() {
        return Boolean.TRUE;
    }
}
