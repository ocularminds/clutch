package com.binaries.clutch.controllers;

import com.binaries.clutch.model.CarSpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpecificationController {

    @GetMapping
    public List<CarSpecification> getSpecifications() {
        return List.of(new CarSpecification(), new CarSpecification());
    }

    @GetMapping("/specifications/{id}")
    public CarSpecification getSpecification() {
        return new CarSpecification();
    }

    @PostMapping("/specifications")
    public ResponseEntity create(CarSpecification spec) {
        return ResponseEntity.ok(spec);
    }

    @PutMapping("/specifications/{id}/update")
    public ResponseEntity update(CarSpecification spec) {
        return ResponseEntity.ok(spec);
    }
}
