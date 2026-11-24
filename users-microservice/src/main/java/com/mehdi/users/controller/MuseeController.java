package com.mehdi.users.controller;

import com.mehdi.users.entities.Musee;
import com.mehdi.users.repositories.MuseeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musees")
@CrossOrigin(origins = "http://localhost:4200")
public class MuseeController {

    @Autowired
    private MuseeRepository museeRepository;

    @GetMapping
    public List<Musee> getAllMusees() {
        return museeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Musee getMusee(@PathVariable Long id) {
        return museeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Musee not found with id: " + id));
    }

    @PostMapping
    public Musee createMusee(@RequestBody Musee musee) {
        return museeRepository.save(musee);
    }

    @PutMapping("/{id}")
    public Musee updateMusee(@PathVariable Long id, @RequestBody Musee museeDetails) {
        Musee musee = museeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Musee not found with id: " + id));
        musee.setNomMusee(museeDetails.getNomMusee());
        musee.setSuperficieMusee(museeDetails.getSuperficieMusee());
        musee.setDateInauguration(museeDetails.getDateInauguration());
        return museeRepository.save(musee);
    }

    @DeleteMapping("/{id}")
    public void deleteMusee(@PathVariable Long id) {
        museeRepository.deleteById(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Musee> findByNomMusee(@PathVariable String nom) {
        return museeRepository.findByNomMuseeContainingIgnoreCase(nom);
    }
}