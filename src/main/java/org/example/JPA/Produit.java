package org.example.JPA;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Produit implements Serializable {

    @Id
    private String reference;

    private String denomination;
    private double prix;
    private double poids;
    private double volume;

    @ManyToOne
    @JoinColumn(name = "nom_marque") // clé étrangère vers Marque
    private Marque marque;

    public Produit() {
    }

    public Produit(String reference, String denomination, double prix, double poids, double volume, Marque marque) {
        this.reference = reference;
        this.denomination = denomination;
        this.prix = prix;
        this.poids = poids;
        this.volume = volume;
        this.marque = marque;
    }

    // Getters & Setters
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
}
