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
@Table(name = "reste_stock_v")
public class ResteStockV {
    @Column(name = "ref_produit")
    private String refProduit;

    @Id
    @Column(name = "id_stock")
    private Long idStock;

    @Column(name = "quantite_stock")
    private Double quantiteStock;

    @Column(name = "id_magasin")
    private Long idMagasin;

    @Column(name = "unite")
    private String unite;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "date_entre_stock")
    private Instant dateEntreStock;

    public String getRefProduit() {
        return refProduit;
    }

    public Long getIdStock() {
        return idStock;
    }

    public Double getQuantiteStock() {
        return quantiteStock;
    }

    public Long getIdMagasin() {
        return idMagasin;
    }

    public String getUnite() {
        return unite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public Instant getDateEntreStock() {
        return dateEntreStock;
    }

    protected ResteStockV() {
    }

    @Override
    public String toString() {
        return "ResteStockV{" +
                "refProduit='" + refProduit + '\'' +
                ", idStock=" + idStock +
                ", quantiteStock=" + quantiteStock +
                ", idMagasin=" + idMagasin +
                ", unite='" + unite + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", dateEntreStock=" + dateEntreStock +
                '}';
    }
}