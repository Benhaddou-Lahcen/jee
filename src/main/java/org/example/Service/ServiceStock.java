package org.example.Service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

import org.example.DAO.ElementDeStockDAO;
import org.example.DAO.StockDAO;
import org.example.JPA.ElementDeStock;
import org.example.JPA.ListeDeStock;
import org.example.JPA.Stock;

@Stateless
public class ServiceStock {

    @Inject
    private StockDAO stockDAO;

    @Inject
    private ElementDeStockDAO elementDAO;

    public boolean creerStock(String nom) {
        if (stockDAO.findByNom(nom) != null)
            return false;

        Stock stock = new Stock();
        stock.setNom(nom);
        stockDAO.save(stock);
        return true;
    }

    public boolean entreeStock(String nomStock, String refProduit, int quantite) {
        if (quantite <= 0)
            return false;

        Stock stock = stockDAO.findByNom(nomStock);
        if (stock == null)
            return false;

        ListeDeStock listeDeStock = stock.getListeDeStock();
        ElementDeStock element = listeDeStock.rechercherParRef(refProduit);

        if (element == null) {
            element = new ElementDeStock();
            element.setRefProduit(refProduit);
            element.setQuantite(quantite);
            element.setStock(stock);
            listeDeStock.ajouter(element);
            elementDAO.save(element);
        } else {
            element.setQuantite(element.getQuantite() + quantite);
            elementDAO.update(element);
        }

        stockDAO.update(stock);
        return true;
    }

    public boolean sortieStock(String nomStock, String refProduit, int quantite) {
        if (quantite <= 0)
            return false;

        Stock stock = stockDAO.findByNom(nomStock);
        if (stock == null)
            return false;

        ListeDeStock listeDeStock = stock.getListeDeStock();
        ElementDeStock element = listeDeStock.rechercherParRef(refProduit);

        if (element == null || element.getQuantite() < quantite)
            return false;

        int nouvelleQuantite = element.getQuantite() - quantite;

        if (nouvelleQuantite == 0) {
            listeDeStock.getListe().remove(element);
            elementDAO.delete(element.getId());
        } else {
            element.setQuantite(nouvelleQuantite);
            elementDAO.update(element);
        }

        stockDAO.update(stock);
        return true;
    }

    // Modifier la quantité d’un produit
    public boolean modifierQuantite(String nomStock, String refProduit, int nouvelleQuantite) {
        if (nouvelleQuantite < 0)
            return false;

        Stock stock = stockDAO.findByNom(nomStock);
        if (stock == null)
            return false;

        ListeDeStock listeDeStock = stock.getListeDeStock();
        ElementDeStock element = listeDeStock.rechercherParRef(refProduit);

        if (element == null) {
            if (nouvelleQuantite == 0)
                return true;

            element = new ElementDeStock();
            element.setRefProduit(refProduit);
            element.setQuantite(nouvelleQuantite);
            element.setStock(stock);
            listeDeStock.ajouter(element);
            elementDAO.save(element);
        } else {
            if (nouvelleQuantite == 0) {
                listeDeStock.getListe().remove(element);
                elementDAO.delete(element.getId());
            } else {
                element.setQuantite(nouvelleQuantite);
                elementDAO.update(element);
            }
        }

        stockDAO.update(stock);
        return true;
    }

    public List<ElementDeStock> consulterStock(String nomStock) {
        Stock stock = stockDAO.findByNom(nomStock);
        if (stock == null)
            return List.of();

        return stock.getListeDeStock().getListe();
    }

    public List<ElementDeStock> consulterStockGlobal() {
        return elementDAO.findAll();
    }
}
