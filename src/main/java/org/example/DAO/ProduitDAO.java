package org.example.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

import org.example.JPA.Produit;

@Stateless
public class ProduitDAO {

    @PersistenceContext(unitName = "monPU")
    private EntityManager em;

    public void create(Produit produit) {
        em.persist(produit);
    }

    public void delete(String reference) {
        Produit p = em.find(Produit.class, reference);
        if (p != null)
            em.remove(p);
    }

    public void update(Produit produit) {
        em.merge(produit);
    }

    public Produit findById(String reference) {
        return em.find(Produit.class, reference);
    }

    public List<Produit> findAll() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }

    public List<Produit> findByMarque(String nomMarque) {
        return em.createQuery("SELECT p FROM Produit p WHERE p.marque.nom = :nom", Produit.class)
                .setParameter("nom", nomMarque)
                .getResultList();
    }
}
