package com.github.renatotakaoka.filmes_api.dtos;

import com.github.renatotakaoka.filmes_api.models.Filme;
import com.github.renatotakaoka.filmes_api.models.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class GeneroDTO {

    private Long id;
    private String nome;
    private List<Long> filmesIds;

    public GeneroDTO(Genero entity) {
        id = entity.getId();
        nome = entity.getNome();
        if(entity.getFilmes() != null)
            filmesIds = entity.getFilmes().stream().map(Filme::getId).toList();
    }

}
