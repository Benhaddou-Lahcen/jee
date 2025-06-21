package org.example.Service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

import org.example.DAO.ProduitDAO;
import org.example.JPA.Produit;

@Stateless
public class ProduitService {

    @Inject
    private ProduitDAO produitDAO;

    // Création d’un nouveau produit
    public boolean creerProduit(Produit produit) {
        if (produitDAO.findById(produit.getReference()) != null) {
            return false; // le produit existe déjà
        }
        produitDAO.create(produit);
        return true;
    }

    // Suppression d’un produit
    public boolean supprimerProduit(String reference) {
        if (produitDAO.findById(reference) == null) {
            return false; // le produit n'existe pas
        }
        produitDAO.delete(reference);
        return true;
    }

    // Modification d’un produit
    public boolean modifierProduit(Produit produit) {
        if (produitDAO.findById(produit.getReference()) == null) {
            return false; // le produit n'existe pas
        }
        produitDAO.update(produit);
        return true;
    }

    // Liste de tous les produits
    public List<Produit> listerProduits() {
        return produitDAO.findAll();
    }

    // Liste des produits par marque
    public List<Produit> listerProduitsParMarque(String nomMarque) {
        return produitDAO.findByMarque(nomMarque);
    }
}
