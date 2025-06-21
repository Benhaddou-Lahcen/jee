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
import org.example.Service.MarqueService;

@WebServlet("/marques")
public class MarqueServlet extends HttpServlet {

    @EJB
    private MarqueService marqueService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Gestion des Marques</h1>");

        // Lister toutes les marques
        List<Marque> marques = marqueService.listerMarques();
        out.println("<h2>Liste des marques</h2>");
        out.println("<ul>");
        for (Marque m : marques) {
            out.println("<li>" + m.getNom() + " - " + m.getOrigine() + "</li>");
        }
        out.println("</ul>");

        // Formulaire pour ajouter une marque
        out.println("<h2>Ajouter une marque</h2>");
        out.println("<form method='post'>");
        out.println("Nom: <input type='text' name='nom'><br>");
        out.println("Origine: <input type='text' name='origine'><br>");
        out.println("<input type='submit' value='Ajouter'>");
        out.println("</form>");

        // Formulaire pour supprimer une marque
        out.println("<h2>Supprimer une marque</h2>");
        out.println("<form method='post' action='" + req.getContextPath() + "/marques/delete'>");
        out.println("Nom: <input type='text' name='nom'><br>");
        out.println("<input type='submit' value='Supprimer'>");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String origine = req.getParameter("origine");

        if (nom != null && origine != null) {
            marqueService.creerMarque(nom, origine);
        }
        resp.sendRedirect(req.getContextPath() + "/marques");
    }
}

@WebServlet("/marques/delete")
class DeleteMarqueServlet extends HttpServlet {

    @EJB
    private MarqueService marqueService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");

        if (nom != null) {
            marqueService.supprimerMarque(nom);
        }
        resp.sendRedirect(req.getContextPath() + "/marques");
    }
}