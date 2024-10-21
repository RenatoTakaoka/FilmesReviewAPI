package com.github.renatotakaoka.filmes_api.repositories;

import com.github.renatotakaoka.filmes_api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
