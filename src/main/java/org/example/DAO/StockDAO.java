
package org.example.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.example.JPA.Stock;

@Stateless
public class StockDAO {

    @PersistenceContext(unitName = "monPU")
    private EntityManager em;

    public void save(Stock stock) {
        if (stock.getNom() == null) {
            em.persist(stock); // insertion
        } else {
            em.merge(stock); // mise à jour
        }
    }

    public void delete(String nom) {
        Stock stock = em.find(Stock.class, nom);
        if (stock != null) {
            em.remove(stock);
        }
    }

    public Stock findByNom(String nom) {
        return em.find(Stock.class, nom);
    }

    public List<Stock> findAll() {
        return em.createQuery("SELECT s FROM Stock s", Stock.class).getResultList();
    }

    public void update(Stock stock) {
        em.merge(stock);
    }
}
