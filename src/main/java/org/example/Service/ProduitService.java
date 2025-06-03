package org.example.Service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.example.DAO.ProduitDAO;
import org.example.JPA.Produit;

import java.util.List;

@Stateless
public class ProduitService {

    @Inject
    private ProduitDAO dao;
    public void ajouterProduit(String nom,double prix) {
        Produit produit=new Produit(nom,prix);
        dao.save(produit); // persist exécuté dans une transaction JTA
    }

    public void supprimerProduit(Long id) {
        dao.delete(id); // idem
    }

    public List<Produit> listerProduits() {
        return dao.findAll(); // opération de lecture => pas besoin de transaction explicite
    }
}
