package org.example.JPA;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Marque implements Serializable {

    @Id
    private String nom;

    private String origine;

    @OneToMany(mappedBy = "marque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> listeProduits;

    public Marque() {
    }

    public Marque(String nom, String origine) {
        this.nom = nom;
        this.origine = origine;
    }

    // Getters & Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public List<Produit> getListeProduits() {
        return listeProduits;
    }

    public void setListeProduits(List<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }
}
