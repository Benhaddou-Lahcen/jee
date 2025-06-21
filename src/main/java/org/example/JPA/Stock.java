package org.example.JPA;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stock {

    @Id
    private String nom;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementDeStock> listeElements = new ArrayList<>();

    @Transient
    private ListeDeStock listeDeStock;

    public Stock() {
        this.listeDeStock = new ListeDeStock();
        this.listeDeStock.setListe(listeElements);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ElementDeStock> getListeElements() {
        return listeElements;
    }

    public void setListeElements(List<ElementDeStock> listeElements) {
        this.listeElements = listeElements;
        if (listeDeStock == null) {
            listeDeStock = new ListeDeStock();
        }
        this.listeDeStock.setListe(listeElements);
    }

    public ListeDeStock getListeDeStock() {
        if (listeDeStock == null) {
            listeDeStock = new ListeDeStock();
            listeDeStock.setListe(listeElements);
        }
        return listeDeStock;
    }
}
