package com.example.g5_gestion_stock_2.model;

import java.sql.Timestamp;
import java.util.List;

public class EtatDeStock {
    List<DetailEtatDeStock> detailEtatDeStocks;
    Timestamp dateDebut;
    Timestamp dateFin;
    double montantTotal;
    UniteEquivalence uniteEquivalence;

    public EtatDeStock(List<DetailEtatDeStock> detailEtatDeStocks, Timestamp dateDebut, Timestamp dateFin, double montantTotal) {
        setDetailEtatDeStocks(detailEtatDeStocks);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setMontantTotal(montantTotal);
    }

    public EtatDeStock(List<DetailEtatDeStock> detailEtatDeStocks) {
        setDetailEtatDeStocks(detailEtatDeStocks);
        setDateDebut();
        setDateFin();
        setMontantTotal();
    }

    public UniteEquivalence getUniteEquivalence() {
        return uniteEquivalence;
    }

    public void setUniteEquivalence(UniteEquivalence uniteEquivalence) {
        this.uniteEquivalence = uniteEquivalence;
    }

    public List<DetailEtatDeStock> getDetailEtatDeStocks() {
        return detailEtatDeStocks;
    }

    public void setDetailEtatDeStocks(List<DetailEtatDeStock> detailEtatDeStocks) {
        this.detailEtatDeStocks = detailEtatDeStocks;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateDebut() {
        if(!getDetailEtatDeStocks().isEmpty()){
            setDateDebut(getDetailEtatDeStocks().get(0).getDateDebut());
        }
        else{
            setDateDebut(null);
        }
    }


    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
    public void setDateFin() {
        if(!getDetailEtatDeStocks().isEmpty()){
            setDateFin(getDetailEtatDeStocks().get(0).getDateFin());
        }
        else{
            setDateFin(null);
        }
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
    public void setMontantTotal() {
        if(!getDetailEtatDeStocks().isEmpty()){
            double montant =0;
            for (int i = 0; i < getDetailEtatDeStocks().size(); i++) {
                montant+=getDetailEtatDeStocks().get(i).getMontant();
            }
            setMontantTotal(montant);
        }
        else{
            setMontantTotal(0);
        }
    }
}
