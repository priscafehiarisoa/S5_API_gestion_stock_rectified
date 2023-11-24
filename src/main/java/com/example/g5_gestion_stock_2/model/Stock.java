package com.example.g5_gestion_stock_2.model;

import com.example.g5_gestion_stock_2.repositories.ResteStockREpository;
import com.example.g5_gestion_stock_2.repositories.StockRepository;
import com.example.g5_gestion_stock_2.repositories.UniteEqREpository;
import com.example.g5_gestion_stock_2.view.ResteStockV;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock", nullable = false)
    private Long id;
    private LocalDateTime dateEntreStock;
    private double quantite_Entree;
    private double prix_unitaire;
    private String ref_produit;
    private double entreeproduitreel;
    private double entreeprixreel;
//    private String unite;

    @ManyToOne
    @JoinColumn(name = "idequivalence")
    private UniteEquivalence uniteEquivalence;
    @ManyToOne
    @JoinColumn(name = "id_magasin")
    private Magasin magasin;
//



    public Stock(Long id, LocalDateTime dateEntreStock, double quantite_Entree, double prix_unitaire, String ref_produit, Magasin magasin,UniteEquivalence uniteEquivalence) throws Exception {
        setId(id);
        setDateEntreStock(dateEntreStock);
        setQuantite_Entree(quantite_Entree);
        setPrix_unitaire(prix_unitaire);
        setRef_produit(ref_produit);

        setMagasin(magasin);
        setUniteEquivalence(uniteEquivalence);
    }

    public Stock(LocalDateTime dateEntreStock, double quantite_Entree, double prix_unitaire, String ref_produit, UniteEquivalence uniteEquivalence, Magasin magasin) throws Exception {
        setDateEntreStock(dateEntreStock);
        setQuantite_Entree(quantite_Entree);
        setPrix_unitaire(prix_unitaire);
        setRef_produit(ref_produit);
        setMagasin(magasin);
        setUniteEquivalence(uniteEquivalence);
    }

    public Stock() {
    }
    //  getters and setters


    public double getEntreeproduitreel() {
        return entreeproduitreel;
    }

    public void setEntreeproduitreel(double entreeproduitreel) {
        this.entreeproduitreel = entreeproduitreel;
    }

    public double getEntreeprixreel() {
        return entreeprixreel;
    }

    public void setEntreeprixreel(double entreeprixreel) {
        this.entreeprixreel = entreeprixreel;
    }

    public LocalDateTime getDateEntreStock() {
        return dateEntreStock;
    }

    public void setDateEntreStock(LocalDateTime dateEntreStock) {
        this.dateEntreStock = dateEntreStock;
    }
    public void setDateEntreStock(String dateEntreStock) {
        if(dateEntreStock.contains("T")){
            dateEntreStock=dateEntreStock.replace("T"," ");
        }
        if(dateEntreStock.isEmpty()){
            setDateEntreStock(LocalDateTime.now());
        }
        setDateEntreStock(LocalDateTime.now());
    }

    public double getQuantite_Entree() {
        return quantite_Entree;
    }

    public void setQuantite_Entree(double quantite_Entree) {
        if(quantite_Entree<0){
            throw new IllegalArgumentException("la quantite d'entrée est negative ");
        }
        this.quantite_Entree = quantite_Entree;
    }
    public void setQuantite_Entree(String quantite_Entree) {
        if(quantite_Entree.isEmpty()){
            throw new IllegalArgumentException("la quantite d'entrée est nulle ");
        }
        setQuantite_Entree(Double.parseDouble(quantite_Entree));
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        if(prix_unitaire<0){
            throw new IllegalArgumentException("le prix unitaire ne peut pas etre negative ");
        }
        this.prix_unitaire = prix_unitaire;
    }
    public void setPrix_unitaire(String prix_unitaire) {
        if(prix_unitaire.isEmpty()){
            throw new IllegalArgumentException("le prix unitaire est nulle ");
        }
        setPrix_unitaire(Double.parseDouble(prix_unitaire));
    }

    public String getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(String ref_produit) {
        this.ref_produit = ref_produit;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UniteEquivalence getUniteEquivalence() {
        return uniteEquivalence;
    }

    public void setUniteEquivalence(UniteEquivalence uniteEquivalence) throws Exception {
//        if(uniteEquivalence.getProduit()!=null){
//            if(!uniteEquivalence.getProduit().getRef_produit().equals(this.getRef_produit())){
//                System.out.println("ref1 "+uniteEquivalence.getProduit().getRef_produit()+"...."+this.getRef_produit());
//                throw new Exception("l'equivalence ne correspond pas au produit");
//            }
//        }

        this.uniteEquivalence = uniteEquivalence;
    }

    public void newStock(UniteEqREpository uniteEqREpository, StockRepository stockRepository) throws Exception {
        //getEquivalence

        System.out.println("=>"+this.toString());
        Optional<UniteEquivalence> u= uniteEqREpository.findById(this.getUniteEquivalence().getId());
        setEntreeprixreel(getPrix_unitaire());
        setEntreeproduitreel(getQuantite_Entree());
        if(u.isPresent()){

            double nombreStock= 0 ;
            double prixStock=0 ;
            nombreStock=this.getQuantite_Entree()*u.get().getEquivalence2();
            prixStock=getPrix_unitaire()/u.get().getEquivalence2();
            this.setQuantite_Entree(nombreStock);
            this.setPrix_unitaire(prixStock);
            this.setUniteEquivalence(u.get());
        }
        stockRepository.save(this);
    }

    //    constructor

//    others

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", dateEntreStock=" + dateEntreStock +
                ", quantite_Entree=" + quantite_Entree +
                ", prix_unitaire=" + prix_unitaire +
                ", ref_produit='" + ref_produit + '\'' +
                ", magasin=" + magasin +
                '}';
    }


}
