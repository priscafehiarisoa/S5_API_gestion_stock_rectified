package com.example.g5_gestion_stock_2.model;


import com.example.g5_gestion_stock_2.repositories.MagasinRepository;
import com.example.g5_gestion_stock_2.repositories.ProduitREpository;
import com.example.g5_gestion_stock_2.repositories.StockRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailEtatDeStock {
    private Timestamp dateDebut;
    private Timestamp dateFin ;
    private double stockInitiale=0;
    private double stockFinale=0;
    private double sortie_Stock=0;
    private String references;
    private double montant =0;
    private Produit produit;
    private Magasin magasin;
    private List<Stock> resteStockInitiale;
    private List<Stock> resteStockFinale;
    private double prixUnitaireMoyen;

    private Long idUniteEquivalence;





//-----------------------------------------


    public double getPrixUnitaireMoyen() {
        return prixUnitaireMoyen;
    }

    public void setPrixUnitaireMoyen(double prixUnitaireMoyen) {
        this.prixUnitaireMoyen = prixUnitaireMoyen;
    }
    public void setPrixUnitaireMoyen() {
        if(!getResteStockFinale().isEmpty()){
            double prix =0;
            double total =0;
            for (int i = 0; i < getResteStockFinale().size(); i++) {
                prix+=getResteStockFinale().get(i).getQuantite_Entree()*getResteStockFinale().get(i).getPrix_unitaire();
                total+=getResteStockFinale().get(i).getQuantite_Entree();
            }
            setPrixUnitaireMoyen(prix/total);
        }
    }

    public Magasin getMagasin() {

        return magasin;
    }

    public void setMagasin(Magasin magasin) {

        this.magasin = magasin;
    }
    public void setMagasin() {
        if(stockInitiale>=0 && !getResteStockInitiale().isEmpty()){
            this.magasin = getResteStockInitiale().get(0).getMagasin();
        }
        else if(stockFinale>=0 && !getResteStockFinale().isEmpty()){
            this.magasin = getResteStockFinale().get(0).getMagasin();
        }

    }

    public double getSortie_Stock() {
        return sortie_Stock;
    }

    public void setSortie_Stock(double sortie_Stock) {
        this.sortie_Stock = sortie_Stock;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public List<Stock> getResteStockInitiale() {
        return resteStockInitiale;
    }

    public void setResteStockInitiale(List<Stock> resteStockInitiale) {
        this.resteStockInitiale = resteStockInitiale;
    }

    public List<Stock> getResteStockFinale() {
        return resteStockFinale;
    }

    public void setResteStockFinale(List<Stock> resteStockFinale) {
        this.resteStockFinale = resteStockFinale;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        dateDebut=dateDebut.replace("T"," ");
        setDateDebut(Timestamp.valueOf(dateDebut));
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
    public void setDateFin(String dateFin) {
        dateFin=dateFin.replace("T"," ");
        setDateFin(Timestamp.valueOf(dateFin));
    }

    public double getStockInitiale() {
        if(!getResteStockFinale().isEmpty()){
            double montant = 0 ;
            List<Stock> stock = this.getResteStockInitiale();
            for (int i = 0; i < stock.size(); i++) {
                montant+=stock.get(i).getQuantite_Entree();
            }
            return montant;
        }
        return stockInitiale;
    }

    public void setStockInitiale(double stockInitiale) {
        
        this.stockInitiale = stockInitiale;
    }

    public double getStockFinale() {
        if(!getResteStockFinale().isEmpty()){
            double montant = 0 ;
            List<Stock> stock = this.getResteStockFinale();
            for (int i = 0; i < stock.size(); i++) {
                montant+=stock.get(i).getQuantite_Entree();
            }
            return montant;
        }
        return stockFinale;
    }

    public void setStockFinale(double stockFinale) {
        this.stockFinale = stockFinale;
    }

    public double getMontant() {
        if(!getResteStockFinale().isEmpty()){
            double montant = 0 ;
            List<Stock> stock = this.getResteStockFinale();
            for (int i = 0; i < stock.size(); i++) {
                montant+= stock.get(i).getQuantite_Entree()*stock.get(i).getPrix_unitaire();
            }
            return montant;
        }
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
//-----------------------------------------


    public DetailEtatDeStock(Timestamp dateDebut, Timestamp dateFin, double stockInitiale, double stockFinale, double montant, Produit produit) {
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setStockFinale(stockFinale);
        setStockInitiale(stockInitiale);
        setMontant(montant);
        setProduit(produit);
    }

    public DetailEtatDeStock(){
    }

    public DetailEtatDeStock (Timestamp dateDebut, Timestamp dateFin, Produit produit){
        setDateFin(dateFin);
        setDateDebut(dateDebut);
        setProduit(produit);
//        setResteStockFinale();
    }
    public DetailEtatDeStock (Timestamp dateDebut, Timestamp dateFin, String references){
        setDateFin(dateFin);
        setDateDebut(dateDebut);
        setReferences(references);
    }

    @Override
    public String toString() {
        return "DetailEtatDeStock{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", stockInitiale=" + stockInitiale +
                ", stockFinale=" + stockFinale +
                ", sortie_Stock=" + sortie_Stock +
                ", references='" + references + '\'' +
                ", montant=" + montant +
                ", produit=" + produit +
                ", magasin=" + magasin +
                ", resteStockInitiale=" + resteStockInitiale +
                ", resteStockFinale=" + resteStockFinale +
                ", prixUnitaireMoyen=" + prixUnitaireMoyen +
                '}';
    }

    //    ==========
public List<DetailEtatDeStock> getAllEtatDeStock(ProduitREpository produitREpository, StockRepository stockRepository, MagasinRepository magasinRepository){
    // todo verifier si la reference est egale à %
    List<Produit> listeProduit=new ArrayList<>();
    if(this.getReferences().equals("%")){
        listeProduit=produitREpository.findAll();
    }else{
        listeProduit= produitREpository.getAllProduitByReferences(this.getReferences());
    }    System.out.println(listeProduit.toString());
    List<DetailEtatDeStock> listeEtatDeStock = new ArrayList<>();
    for (int i = 0; i < listeProduit.size(); i++) {
        DetailEtatDeStock etat= getOneEtatDeStock(this.getDateDebut(),this.getDateFin(),listeProduit.get(i).getRef_produit(), this.getReferences(),this.getMagasin().getId(), produitREpository,  stockRepository,  magasinRepository);
        listeEtatDeStock.add(etat);
    }
    return listeEtatDeStock;
}
    public DetailEtatDeStock getOneEtatDeStock(Timestamp dateDebut, Timestamp dateFin, String refProduit, String references, Long idMagasin, ProduitREpository produitREpository, StockRepository stockRepository, MagasinRepository magasinRepository){
        System.out.println(refProduit);
        Optional<Produit> p = produitREpository.findById(refProduit);
        DetailEtatDeStock nouvelEtat= new DetailEtatDeStock(dateDebut,dateFin, p.orElseGet(Produit::new));
        nouvelEtat.setReferences(references);
        // set etat initiale
//        todo à revoir
        nouvelEtat.setResteStockInitiale(stockRepository.getAllStock_mouvement(dateDebut,refProduit,idMagasin));
        // set etat Finale
        //        todo à revoir
        nouvelEtat.setResteStockFinale(stockRepository.getAllStock_mouvement(dateFin,refProduit,idMagasin));
        nouvelEtat.setProduit(p.get());
        nouvelEtat.setMagasin(magasinRepository.findById(idMagasin).get());
        nouvelEtat.setSortie_Stock(stockRepository.getSumSortiesStockByDate(dateFin,refProduit,idMagasin));
        nouvelEtat.setPrixUnitaireMoyen();

        return nouvelEtat;
    }
}
