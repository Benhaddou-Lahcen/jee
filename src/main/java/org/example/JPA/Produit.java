package org.example.JPA;

import jakarta.persistence.*;
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private double prix;
    public Produit() {
    }
    public Produit(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }
    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
}
