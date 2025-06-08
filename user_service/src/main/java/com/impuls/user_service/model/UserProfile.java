package com.impuls.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private Gender gender ;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType ;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "social_network_id")
    private SocialNetwork socialNetwork ;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

}
