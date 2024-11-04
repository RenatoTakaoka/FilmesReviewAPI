package com.github.renatotakaoka.filmes_api.services;

import com.github.renatotakaoka.filmes_api.dtos.RoleDTO;
import com.github.renatotakaoka.filmes_api.dtos.UserDTO;
import com.github.renatotakaoka.filmes_api.dtos.UserInsertDTO;
import com.github.renatotakaoka.filmes_api.exceptions.DatabaseException;
import com.github.renatotakaoka.filmes_api.exceptions.ResourceNotFoundException;
import com.github.renatotakaoka.filmes_api.models.Role;
import com.github.renatotakaoka.filmes_api.models.User;
import com.github.renatotakaoka.filmes_api.projections.UserDetailProjection;
import com.github.renatotakaoka.filmes_api.repositories.ReviewRepository;
import com.github.renatotakaoka.filmes_api.repositories.RoleRepository;
import com.github.renatotakaoka.filmes_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            if(dto.getUserReviewsIds() != null)
                entity.setUserReviews(reviewRepository.findAllById(dto.getUserReviewsIds()));
            entity = repository.save(entity);
            return new UserDTO(entity);
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

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
//        entity.setPassword(dto.getPassword());
        entity.getRoles().clear();
        for(RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailProjection> list = repository.searchUserAndRolesByEmail(username);
        if(list.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado");
        }
        User user = new User();
        user.setName(username);
        user.setPassword(list.get(0).getPassword());
        for(UserDetailProjection projection : list) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
