package org.example.JPA;

import jakarta.persistence.*;

@Entity
public class ElementDeStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refProduit;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "stock_nom")
    private Stock stock;

    public Long getId() {
        return id;
    }

    public String getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(String refProduit) {
        this.refProduit = refProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
