package com.example.g5_gestion_stock_2.service;

import com.example.g5_gestion_stock_2.model.*;
import com.example.g5_gestion_stock_2.repositories.*;
import com.example.g5_gestion_stock_2.view.ResteStockV;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {

    private final Mouvement_Sortie_Repository mouvement_Sortie_Repository;
    private final ProduitREpository produitREpository;
    private final ResteStockREpository resteStockREpository;
    private final StockRepository stockRepository;
    private final SortieStockRepository sortieStockRepository;
    private final ValidationRepository validationRepository;
    private final MagasinRepository magasinRepository;
    private final UniteEqREpository uniteEqREpository;

    public SortieService(Mouvement_Sortie_Repository mouvement_Sortie_Repository,
                         ProduitREpository produitREpository,
                         ResteStockREpository resteStockREpository,
                         StockRepository stockRepository,
                         SortieStockRepository sortieStockRepository,
                         ValidationRepository validationRepository,
                         MagasinRepository magasinRepository,
                         UniteEqREpository uniteEqREpository) {
        this.mouvement_Sortie_Repository = mouvement_Sortie_Repository;
        this.produitREpository = produitREpository;
        this.resteStockREpository = resteStockREpository;
        this.stockRepository = stockRepository;
        this.sortieStockRepository = sortieStockRepository;
        this.validationRepository = validationRepository;
        this.magasinRepository = magasinRepository;
        this.uniteEqREpository = uniteEqREpository;
    }

    //enregistrer une sortie de stock
    public void saveSortieStock(Mouvement_sortie mouvementSortie) throws Exception {
        // verifier si date < date dernier truc validé
        Optional<Mouvement_sortie> derniermv = mouvement_Sortie_Repository.getDernierMouvementValide(mouvementSortie.getProduit().getRef_produit(),mouvementSortie.getMagasin().getId());
        Optional<Produit> p=produitREpository.findByRef_produit(mouvementSortie.getProduit().getRef_produit());
        Produit produit= p.orElse(new Produit());
        if(derniermv.isPresent()){
            System.out.println("hello here is present ");
            if(mouvementSortie.getDateMouvementSortie().isAfter(derniermv.get().getDateMouvementSortie())){
                throw new Exception("la date ne peut etre inférieure à la derniere date de validation "+derniermv.get().getDateMouvementSortie());
            }
        }
        double sommemvt=mouvement_Sortie_Repository.sommeMouvementNonVAlide(mouvementSortie.getProduit().getRef_produit(),mouvementSortie.getMagasin().getId());
        List<ResteStockV> l_reste_stock= produit.getResteStockParProduit(mouvementSortie.getMagasin().getId(),resteStockREpository);
        mouvementSortie.checkDisponibiliteStock2(l_reste_stock,sommemvt);
        // ny equivalence an'le sortie no apetrako any miaraka @ le prix
        Optional<UniteEquivalence> u = uniteEqREpository.findById(mouvementSortie.getUniteEquivalence().getId());
        mouvementSortie.setSortieReel(mouvementSortie.getQuantite_sortie());
        if(u.isPresent()){
            mouvementSortie.setQuantite_sortie((mouvementSortie.getQuantite_sortie() *u.get().getEquivalence2())/u.get().getEquivalence1());
        }

        mouvement_Sortie_Repository.save(mouvementSortie);
    }
    //valider une sortie
    @Transactional
    public void validerSortie(Long idMouvement){
        Optional<Mouvement_sortie> optmouvementSortie= mouvement_Sortie_Repository.findById(idMouvement);
        if(optmouvementSortie.isPresent()){
            Mouvement_sortie mv= optmouvementSortie.get();
            System.out.println("--=--"+ mv.toString());
            mv.validerMouvement(produitREpository,resteStockREpository,stockRepository,sortieStockRepository,mouvement_Sortie_Repository,validationRepository);
        }
        else{
            throw new IllegalArgumentException("le mouvement sélectionné n'existe pas");
        }
    }
    // liste des mouvements non validés
    public List<Mouvement_sortie> getAllMouvementNonValide(){
       return mouvement_Sortie_Repository.getMouvementN0nValide();
    }
    // liste mouvement validés
    public List<Mouvement_sortie> getAllMouvementValide(){
        return mouvement_Sortie_Repository.getMouvementValide();
    }
    // liste mouvement en attente
    public List<Mouvement_sortie> getAllMouvementAttente(){
        return mouvement_Sortie_Repository.getMouvementEnAttente();
    }

    // get all etat de stock
    public EtatDeStock getEtatdestock( DetailEtatDeStock etatDeStock){
        List<DetailEtatDeStock> e=etatDeStock.getAllEtatDeStock(produitREpository,stockRepository,magasinRepository);
        return new EtatDeStock(e);
    }
    // magasin
    public List<Magasin> getMAgasin(){
        return magasinRepository.findAll();
    }

    // produit
    public List<Produit> getProduit(){
        return produitREpository.findAll();
    }

    public List<UniteEquivalence> getUnite() {
        return uniteEqREpository.findAll();
    }

    public void saveStock(Stock stock) throws Exception {
        stock.newStock(uniteEqREpository,stockRepository);
    }


}


