package com.example.g5_gestion_stock_2.model;

import jakarta.persistence.*;

@Entity
public class Magasin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_magasin", nullable = false)
    private Long id;
    private String nom_magasin;

    public String getNom_magasin() {
        return nom_magasin;
    }

    public void setNom_magasin(String nom_magasin) {
        this.nom_magasin = nom_magasin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Magasin(Long id, String nom_magasin) {
        this.id = id;
        this.nom_magasin = nom_magasin;
    }

    public Magasin() {
    }

    @Override
    public String toString() {
        return "Magasin{" +
                "id=" + id +
                ", nom_magasin='" + nom_magasin + '\'' +
                '}';
    }
}
