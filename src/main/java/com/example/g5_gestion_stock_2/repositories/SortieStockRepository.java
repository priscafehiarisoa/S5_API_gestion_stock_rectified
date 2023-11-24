package com.example.g5_gestion_stock_2.repositories;

import com.example.g5_gestion_stock_2.model.SortieStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortieStockRepository extends JpaRepository<SortieStock,Long> {
}
