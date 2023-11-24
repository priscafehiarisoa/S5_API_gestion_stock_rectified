package com.example.g5_gestion_stock_2.model;

import jakarta.persistence.*;

@Entity
public class UniteEquivalence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eq", nullable = false)
    private Long id;

    @ManyToOne
    private Produit produit;
    private double equivalence1;
    private double equivalence2;
    private String denom ;

    public Produit getProduit() {
        return produit;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getEquivalence1() {
        return equivalence1;
    }

    public void setEquivalence1(double equivalence1) throws Exception {
        if(equivalence1<0){
            throw new Exception("equivalence cannot be negative");
        }
        this.equivalence1 = equivalence1;
    }

    public double getEquivalence2() {
        return equivalence2;
    }

    public void setEquivalence2(double equivalence2) throws Exception {
        if(equivalence2<0){
            throw new Exception("equivalence cannot be negative");
        }
        this.equivalence2 = equivalence2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UniteEquivalence(Long id, Produit produit, double equivalence1, double equivalence2,String denom) throws Exception {
        setEquivalence1(equivalence1);
        setEquivalence2(equivalence2);
        setId(id);
        setProduit(produit);
        setDenom(denom);
    }

    public UniteEquivalence(Produit produit, double equivalence1, double equivalence2,String denom) throws Exception {
        setProduit(produit);
        setEquivalence1(equivalence1);
        setEquivalence2(equivalence2);
        setDenom(denom);
    }

    public UniteEquivalence() {
    }
}
