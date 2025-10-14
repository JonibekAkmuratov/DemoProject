package com.example.demoproject.entity.main;

import com.example.demoproject.entity.Auditable;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_PRODUCT_ID_SEQ")
    @SequenceGenerator(name = "_PRODUCT_ID_SEQ", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private Category category;

}
