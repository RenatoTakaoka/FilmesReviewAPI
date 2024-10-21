package com.github.renatotakaoka.filmes_api.services;

import com.github.renatotakaoka.filmes_api.dtos.FilmeDTO;
import com.github.renatotakaoka.filmes_api.exceptions.DatabaseException;
import com.github.renatotakaoka.filmes_api.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.filmes_api.models.Filme;
import com.github.renatotakaoka.filmes_api.repositories.FilmeRepository;
import com.github.renatotakaoka.filmes_api.repositories.GeneroRepository;
import com.github.renatotakaoka.filmes_api.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<FilmeDTO> findAll() {
        return repository.findAll().stream().map(FilmeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FilmeDTO findById(Long id) {
        Filme filme = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new FilmeDTO(filme);
    }

    @Transactional
    public FilmeDTO insert(FilmeDTO dto) {
        Filme entity = new Filme();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new FilmeDTO(entity);
    }

    @Transactional
    public FilmeDTO update(Long id, FilmeDTO dto) {
        try {
            Filme entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            if(dto.getReviewsIds() != null)
                entity.setFilmeReviews(reviewRepository.findAllById(dto.getReviewsIds()));
            entity = repository.save(entity);
            return new FilmeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontado");
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

    private void copyDtoToEntity(FilmeDTO dto, Filme entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setAno(dto.getAno());
        entity.setGenero(generoRepository.findById(dto.getGeneroId()).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        ));
    }

}
