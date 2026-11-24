package com.mehdi.users.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oeuvre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOeuvre;

    private String titreOeuvre;

    private int anneeCreation;

    @Temporal(TemporalType.DATE)
    private Date dateAcquisition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "musee_id")
    private Musee musee;
}