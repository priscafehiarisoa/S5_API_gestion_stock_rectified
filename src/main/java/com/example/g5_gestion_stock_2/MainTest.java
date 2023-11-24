package com.example.g5_gestion_stock_2;

import com.example.g5_gestion_stock_2.model.*;
import com.example.g5_gestion_stock_2.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class MainTest {
    @Bean
    CommandLineRunner commandLineRunner (Mouvement_Sortie_Repository mouvement_Sortie_Repository,
                                         ProduitREpository produitREpository,
                                         ResteStockREpository resteStockREpository,
                                         StockRepository stockRepository,
                                         MagasinRepository magasinRepository,
                                         SortieStockRepository sortieStockRepository,
                                         ValidationRepository validationRepository,
                                         UniteEqREpository uniteEqREpository){
        return args -> {
            //valeurs de tests
            Produit p=new Produit("B002","biscuits",true);
            Produit p1=new Produit("T001","THB",false);
            Produit p2=new Produit("F003","Fromage",false);
            produitREpository.saveAll(List.of(p,p1,p2));
//            Magasin m=new Magasin(1L,"mag1");
//            Magasin m1=new Magasin(2L,"mag2");
//            magasinRepository.saveAll(List.of(m,m1));
//            //set unites d'equivalence
            UniteEquivalence u1= new UniteEquivalence(1L,p1,1,8,"cageot bierre");
            UniteEquivalence u2= new UniteEquivalence(2L,p,1,50,"sachet biscuit");
            UniteEquivalence u3= new UniteEquivalence(3L,p2,1,10,"carton fromage");
            uniteEqREpository.saveAll(List.of(u1,u2,u3));
//            List<Stock> s=new ArrayList<>();
//            s.add(new Stock(1L, LocalDateTime.now(),200,200,"SR001",m,u2));
//            s.add(new Stock(2L, LocalDateTime.now(),1000,200,"SR001",m,u2));
//            s.add(new Stock(3L, LocalDateTime.now(),2000,200,"SR001",m,u2));
//            s.add(new Stock(4L, LocalDateTime.now(),2500,200,"SR001",m,u2));
//            stockRepository.saveAll(s);
//            List<Mouvement_sortie> ms=new ArrayList<>();
//            Mouvement_sortie ms1=new Mouvement_sortie(1L, LocalDateTime.now(),100,0,p1,m);
//            ms.add(ms1);
//            ms.add(new Mouvement_sortie(2L,LocalDateTime.now(),300,0,p1,m));
////            ms.add(new Mouvement_sortie(3L,Timestamp.from(Instant.now()),300,0,p,m));
////            ms.add(new Mouvement_sortie(4L,Timestamp.from(Instant.now()),3000,0,p1,m));
////            ms.add(new Mouvement_sortie(5L,Timestamp.from(Instant.now()),300,0,p1,m));
////            ms.add(new Mouvement_sortie(6L,Timestamp.from(Instant.now()),300,0,p1,m));
////            mouvement_Sortie_Repository.saveAll(ms);
//
//
//            // eto no atao ny test rehetra aloha
//            Optional<Mouvement_sortie> mv=mouvement_Sortie_Repository.getDernierMouvementValide("SR001",1L);
//            if(mv.isPresent()){
//                System.out.println(mv);
//            }
////            List<SortieStock> sortie=mv.decompositionSorties(produitREpository,resteStockREpository,stockRepository);
////            sortie.forEach(System.out::println);
//
////            ms1.validerMouvement(produitREpository,resteStockREpository,stockRepository,sortieStockRepository,mouvement_Sortie_Repository,validationRepository);
////            System.out.println(mv.toString());
        };
    }
}
