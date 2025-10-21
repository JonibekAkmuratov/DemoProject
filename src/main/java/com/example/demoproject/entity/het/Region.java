package com.example.demoproject.entity.het;


import com.example.demoproject.entity.BaseDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "REGION")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Region implements BaseDomain {

    @Id
    @Column(name = "ID", length = 16, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ORD")
    private BigDecimal ord;


}
