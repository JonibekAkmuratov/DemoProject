package com.example.demoproject.entity.main;

import com.example.demoproject.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_CATEGORY_ID_SEQ")
    @SequenceGenerator(name = "_CATEGORY_ID_SEQ", sequenceName = "CATEGORY_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

}
