package com.impuls.business_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "legal_form")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private String description;

    @OneToMany(mappedBy = "legalForm", fetch = FetchType.LAZY)
    private Set<Entrepreneurship> entrepreneurshipSet;
}