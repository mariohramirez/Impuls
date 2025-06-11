package com.impuls.business_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepreneurshipCategories {

    @EmbeddedId
    private EntrepreneurshipCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("entrepreneurshipId")
    @JoinColumn(name = "entrepreneurship_id")
    private Entrepreneurship entrepreneurship;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;
}
