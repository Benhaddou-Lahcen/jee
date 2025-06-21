package org.example.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.example.JPA.Marque;

@Stateless
public class MarqueDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Marque marque) {
        em.persist(marque);
    }

    public Marque find(String nom) {
        return em.find(Marque.class, nom);
    }

    public void delete(String nom) {
        Marque m = em.find(Marque.class, nom);
        if (m != null)
            em.remove(m);
    }

    public List<Marque> findAll() {
        return em.createQuery("SELECT m FROM Marque m", Marque.class).getResultList();
    }
}
