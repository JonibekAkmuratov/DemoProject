package com.example.demoproject.dto.het;

import com.example.demoproject.entity.het.District;
import com.example.demoproject.entity.het.Region;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransformerRegionsDTO {

    private String pkey;

    private BigDecimal electricSolarCustomerTp;

    private LocalDate createdAtDate;

    private LocalDate collectionDate;

    private BigDecimal level1;

    private Long regionId;
    private String regionName;

    private Long districtId;
    private String districtName;

    private String owner;

    private Long countTransformer;

    private Long countFullPassport;

    private Long countEspTransformers;

    private Long countEspMeters;

    private Long countEspSentMeters;

    private BigDecimal electricEspTp;

    private Long countCustomerTransformers;

    private Long countCustomerMeters;

    private Long countCustomerSentMeters;

    private BigDecimal electricCustomerTp;

    private BigDecimal electricAllTp;

    private Long countConsumers;

    private Long countConsumerMeters;

    private Long countConsumerSentMeters;

    private BigDecimal electricConsumers;

    private Long countLossMinus;

    private BigDecimal electricLossMinus;

    private Long countLossPlus;

    private BigDecimal electricLossPlus;

}
