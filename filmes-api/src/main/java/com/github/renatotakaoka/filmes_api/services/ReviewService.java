package com.github.renatotakaoka.filmes_api.services;

import com.github.renatotakaoka.filmes_api.dtos.ReviewDTO;
import com.github.renatotakaoka.filmes_api.exceptions.DatabaseException;
import com.github.renatotakaoka.filmes_api.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.filmes_api.models.Filme;
import com.github.renatotakaoka.filmes_api.models.Review;
import com.github.renatotakaoka.filmes_api.models.User;
import com.github.renatotakaoka.filmes_api.repositories.FilmeRepository;
import com.github.renatotakaoka.filmes_api.repositories.ReviewRepository;
import com.github.renatotakaoka.filmes_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        return repository.findAll().stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review review = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ReviewDTO(review);
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ReviewDTO(entity);
    }

    @Transactional
    public ReviewDTO update(Long id, ReviewDTO dto) {
        try {
            Review entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ReviewDTO(entity);
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

    @Transactional(readOnly = true)
    public List<ReviewDTO> findByFilme(Long filmeId) {
        if(!filmeRepository.existsById(filmeId)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        Filme filme = filmeRepository.getReferenceById(filmeId);
        List<Review> reviewList = repository.findByFilme(filme);
        return reviewList.stream().map(ReviewDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findByUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        User user = userRepository.getReferenceById(userId);
        List<Review> reviewList = repository.findByUser(user);
        return reviewList.stream().map(ReviewDTO::new).toList();
    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity) {
        entity.setTexto(dto.getTexto());
        entity.setUser(userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        ));
        entity.setFilme(filmeRepository.findById(dto.getFilmeId()).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        ));
    }

}
