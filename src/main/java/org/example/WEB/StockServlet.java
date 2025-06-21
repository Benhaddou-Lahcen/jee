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

import org.example.JPA.ElementDeStock;
import org.example.Service.ServiceStock;

@WebServlet("/stocks")
public class StockServlet extends HttpServlet {

    @EJB
    private ServiceStock stockService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Gestion des Stocks</h1>");

        // Formulaire pour créer un stock
        out.println("<h2>Créer un stock</h2>");
        out.println("<form method='post' action='" + req.getContextPath() + "/stocks/create'>");
        out.println("Nom: <input type='text' name='nom'><br>");
        out.println("<input type='submit' value='Créer'>");
        out.println("</form>");

        // Formulaire pour entrée en stock
        out.println("<h2>Entrée en stock</h2>");
        out.println("<form method='post' action='" + req.getContextPath() + "/stocks/entree'>");
        out.println("Nom du stock: <input type='text' name='nomStock'><br>");
        out.println("Référence produit: <input type='text' name='refProduit'><br>");
        out.println("Quantité: <input type='number' name='quantite'><br>");
        out.println("<input type='submit' value='Ajouter'>");
        out.println("</form>");

        // Formulaire pour sortie de stock
        out.println("<h2>Sortie de stock</h2>");
        out.println("<form method='post' action='" + req.getContextPath() + "/stocks/sortie'>");
        out.println("Nom du stock: <input type='text' name='nomStock'><br>");
        out.println("Référence produit: <input type='text' name='refProduit'><br>");
        out.println("Quantité: <input type='number' name='quantite'><br>");
        out.println("<input type='submit' value='Retirer'>");
        out.println("</form>");

        // Consulter un stock
        out.println("<h2>Consulter un stock</h2>");
        out.println("<form method='get' action='" + req.getContextPath() + "/stocks/consulter'>");
        out.println("Nom du stock: <input type='text' name='nomStock'><br>");
        out.println("<input type='submit' value='Consulter'>");
        out.println("</form>");

        out.println("</body></html>");
    }
}

@WebServlet("/stocks/create")
class CreateStockServlet extends HttpServlet {

    @EJB
    private ServiceStock stockService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");

        if (nom != null) {
            stockService.creerStock(nom);
        }
        resp.sendRedirect(req.getContextPath() + "/stocks");
    }
}

@WebServlet("/stocks/entree")
class EntreeStockServlet extends HttpServlet {

    @EJB
    private ServiceStock stockService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomStock = req.getParameter("nomStock");
        String refProduit = req.getParameter("refProduit");
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        stockService.entreeStock(nomStock, refProduit, quantite);
        resp.sendRedirect(req.getContextPath() + "/stocks");
    }
}

@WebServlet("/stocks/sortie")
class SortieStockServlet extends HttpServlet {

    @EJB
    private ServiceStock stockService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomStock = req.getParameter("nomStock");
        String refProduit = req.getParameter("refProduit");
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        stockService.sortieStock(nomStock, refProduit, quantite);
        resp.sendRedirect(req.getContextPath() + "/stocks");
    }
}

@WebServlet("/stocks/consulter")
class ConsulterStockServlet extends HttpServlet {

    @EJB
    private ServiceStock stockService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomStock = req.getParameter("nomStock");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Stock: " + nomStock + "</h1>");

        List<ElementDeStock> elements = stockService.consulterStock(nomStock);
        out.println("<ul>");
        for (ElementDeStock e : elements) {
            out.println("<li>" + e.getRefProduit() + " - Quantité: " + e.getQuantite() + "</li>");
        }
        out.println("</ul>");

        out.println("<a href='" + req.getContextPath() + "/stocks'>Retour</a>");
        out.println("</body></html>");
    }
}