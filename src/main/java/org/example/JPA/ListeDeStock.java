package org.example.JPA;

import java.util.ArrayList;
import java.util.List;

public class ListeDeStock {

    private List<ElementDeStock> liste = new ArrayList<>();

    public List<ElementDeStock> getListe() {
        return liste;
    }

    public void setListe(List<ElementDeStock> liste) {
        this.liste = liste;
    }

    public void ajouter(ElementDeStock e) {
        liste.add(e);
    }

    public ElementDeStock rechercherParRef(String refProduit) {
        return liste.stream()
                .filter(e -> refProduit.equals(e.getRefProduit()))
                .findFirst()
                .orElse(null);
    }
}
