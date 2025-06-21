package org.example.WEB;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.example.JPA.Marque;
import org.example.JPA.Produit;
import org.example.Service.MarqueService;
import org.example.Service.ProduitService;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    @EJB
    private ProduitService produitService;

    @EJB
    private MarqueService marqueService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Gestion des Produits</h1>");

        // Lister tous les produits
        List<Produit> produits = produitService.listerProduits();
        out.println("<h2>Liste des produits</h2>");
        out.println("<ul>");
        for (Produit p : produits) {
            out.println("<li>" + p.getReference() + " - " + p.getDenomination() + " - " + p.getPrix() + "€</li>");
        }
        out.println("</ul>");

        // Formulaire pour ajouter un produit
        out.println("<h2>Ajouter un produit</h2>");
        out.println("<form method='post'>");
        out.println("Référence: <input type='text' name='reference'><br>");
        out.println("Dénomination: <input type='text' name='denomination'><br>");
        out.println("Prix: <input type='number' step='0.01' name='prix'><br>");
        out.println("Poids: <input type='number' step='0.01' name='poids'><br>");
        out.println("Volume: <input type='number' step='0.01' name='volume'><br>");

        // Liste déroulante pour les marques
        out.println("Marque: <select name='nomMarque'>");
        for (Marque m : marqueService.listerMarques()) {
            out.println("<option value='" + m.getNom() + "'>" + m.getNom() + "</option>");
        }
        out.println("</select><br>");

        out.println("<input type='submit' value='Ajouter'>");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reference = req.getParameter("reference");
        String denomination = req.getParameter("denomination");
        double prix = Double.parseDouble(req.getParameter("prix"));
        double poids = Double.parseDouble(req.getParameter("poids"));
        double volume = Double.parseDouble(req.getParameter("volume"));
        String nomMarque = req.getParameter("nomMarque");

        Marque marque = marqueService.listerMarques().stream()
                .filter(m -> m.getNom().equals(nomMarque))
                .findFirst()
                .orElse(null);

        if (marque != null) {
            Produit produit = new Produit(reference, denomination, prix, poids, volume, marque);
            produitService.creerProduit(produit);
        }
        resp.sendRedirect(req.getContextPath() + "/produits");
    }
}