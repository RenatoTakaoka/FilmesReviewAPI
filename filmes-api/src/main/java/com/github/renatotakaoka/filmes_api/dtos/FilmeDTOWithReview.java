package com.github.renatotakaoka.filmes_api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.renatotakaoka.filmes_api.models.Filme;
import com.github.renatotakaoka.filmes_api.models.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmeDTOWithReview {

    private Long id;
    private String titulo;
    private Integer ano;
    @JsonIgnoreProperties({"filmeId"})
    private List<ReviewDTO> reviewDTOS;
    @JsonIgnoreProperties({"filmesIds"})
    private GeneroDTO generoDTO;

    public FilmeDTOWithReview(Filme entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        ano = entity.getAno();
        reviewDTOS = entity.getFilmeReviews().stream().map(ReviewDTO::new).toList();
        generoDTO = new GeneroDTO(entity.getGenero());
    }

}
