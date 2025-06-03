package org.example.WEB;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.JPA.Produit;
import org.example.Service.ProduitService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listeProduits")
public class ListeProduitServlet extends HttpServlet {

    @Inject
    private ProduitService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Produit> produits = service.listerProduits();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h2>Liste des produits</h2>");
        out.println("<ul>");
        for (Produit p : produits) {
            out.println("<li>" + p.getNom() + " - " + p.getPrix() + " MAD</li>");
        }
        out.println("</ul>");
        out.println("<a href='ajouterProduit'>Ajouter un produit</a>");
        out.println("</body></html>");
    }
}