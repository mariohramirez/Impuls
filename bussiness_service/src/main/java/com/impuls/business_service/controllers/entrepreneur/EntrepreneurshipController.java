package com.impuls.business_service.controllers.entrepreneur;

import com.impuls.business_service.model.Entrepreneurship;
import com.impuls.business_service.services.interfaces.EntrepreneurshipService;
import com.impuls.business_service.services.request.EntrepreneurshipRequest;
import com.impuls.business_service.services.response.EntrepreneurshipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.impuls.business_service.controllers.ApiPaths.PUBLIC_PATH;

@RestController
@RequestMapping(PUBLIC_PATH + "entrepreneurship")
public class EntrepreneurshipController {

    @Autowired
    EntrepreneurshipService entrepreneurshipService;

    @PostMapping("/register")
    public ResponseEntity<Entrepreneurship> createEntrepreneurshipHandler(
            @RequestBody EntrepreneurshipRequest request,
            @RequestHeader("Authorization") String token) throws Exception{
        Entrepreneurship entrepreneurship = entrepreneurshipService.createEntrepreneurship(request, token);
        return new ResponseEntity<>(entrepreneurship, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EntrepreneurshipResponse>> getAllEntrepreneurships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<EntrepreneurshipResponse> response = entrepreneurshipService.getAllEntrepreneurship(pageable);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/by-category")
    public ResponseEntity<Page<EntrepreneurshipResponse>> getByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection));
        return ResponseEntity.ok(
                entrepreneurshipService.getByCategory(category, pageable)
        );
    }

    @GetMapping("/by-country")
    public ResponseEntity<Page<EntrepreneurshipResponse>> getByCountry(
            @RequestParam String country,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection));
        return ResponseEntity.ok(
                entrepreneurshipService.getByCountry(country, pageable)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EntrepreneurshipResponse>> searchEntrepreneurships(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EntrepreneurshipResponse> response = entrepreneurshipService.search(query, pageable);
        return ResponseEntity.ok(response);
    }

}
