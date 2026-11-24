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
public class Musee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusee;

    @Column(unique = true, nullable = false)
    private String nomMusee;

    private double superficieMusee;

    @Temporal(TemporalType.DATE)
    private Date dateInauguration;
}