package com.example.g5_gestion_stock_2.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "mv_sorties_v")
public class MvSortiesV {
    @Id
    @Column(name = "id_sortie_stock")
    private Long idSortieStock;

    @Column(name = "quantite_sortie")
    private Double quantiteSortie;

    @Column(name = "quantite_sortie_stock")
    private Double quantiteSortieStock;

    @Column(name = "id_mouvement_sortie")
    private Long idMouvementSortie;

    @Column(name = "date_mouvement_sortie")
    private Instant dateMouvementSortie;

    @Column(name = "id_magasin")
    private Long idMagasin;

    @Column(name = "id_stock")
    private Long idStock;

    @Column(name = "ref_produit")
    private String refProduit;

    @Column(name = "etat")
    private Integer etat;

    public Integer getEtat() {
        return etat;
    }

    public Long getIdSortieStock() {
        return idSortieStock;
    }

    public Double getQuantiteSortie() {
        return quantiteSortie;
    }

    public Double getQuantiteSortieStock() {
        return quantiteSortieStock;
    }

    public Long getIdMouvementSortie() {
        return idMouvementSortie;
    }

    public Instant getDateMouvementSortie() {
        return dateMouvementSortie;
    }

    public Long getIdMagasin() {
        return idMagasin;
    }

    public Long getIdStock() {
        return idStock;
    }

    public String getRefProduit() {
        return refProduit;
    }

    protected MvSortiesV() {
    }
}