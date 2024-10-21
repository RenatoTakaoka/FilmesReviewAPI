package com.github.renatotakaoka.filmes_api.repositories;

import com.github.renatotakaoka.filmes_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
