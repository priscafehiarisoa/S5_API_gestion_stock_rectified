package com.example.g5_gestion_stock_2.repositories;

import com.example.g5_gestion_stock_2.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProduitREpository extends JpaRepository<Produit, String> {
    @Query("select s from Produit s where s.ref_produit=:ref")
    Optional<Produit> findByRef_produit(@Param("ref") String ref_produit);

    @Query(value = "select p from Produit p where p.ref_produit like '%' || :ref || '%'")
    List<Produit> getAllProduitByReferences(@Param("ref") String ref);
}
