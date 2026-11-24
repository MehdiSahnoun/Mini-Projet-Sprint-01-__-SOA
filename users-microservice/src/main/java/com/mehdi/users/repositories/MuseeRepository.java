package com.mehdi.users.repositories;

import com.mehdi.users.entities.Musee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuseeRepository extends JpaRepository<Musee, Long> {
    List<Musee> findByNomMuseeContainingIgnoreCase(String nom);
}