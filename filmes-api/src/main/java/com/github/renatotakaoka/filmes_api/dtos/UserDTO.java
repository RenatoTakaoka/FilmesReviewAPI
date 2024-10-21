package com.github.renatotakaoka.filmes_api.dtos;

import com.github.renatotakaoka.filmes_api.models.Review;
import com.github.renatotakaoka.filmes_api.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long id;
    @NotBlank(message = "Nome é requerido")
    private String name;
    @NotBlank(message = "Email é requerido")
    private String email;
    @NotBlank(message = "Senha é requerido")
    private String password;
    private List<Long> userReviewsIds;

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        password = entity.getPassword();
        if(entity.getUserReviews() != null)
            userReviewsIds = entity.getUserReviews().stream().map(Review::getId).toList();
    }
}
