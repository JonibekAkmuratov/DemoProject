package com.example.demoproject.entity.het;


import com.example.demoproject.entity.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "DISTRICT")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class District implements BaseDomain {

    @Id
    @Column(name = "ID", length = 16, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Region region;

    @Column(name = "name")
    private String name;

    @Column(name = "ORD")
    private BigDecimal ord;


}
