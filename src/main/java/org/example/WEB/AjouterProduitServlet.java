package org.example.WEB;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.Service.ProduitService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajouterProduit")
public class AjouterProduitServlet extends HttpServlet {

    @Inject
    private ProduitService service;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<form method='post'>");
        out.println("Nom du produit: <input name='nom' /><br/>");
        out.println("Prix: <input name='prix' /><br/>");
        out.println("<input type='submit' value='Ajouter' />");
        out.println("</form>");
        out.println("</body></html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nom = req.getParameter("nom");
        double prix = Double.parseDouble(req.getParameter("prix"));
        service.ajouterProduit(nom, prix);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("Produit Ajouté");
        out.println("<form method='get'>");
        out.println("<input type='submit' value='Retour' />");
        out.println("</form>");
        out.println("</body></html>");
    }
}