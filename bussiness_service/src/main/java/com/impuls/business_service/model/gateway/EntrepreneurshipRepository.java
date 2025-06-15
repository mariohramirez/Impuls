package com.impuls.business_service.model.gateway;

import com.impuls.business_service.model.Entrepreneurship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship, Long> {

        // Consulta para filtrar por categoría
        @Query(value = """
        SELECT e.* FROM entrepreneurship e
        INNER JOIN entrepreneurship_category ec ON e.id = ec.entrepreneurship_id
        INNER JOIN category c ON ec.category_id = c.id
        WHERE c.name = :categoryName
        """,
                countQuery = """
        SELECT COUNT(e.id) FROM entrepreneurship e
        INNER JOIN entrepreneurship_category ec ON e.id = ec.entrepreneurship_id
        INNER JOIN category c ON ec.category_id = c.id
        WHERE c.name = :categoryName
        """,
                nativeQuery = true)
        Page<Entrepreneurship> findByCategoryName(
                @Param("categoryName") String categoryName,
                Pageable pageable);

        // Consulta para filtrar por país
        @Query(value = """
        SELECT DISTINCT e.* FROM entrepreneurship e
        INNER JOIN address a ON e.id = a.entrepreneurship_id
        INNER JOIN city ci ON a.city_id = ci.id
        INNER JOIN country co ON ci.country_id = co.id
        WHERE co.name = :countryName
        """,
                countQuery = """
        SELECT COUNT(DISTINCT e.id) FROM entrepreneurship e
        INNER JOIN address a ON e.id = a.entrepreneurship_id
        INNER JOIN city ci ON a.city_id = ci.id
        INNER JOIN country co ON ci.country_id = co.id
        WHERE co.name = :countryName
        """,
                nativeQuery = true)
        Page<Entrepreneurship> findByCountryName(
                @Param("countryName") String countryName,
                Pageable pageable);

        // Custom query method for search
        @Query("SELECT e FROM Entrepreneurship e WHERE " +
                "LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                "LOWER(e.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                "LOWER(e.shortDescription) LIKE LOWER(CONCAT('%', :query, '%'))")
        Page<Entrepreneurship> search(@Param("query") String query, Pageable pageable);

}
