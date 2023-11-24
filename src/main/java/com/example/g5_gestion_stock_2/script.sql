
create view mv_sorties_v as select id_sortie_stock,etat, mouvement_sortie.quantite_sortie,ss.quantite_sortie as quantite_sortie_stock, mouvement_sortie.id_mouvement_sortie,date_mouvement_sortie,id_magasin, id_stock, mouvement_sortie.ref_produit from mouvement_sortie left join sortie_stock ss on mouvement_sortie.id_mouvement_sortie = ss.id_mouvement_sortie

create view reste_stock_v as SELECT s.ref_produit, s.id_stock, (s.quantite_entree - coalesce(mouvement.quantite_sortie, 0)) as quantite_stock, s.id_magasin, s.unite,s.prix_unitaire,s.date_entre_stock
FROM Stock s
         LEFT JOIN (SELECT SUM(quantite_sortie_stock) as quantite_sortie, id_magasin, id_stock FROM mv_sorties_v where etat=10 GROUP BY id_stock, id_magasin) as mouvement
                   ON s.id_stock = mouvement.id_stock
-- ===============================
select * from reste_stock_v as rv where rv.ref_produit=:ref_Produit and rv.id_magasin=:idmagasin and rv.quantite_stock!=0 order by case when :order = 'ASC' THEN rv.date_entre_stock END ASC, CASE WHEN :order = 'DESC' THEN rv.date_entre_stock END DESC ,rv.id_stock

select * from reste_stock_v


                SELECT
               s.id_stock,
               s.ref_produit,
               s.date_entre_stock,
               s.prix_unitaire,
               s.id_magasin,
               s.unite,
               s.entreeprixreel,
               s.entreeproduitreel,
               (s.quantite_entree - COALESCE(SUM(mv.quantite_sortie_stock), 0)) AS quantite_stock
        FROM
            stock AS s
                left JOIN
            (select * from mv_sorties_v where mv_sorties_v.date_mouvement_sortie<=:dates and mv_sorties_v.ref_produit=:refProduit and etat=10 and mv_sorties_v.id_magasin=:magasin) mv ON mv.id_stock = s.id_stock
        WHERE
                s.date_entre_stock <= :dates and s.ref_produit=:refProduit and s.id_magasin=:magasin
        GROUP BY
            s.id_stock

                select * from mv_sorties_v

select coalesce(sum(mv.quantite_sortie_stock),0) from mv_sorties_v mv where mv.date_mouvement_sortie<=:dates and mv.ref_produit=:ref and id_magasin=:magasin

select * from reste_stock_v rv where rv.ref_produit=:ref_Produit and rv.id_magasin=:idmagasin and rv.quantite_stock!=0 order by case when :order = 'ASC' THEN rv.date_entre_stock END ASC, CASE WHEN :order = 'DESC' THEN rv.date_entre_stock END DESC ,rv.id_stock

select COALESCE(SUM(mv_sorties_v.quantite_sortie_stock), 0) from mv_sorties_v where mv_sorties_v.date_mouvement_sortie<=:dates and mv_sorties_v.ref_produit=:refProduit and etat=10 and mv_sorties_v.id_magasin=:magasin