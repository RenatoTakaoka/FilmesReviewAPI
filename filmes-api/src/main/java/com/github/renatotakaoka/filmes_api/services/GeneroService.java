package com.github.renatotakaoka.filmes_api.services;

import com.github.renatotakaoka.filmes_api.dtos.GeneroDTO;
import com.github.renatotakaoka.filmes_api.exceptions.DatabaseException;
import com.github.renatotakaoka.filmes_api.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.filmes_api.models.Genero;
import com.github.renatotakaoka.filmes_api.repositories.FilmeRepository;
import com.github.renatotakaoka.filmes_api.repositories.GeneroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;
    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = true)
    public List<GeneroDTO> findAll() {
        return repository.findAll().stream().map(GeneroDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GeneroDTO findById(Long id) {
        Genero genero = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new GeneroDTO(genero);
    }

    @Transactional
    public GeneroDTO insert(GeneroDTO dto) {
        Genero entity = new Genero();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new GeneroDTO(entity);
    }

    @Transactional
    public GeneroDTO update(Long id, GeneroDTO dto) {
        try {
            Genero entity = repository.getReferenceById(id);
            copyDtoToEntity(dto , entity);
            entity = repository.save(entity);
            return new GeneroDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(GeneroDTO dto, Genero entity) {
        entity.setNome(dto.getNome());
        entity.setFilmes(filmeRepository.findAllById(dto.getFilmesIds()));
    }

}
