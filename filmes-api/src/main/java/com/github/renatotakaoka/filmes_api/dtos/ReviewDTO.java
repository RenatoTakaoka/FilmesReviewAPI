package com.github.renatotakaoka.filmes_api.dtos;

import com.github.renatotakaoka.filmes_api.models.Review;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReviewDTO {

    private Long id;
    @NotBlank(message = "Texto é requerido")
    private String texto;
    private Long userId;
    private Long filmeId;

    public ReviewDTO(Review entity) {
        id = entity.getId();
        texto = entity.getTexto();
        if(entity.getUser() != null)
            userId = entity.getUser().getId();
        if(entity.getFilme() != null)
            filmeId = entity.getFilme().getId();
    }

}
