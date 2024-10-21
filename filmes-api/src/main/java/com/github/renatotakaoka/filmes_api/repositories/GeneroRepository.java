package com.github.renatotakaoka.filmes_api.repositories;

import com.github.renatotakaoka.filmes_api.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
