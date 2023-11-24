package com.example.g5_gestion_stock_2.repositories;

import com.example.g5_gestion_stock_2.model.Mouvement_sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface Mouvement_Sortie_Repository extends JpaRepository<Mouvement_sortie,Long> {

    @Query(value = "select * from mouvement_sortie where etat=10 order by date_mouvement_sortie desc , id_mouvement_sortie desc limit 1", nativeQuery = true)
    Optional<Mouvement_sortie> getDernierMouvementValide(String ref_produit, Long idMagasin);

    @Modifying
    @Transactional
    @Query(value = "update Mouvement_sortie p set p.etat=10 where p.id = :id")
    void validerMouvementSortie(@Param("id") Long id);

    @Query("select s from Mouvement_sortie s where s.etat=10")
    List<Mouvement_sortie> getMouvementValide ();
    @Query("select s from Mouvement_sortie s where s.etat=-10")
    List<Mouvement_sortie> getMouvementN0nValide ();
    @Query("select s from Mouvement_sortie s where s.etat=0")
    List<Mouvement_sortie> getMouvementEnAttente ();

    @Query ("select coalesce(sum(s.quantite_sortie),0) from Mouvement_sortie s where s.etat=0")
    double sommeMouvementNonVAlide(String refProduit , Long magasin);


}
