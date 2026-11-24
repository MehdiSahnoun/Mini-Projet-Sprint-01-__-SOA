package com.mehdi.users.controller;

import com.mehdi.users.entities.Musee;
import com.mehdi.users.entities.Oeuvre;
import com.mehdi.users.repositories.MuseeRepository;
import com.mehdi.users.repositories.OeuvreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class OeuvreController {

    @Autowired
    private OeuvreRepository oeuvreRepository;

    @Autowired
    private MuseeRepository museeRepository;

    @GetMapping("/all")
    public List<Oeuvre> getAllOeuvres() {
        return oeuvreRepository.findAll();
    }

    @GetMapping("/getbyid/{id}")
    public Oeuvre getOeuvre(@PathVariable Long id) {
        return oeuvreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oeuvre not found with id: " + id));
    }

    @PostMapping("/addoeuv")
    public Oeuvre addOeuvre(@RequestBody Oeuvre oeuvre) {
        if (oeuvre.getMusee() != null && oeuvre.getMusee().getIdMusee() != null) {
            Musee musee = museeRepository.findById(oeuvre.getMusee().getIdMusee())
                    .orElseThrow(() -> new RuntimeException("Musee not found with id: " + oeuvre.getMusee().getIdMusee()));
            oeuvre.setMusee(musee);
        }
        return oeuvreRepository.save(oeuvre);
    }

    @PutMapping("/updateoeuv")
    public Oeuvre updateOeuvre(@RequestBody Oeuvre oeuvre) {
        if (oeuvre.getIdOeuvre() == null) {
            throw new RuntimeException("Oeuvre id is required for update");
        }
        Oeuvre existing = oeuvreRepository.findById(oeuvre.getIdOeuvre())
                .orElseThrow(() -> new RuntimeException("Oeuvre not found with id: " + oeuvre.getIdOeuvre()));
        existing.setTitreOeuvre(oeuvre.getTitreOeuvre());
        existing.setAnneeCreation(oeuvre.getAnneeCreation());
        existing.setDateAcquisition(oeuvre.getDateAcquisition());
        existing.setMusee(oeuvre.getMusee());
        return oeuvreRepository.save(existing);
    }

    @DeleteMapping("/deloeuv/{id}")
    public void deleteOeuvre(@PathVariable Long id) {
        oeuvreRepository.deleteById(id);
    }

    @GetMapping("/oeuvsmusee/{idMusee}")
    public List<Oeuvre> getOeuvresByMusee(@PathVariable Long idMusee) {
        return oeuvreRepository.findByMuseeIdMusee(idMusee);
    }
}