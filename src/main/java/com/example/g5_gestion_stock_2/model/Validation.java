package com.example.g5_gestion_stock_2.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validation", nullable = false)
    private Long id;

    private Timestamp dateValidation;
    private Long idMouvementSortie;

//    getters
    public Timestamp getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Timestamp dateValidation) {
        if(dateValidation==null){
            this.dateValidation = Timestamp.from(Instant.now());
        }
        this.dateValidation = dateValidation;
    }
    public void setDateValidation(String dateValidation) {
        if(dateValidation.isEmpty()){
            setDateValidation(Timestamp.from(Instant.now()));
        }
        setDateValidation(dateValidation);
    }

    public Long getIdMouvementSortie() {
        return idMouvementSortie;
    }

    public void setIdMouvementSortie(Long idSortie) {
        this.idMouvementSortie = idSortie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//    constructor


    public Validation(Long id, Timestamp dateValidation, Long idMouvementSortie) {
        setId(id);
        setDateValidation(dateValidation);
        setIdMouvementSortie(idMouvementSortie);
    }

    public Validation(Timestamp dateValidation, Long idMouvementSortie) {
        setDateValidation(dateValidation);
        setIdMouvementSortie(idMouvementSortie);
    }
    public Validation( Long idMouvementSortie) {
        setDateValidation(Timestamp.from(Instant.now()));
        setIdMouvementSortie(idMouvementSortie);
    }


    public Validation() {
    }
}
