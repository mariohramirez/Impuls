package com.impuls.business_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "entrepreneurship")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entrepreneurship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entrepreneurshipNumber;
    private Long ownerId;

    private String name;
    private String email;
    private String phone;
    private String nit;
    private String logoUrl;
    private String companySize;
    private String companyName;
    private String websiteUrl;
    private String shortDescription;
    private String bannerUrl;
    private String description;

    private Boolean isVerified;
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "legal_form_id")
    private LegalForm legalForm;

    private LocalDateTime registerDate;
    private LocalDateTime startDate;
    private LocalDateTime incorporationDate;
    private Boolean formalized;

    @OneToMany(mappedBy = "entrepreneurship", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "entrepreneurship", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SocialNetwork> socialNetworks;

    @OneToMany(mappedBy = "entrepreneurship", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntrepreneurshipCategories> categories = new HashSet<>();

    @OneToMany(mappedBy = "entrepreneurship", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiceEntrepreneurship> services = new HashSet<>();
}