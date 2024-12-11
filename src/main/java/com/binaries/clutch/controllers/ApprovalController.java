package com.binaries.clutch.controllers;

import com.binaries.clutch.model.CarSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApprovalController {

    @GetMapping("/specifications")
    @PreAuthorize("hasRole('DEV')")
    public ResponseEntity<List<CarSpecification>> getSpecifications() {
        // Fetch specifications from database
        return ResponseEntity.ok(fetchSpecifications());
    }

    @PutMapping("/specifications/{id}/approve")
    @PreAuthorize("hasRole('OPERATIONS')")
    public ResponseEntity<String> approveSpecification(@PathVariable String id) {
        // Approve car specification
        return ResponseEntity.ok("Specification approved");
    }

    private List<CarSpecification> fetchSpecifications(){
        return List.of(new CarSpecification());
    }
}

