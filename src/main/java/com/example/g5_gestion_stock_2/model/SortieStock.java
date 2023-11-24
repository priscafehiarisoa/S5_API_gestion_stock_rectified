package com.example.g5_gestion_stock_2.model;

import jakarta.persistence.*;

@Entity
public class SortieStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sortie_stock", nullable = false)
    private Long id;
    private double quantite_Sortie;

    @ManyToOne
    @JoinColumn(name = "id_mouvement_sortie")

    private Mouvement_sortie mouvementSortie;
    @ManyToOne
    @JoinColumn(name = "id_stock")
    private Stock stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public double getQuantite_Sortie() {
        return quantite_Sortie;
    }


    public void setQuantite_Sortie(double quantite_Entree) {
        if(quantite_Entree<0){
            throw new IllegalArgumentException("la quantite d'entrée est negative ");
        }
        this.quantite_Sortie = quantite_Entree;
    }
    public void setQuantite_Sortie(String quantite_Entree) {
        if(quantite_Entree.isEmpty()){
            throw new IllegalArgumentException("la quantite d'entrée est nulle ");
        }
        setQuantite_Sortie(Double.parseDouble(quantite_Entree));
    }

    public Mouvement_sortie getMouvementSortie() {
        return mouvementSortie;
    }

    public void setMouvementSortie(Mouvement_sortie mouvementSortie) {
        this.mouvementSortie = mouvementSortie;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "SortieStock{" +
                "id=" + id +
                ", quantite_Sortie=" + quantite_Sortie +
                ", stock=" + stock +
                ", mouvementSortie=" + mouvementSortie +
                '}';
    }
}
