package com.example.g5_gestion_stock_2.model;

import com.example.g5_gestion_stock_2.repositories.ResteStockREpository;
import com.example.g5_gestion_stock_2.view.ResteStockV;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Produit {
    @Id
    @Column(name = "ref_produit", nullable = false)
    private String ref_produit;

    private String nom_produit;
    private boolean isFifo;

    public Produit() {

    }

    public boolean isFifo() {
        return isFifo;
    }

    public void setFifo(boolean fifo) {
        isFifo = fifo;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(String ref_produit) {
        this.ref_produit = ref_produit;
    }

    public Produit(String ref_produit, String nom_produit, boolean isFifo) {
        setFifo(isFifo);
        setNom_produit(nom_produit);
       setRef_produit(ref_produit);
    }
    public List<ResteStockV> getResteStockParProduit(Long idMagasin, ResteStockREpository resteStockREpository){
        String order=this.isFifo()?"ASC":"DESC";
        String refPorduit=this.getRef_produit();
        List<ResteStockV> l_reste_stock= resteStockREpository.findResteStock(order,refPorduit,idMagasin);
        return l_reste_stock;
    }
}
