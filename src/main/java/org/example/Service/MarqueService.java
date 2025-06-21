package org.example.Service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

import org.example.DAO.MarqueDAO;
import org.example.DAO.ProduitDAO;
import org.example.JPA.Marque;
import org.example.JPA.Produit;

@Stateless
public class MarqueService {

    @Inject
    private MarqueDAO marqueDAO;

    @Inject
    private ProduitDAO produitDAO;

    public boolean creerMarque(String nom, String origine) {
        if (marqueDAO.find(nom) != null)
            return false;
        Marque m = new Marque();
        m.setNom(nom);
        m.setOrigine(origine);
        marqueDAO.create(m);
        return true;
    }

    public boolean supprimerMarque(String nom) {
        if (marqueDAO.find(nom) == null)
            return false;
        marqueDAO.delete(nom);
        return true;
    }

    public List<Marque> listerMarques() {
        return marqueDAO.findAll();
    }

    public boolean ajouterProduitAMarque(String nomMarque, Produit p) {
        Marque marque = marqueDAO.find(nomMarque);
        if (marque == null)
            return false;
        p.setMarque(marque);
        produitDAO.create(p);
        return true;
    }
}
