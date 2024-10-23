package com.github.renatotakaoka.filmes_api.controllers;

import com.github.renatotakaoka.filmes_api.dtos.GeneroDTO;
import com.github.renatotakaoka.filmes_api.services.GeneroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/generos")
public class GeneroController {
    
    @Autowired
    private GeneroService service;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> findAll() {
        List<GeneroDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GeneroDTO> findById(@PathVariable Long id) {
        GeneroDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> insert(@RequestBody @Valid GeneroDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> update(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid GeneroDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
