package com.github.renatotakaoka.filmes_api.dtos;

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

public class FilmeDTO {

    private Long id;
    private String titulo;
    private Integer ano;
    private List<Long> reviewsIds;
    private Long generoId;

    public FilmeDTO(Filme entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        ano = entity.getAno();
        if(entity.getFilmeReviews() != null)
            reviewsIds = entity.getFilmeReviews().stream().map(Review::getId).toList();
        if(entity.getGenero() != null)
            generoId = entity.getGenero().getId();
    }

}
