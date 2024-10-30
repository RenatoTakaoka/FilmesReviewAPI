package com.github.renatotakaoka.filmes_api.repositories;

import com.github.renatotakaoka.filmes_api.models.Filme;
import com.github.renatotakaoka.filmes_api.models.Review;
import com.github.renatotakaoka.filmes_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT obj FROM Review obj WHERE: filme IS NULL OR obj.filme = :filme")
    List<Review> findByFilme(Filme filme);

    @Query("SELECT obj FROM Review obj WHERE: user IS NULL OR obj.user = :user")
    List<Review> findByUser(User user);

}
