package org.example.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.example.JPA.ElementDeStock;

@Stateless
public class ElementDeStockDAO {

    @PersistenceContext(unitName = "monPU")
    private EntityManager em;

    public void save(ElementDeStock element) {
        if (element.getId() == null) {
            em.persist(element);
        } else {
            em.merge(element);
        }
    }

    public void delete(Long id) {
        ElementDeStock element = em.find(ElementDeStock.class, id);
        if (element != null) {
            em.remove(element);
        }
    }

    public ElementDeStock findById(Long id) {
        return em.find(ElementDeStock.class, id);
    }

    public List<ElementDeStock> findAll() {
        return em.createQuery("SELECT e FROM ElementDeStock e", ElementDeStock.class).getResultList();
    }

    public void update(ElementDeStock element) {
        em.merge(element);
    }
}
