package com.example.g5_gestion_stock_2.controller;

import com.example.g5_gestion_stock_2.model.*;
import com.example.g5_gestion_stock_2.repositories.MagasinRepository;
import com.example.g5_gestion_stock_2.repositories.StockRepository;
import com.example.g5_gestion_stock_2.service.SortieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin(origins = "http://localhost:3000")
public class SortieController {
    private final SortieService sortieService;
    private final MagasinRepository magasinRepository;
    private final StockRepository stockRepository;

    public SortieController(SortieService sortieService,
                            MagasinRepository magasinRepository,
                            StockRepository stockRepository) {
        this.sortieService = sortieService;
        this.magasinRepository = magasinRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping("/mvtValide")
    public List<Mouvement_sortie> mvtValide(){
       return sortieService.getAllMouvementValide();
    }

    @GetMapping("/mvtNonValide")
    public List<Mouvement_sortie> mvtNOnValide(){
       return sortieService.getAllMouvementNonValide();
    }
    @GetMapping("/mvtEnAttente")
    public List<Mouvement_sortie> mvtEnAttente(){
       return sortieService.getAllMouvementAttente();
    }

    @PostMapping("/savemouvement")
    public void save(@RequestBody Mouvement_sortie mouvementSortie) throws Exception {
        System.out.println("*** "+mouvementSortie.toString());
        sortieService.saveSortieStock(mouvementSortie);
    }
    @PostMapping("/validerMouvement/{id}")
    public void valider(@PathVariable Long id){
        sortieService.validerSortie(id);
    }

    @PostMapping("/etatDeStock")
    public EtatDeStock getEtatdestock(@RequestBody DetailEtatDeStock etatDeStock){
        return sortieService.getEtatdestock(etatDeStock);
    }

//    magasin
    @GetMapping("/magasin")
    public List<Magasin> getMagasin(){
        return sortieService.getMAgasin();
    }

//    produit
    @GetMapping("/produit")
    public List<Produit> getProduit(){
        return sortieService.getProduit();
    }

    @GetMapping("/unite")
    public List<UniteEquivalence> getUnite(){
        return sortieService.getUnite();
    }

    @PostMapping("/saveStock")
    public void save(@RequestBody Stock stock) throws Exception {
        sortieService.saveStock(stock);
    }

    @GetMapping
    public List<Stock> getStock(){
       return stockRepository.findAll();
    }


}
