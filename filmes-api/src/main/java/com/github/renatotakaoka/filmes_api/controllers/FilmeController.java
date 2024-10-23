package com.github.renatotakaoka.filmes_api.controllers;

import com.github.renatotakaoka.filmes_api.dtos.FilmeDTO;
import com.github.renatotakaoka.filmes_api.dtos.FilmeDTOWithReview;
import com.github.renatotakaoka.filmes_api.services.FilmeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public ResponseEntity<List<FilmeDTOWithReview>> findAll() {
        List<FilmeDTOWithReview> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FilmeDTOWithReview> findById(@PathVariable Long id) {
        FilmeDTOWithReview dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FilmeDTO> insert(@RequestBody @Valid FilmeDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> update(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid FilmeDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
