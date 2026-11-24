package com.mehdi.users.repositories;

import com.mehdi.users.entities.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OeuvreRepository extends JpaRepository<Oeuvre, Long> {
    List<Oeuvre> findByMuseeIdMusee(Long idMusee);
}