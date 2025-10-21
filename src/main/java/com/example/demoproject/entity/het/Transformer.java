package com.example.demoproject.entity.het;


import com.example.demoproject.entity.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSFORMER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transformer implements BaseDomain {

    @Id
    @Column(name = "PKEY", length = 16, nullable = false)
    private String pkey;

    @Column(name = "ELECTRIC_SOLAR_CUSTOMER_TP", precision = 19, scale = 2)
    private BigDecimal electricSolarCustomerTp;

    @Column(name = "CREATED_AT")
    private LocalDate createdAtDate;

    @Column(name = "COLLECTION_DATE")
    private LocalDate collectionDate;

    @Column(name = "LEVEL1", precision = 19, scale = 2)
    private BigDecimal level1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @Column(name = "OWNER", length = 32)
    private String owner;

    // Transformer Counts
    @Column(name = "COUNT_TRANSFORMER", precision = 19, scale = 0)
    private Long countTransformer;

    @Column(name = "COUNT_FULL_PASSPORT", precision = 19, scale = 0)
    private Long countFullPassport;

    @Column(name = "COUNT_ESP_TRANSFORMERS", precision = 19, scale = 0)
    private Long countEspTransformers;

    @Column(name = "COUNT_ESP_METERS", precision = 19, scale = 0)
    private Long countEspMeters;

    @Column(name = "COUNT_ESP_SENT_METERS", precision = 19, scale = 0)
    private Long countEspSentMeters;

    // Electric ESP
    @Column(name = "ELECTRIC_ESP_TP", precision = 19, scale = 2)
    private BigDecimal electricEspTp;

    // Customer related
    @Column(name = "COUNT_CUSTOMER_TRANSFORMERS", precision = 19, scale = 0)
    private Long countCustomerTransformers;

    @Column(name = "COUNT_CUSTOMER_METERS", precision = 19, scale = 0)
    private Long countCustomerMeters;

    @Column(name = "COUNT_CUSTOMER_SENT_METERS", precision = 19, scale = 0)
    private Long countCustomerSentMeters;

    @Column(name = "ELECTRIC_CUSTOMER_TP", precision = 19, scale = 2)
    private BigDecimal electricCustomerTp;

    @Column(name = "ELECTRIC_ALL_TP", precision = 19, scale = 2)
    private BigDecimal electricAllTp;

    // Consumer related
    @Column(name = "COUNT_CONSUMERS", precision = 19, scale = 0)
    private Long countConsumers;

    @Column(name = "COUNT_CONSUMER_METERS", precision = 19, scale = 0)
    private Long countConsumerMeters;

    @Column(name = "COUNT_CONSUMER_SENT_METERS", precision = 19, scale = 0)
    private Long countConsumerSentMeters;

    @Column(name = "ELECTRIC_CONSUMERS", precision = 19, scale = 2)
    private BigDecimal electricConsumers;

    // Loss related
    @Column(name = "COUNT_LOSS_MINUS", precision = 19, scale = 0)
    private Long countLossMinus;

    @Column(name = "ELECTRIC_LOSS_MINUS", precision = 19, scale = 2)
    private BigDecimal electricLossMinus;

    @Column(name = "COUNT_LOSS_PLUS", precision = 19, scale = 0)
    private Long countLossPlus;

    @Column(name = "ELECTRIC_LOSS_PLUS", precision = 19, scale = 2)
    private BigDecimal electricLossPlus;

    @Column(name = "DELETED")
    private Boolean deleted;
}
