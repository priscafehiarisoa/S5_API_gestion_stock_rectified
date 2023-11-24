package com.example.g5_gestion_stock_2.model;

import com.example.g5_gestion_stock_2.repositories.*;
import com.example.g5_gestion_stock_2.view.ResteStockV;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Mouvement_sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mouvement_sortie", nullable = false)
    private Long id;
    private LocalDateTime dateMouvementSortie;
    private double quantite_sortie;
    private int etat;
    private double sortieReel;
    @ManyToOne
    @JoinColumn(name = "ref_produit")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "id_magasin")
    private Magasin magasin;
    @ManyToOne
    @JoinColumn(name = "idUniteEq")
    private UniteEquivalence uniteEquivalence;


    public UniteEquivalence getUniteEquivalence() {
        return uniteEquivalence;
    }

    public void setUniteEquivalence(UniteEquivalence uniteEquivalence) {
        this.uniteEquivalence = uniteEquivalence;
    }

    public LocalDateTime getDateMouvementSortie() {
        return dateMouvementSortie;
    }

    public void setDateMouvementSortie(LocalDateTime dateMouvementSortie) {
        this.dateMouvementSortie = dateMouvementSortie;
    }
    public void setDateMouvementSortie(String dateMouvementSortie) {
        if(dateMouvementSortie.contains("T")){
            dateMouvementSortie=dateMouvementSortie.replace("T"," ");
        }
        if(dateMouvementSortie.isEmpty()){
            setDateMouvementSortie(LocalDateTime.now());
        }
        setDateMouvementSortie(LocalDateTime.now());
    }

    public double getQuantite_sortie() {
        return quantite_sortie;
    }

    public void setQuantite_sortie(double quantite) {
       if(quantite<0){
           throw new IllegalArgumentException("quantite negative");
       }
        this.quantite_sortie = quantite;
    }
    public void setQuantite_sortie(String quantite) {
        if(quantite.isEmpty()){
            throw new IllegalArgumentException("quantite nulle");
        }
        setQuantite_sortie(Double.parseDouble(quantite));
    }

    public double getSortieReel() {
        return sortieReel;
    }

    public void setSortieReel(double sortieReel) {
        this.sortieReel = sortieReel;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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

    public Mouvement_sortie(Long id, LocalDateTime dateMouvementSortie, double quantite_sortie, int etat, Produit produit, Magasin magasin) {
        setId(id);
        setDateMouvementSortie(dateMouvementSortie);
        setQuantite_sortie(quantite_sortie);
        setEtat(etat);
        setProduit(produit);
        setMagasin(magasin);
    }

    public Mouvement_sortie() {
    }

    public Mouvement_sortie(LocalDateTime dateMouvementSortie, double quantite_sortie, int etat, Produit produit, Magasin magasin) {
        setDateMouvementSortie(dateMouvementSortie);
        setQuantite_sortie(quantite_sortie);
        setEtat(etat);
        setProduit(produit);
        setMagasin(magasin);
    }

    @Override
    public String toString() {
        return "Mouvement_sortie{" +
                "id=" + id +
                ", dateMouvementSortie=" + dateMouvementSortie +
                ", quantite_sortie=" + quantite_sortie +
                ", etat=" + etat +
                ", produit=" + produit +
                ", magasin=" + magasin +
                '}';
    }

//    others

    public void saveMouvementSortie(Mouvement_Sortie_Repository mouvementSortieRepository){
        //put the additionnal code here
        mouvementSortieRepository.save(this);
    }

    public List<SortieStock> decompositionSorties(List<ResteStockV> resteStocksParProduit, StockRepository stockRepository){
        List<SortieStock> sortieStocks=new ArrayList<>();
        double quantite_sortie=this.getQuantite_sortie(); //s
        for (int i = 0; quantite_sortie>0; i++) {
            double reste_stock= resteStocksParProduit.get(i).getQuantiteStock(); //n
            SortieStock sortieStock=new SortieStock();
            if(quantite_sortie>reste_stock){
                sortieStock.setQuantite_Sortie(reste_stock);
                quantite_sortie-=reste_stock;
            }
            else{
                sortieStock.setQuantite_Sortie(quantite_sortie);
                quantite_sortie=0;
            }
            sortieStock.setMouvementSortie(this);
            Optional<Stock> stock=stockRepository.findById(resteStocksParProduit.get(i).getIdStock());
            sortieStock.setStock(stock.orElseGet(Stock::new));
            sortieStocks.add(sortieStock);
        }
        return sortieStocks;
    }

    public List<SortieStock> decompositionSorties( ProduitREpository produitREpository, ResteStockREpository resteStockREpository,StockRepository stockRepository){
        List<SortieStock> sortieStocks=new ArrayList<>();
        Optional <Produit> optionalProduit=produitREpository.findByRef_produit(this.getProduit().getRef_produit());
        Produit produit= optionalProduit.orElseGet(Produit::new);
        //ordre de trie de chaque produit
//        String order=produit.isFifo()?"ASC":"DESC";
//        String refPorduit=produit.getRef_produit();
        Long idMagasin=this.getMagasin().getId();
//        List<ResteStockV> l_reste_stock= resteStockREpository.findResteStock(order,refPorduit,idMagasin);
        List<ResteStockV> l_reste_stock= produit.getResteStockParProduit(idMagasin,resteStockREpository);
        System.out.println(">>");
        l_reste_stock.forEach(System.out::println);
        System.out.println(">>");
        checkDisponibiliteStock(l_reste_stock);
        sortieStocks=decompositionSorties(l_reste_stock,stockRepository);

        return sortieStocks;
    }
    public void checkDisponibiliteStock(List<ResteStockV> resteStockVList)   {
        double reste= 0 ;
        for (int i = 0; i < resteStockVList.size(); i++) {
            reste+= resteStockVList.get(i).getQuantiteStock();
        }
        System.out.println(reste+"//"+this.getQuantite_sortie() );
        if(reste< this.getQuantite_sortie()){
            throw new IllegalArgumentException("les restes en stocks sont insuffisantes");
        }
    }
    public void checkDisponibiliteStock2(List<ResteStockV> resteStockVList,double somme)   {
        double reste= 0 ;
        for (int i = 0; i < resteStockVList.size(); i++) {
            reste+= resteStockVList.get(i).getQuantiteStock();
        }
        System.out.println(reste+"//"+this.getQuantite_sortie() );
        if((reste-somme)< this.getQuantite_sortie()){
            throw new IllegalArgumentException("les restes en stocks sont insuffisantes");
        }
    }

    @Transactional
    public void validerMouvement(ProduitREpository produitREpository, ResteStockREpository resteStockREpository, StockRepository stockRepository, SortieStockRepository sortieStockRepository,Mouvement_Sortie_Repository mouvementSortieRepository,ValidationRepository validationRepository){
        //enregistrer les sorties
        List<SortieStock> sorties= this.decompositionSorties(produitREpository,resteStockREpository,stockRepository);
        System.out.println("[[++]]");
        sorties.forEach(System.out::println);
        System.out.println("[[++]]");
        sortieStockRepository.saveAll(sorties);
        // update etat du mouvement
        mouvementSortieRepository.validerMouvementSortie(this.getId());
        //enregistrer une validation
        Validation validation=new Validation(this.getId());
        validationRepository.save(validation);
    }

}
