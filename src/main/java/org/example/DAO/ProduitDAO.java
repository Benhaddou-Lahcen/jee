package org.example.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.example.JPA.Produit;

import java.util.List;

@Stateless
public class ProduitDAO {

    @PersistenceContext(unitName = "monPU")
    private EntityManager em;

    public void save(Produit produit) {
        if (produit.getId() == null) {
            em.persist(produit); // insertion
        } else {
            em.merge(produit);   // mise à jour
        }
    }
    // Supprimer un produit
    public void delete(Long id) {
        Produit p = em.find(Produit.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    // Rechercher un produit par ID
    public Produit findById(Long id) {
        return em.find(Produit.class, id);
    }

    // Récupérer tous les produits
    public List<Produit> findAll() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}
