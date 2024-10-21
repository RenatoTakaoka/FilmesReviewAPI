package com.github.renatotakaoka.filmes_api.repositories;

import com.github.renatotakaoka.filmes_api.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
