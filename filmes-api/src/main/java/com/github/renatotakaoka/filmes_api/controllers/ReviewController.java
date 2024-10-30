package com.github.renatotakaoka.filmes_api.controllers;

import com.github.renatotakaoka.filmes_api.dtos.ReviewDTO;
import com.github.renatotakaoka.filmes_api.services.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll() {
        List<ReviewDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) {
        ReviewDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filme/{id}")
    public ResponseEntity<List<ReviewDTO>> findByFilme(@PathVariable Long id) {
        List<ReviewDTO> dtos = service.findByFilme(id);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReviewDTO>> findByUser(@PathVariable Long id) {
        List<ReviewDTO> dtos = service.findByUser(id);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@RequestBody @Valid ReviewDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable @NotNull Long id,
                                            @RequestBody @Valid ReviewDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
