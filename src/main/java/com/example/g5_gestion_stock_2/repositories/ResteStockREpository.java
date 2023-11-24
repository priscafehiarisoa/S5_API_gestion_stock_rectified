package com.example.g5_gestion_stock_2.repositories;

import com.example.g5_gestion_stock_2.view.ResteStockV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResteStockREpository extends JpaRepository<ResteStockV,Long> {
    @Query(value = "select rv from ResteStockV rv where rv.refProduit=:ref_Produit and rv.idMagasin=:idmagasin and rv.quantiteStock!=0 order by case when :order = 'ASC' THEN rv.dateEntreStock END ASC, CASE WHEN :order = 'DESC' THEN rv.dateEntreStock END DESC ,rv.idStock ")
    List<ResteStockV> findResteStock(@Param("order") String order, @Param("ref_Produit") String ref_Produit, @Param("idmagasin") Long idMagasin );

}
