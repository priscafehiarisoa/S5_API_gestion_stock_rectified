package com.example.g5_gestion_stock_2.repositories;

import com.example.g5_gestion_stock_2.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {
    @Query(value = "SELECT\n" +
            "               s.id_stock,\n" +
            "               s.ref_produit,\n" +
            "               s.date_entre_stock,\n" +
            "               s.prix_unitaire,\n" +
            "               s.id_magasin,\n" +
            "               s.idequivalence,"+
            "               s.entreeprixreel,\n" +
            "               s.entreeproduitreel,    "+
            "               (s.quantite_entree - COALESCE(SUM(mv.quantite_sortie_stock), 0)) AS quantite_entree\n" +
            "        FROM\n" +
            "            stock AS s\n" +
            "                left JOIN\n" +
            "            (select * from mv_sorties_v where mv_sorties_v.date_mouvement_sortie<=:dates and mv_sorties_v.ref_produit=:refProduit and etat=10 and mv_sorties_v.id_magasin=:magasin) mv ON mv.id_stock = s.id_stock\n" +
            "        WHERE\n" +
            "                s.date_entre_stock <= :dates and s.ref_produit=:refProduit and s.id_magasin=:magasin\n" +
            "        GROUP BY\n" +
            "            s.id_stock",nativeQuery = true)
    List<Stock> getAllStock_mouvement(@Param("dates") Timestamp dates, @Param("refProduit") String refProduit, @Param("magasin") Long idMagasin);

    @Query (value="select coalesce(sum(mv.quantite_sortie_stock),0) from mv_sorties_v mv where mv.date_mouvement_sortie<=:dates and mv.ref_produit=:ref and id_magasin=:magasin",nativeQuery = true)
    double getSumSortiesStockByDate(@Param("dates") Timestamp dates, @Param("ref") String refProduit,@Param("magasin") Long idMagasin);

}
