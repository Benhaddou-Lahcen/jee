package org.example.WEB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<div style='padding: 20px;'>");
        out.println("<h1>Tableau de bord</h1>");
        out.println("<p>Bienvenue dans votre application de gestion de stock</p>");

        out.println("<div style='display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; margin-top: 30px;'>");

        // Card Marques
        out.println(
                "<div style='background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);'>");
        out.println("<h3><i class='fas fa-tags'></i> Marques</h3>");
        out.println("<p>Gérez les marques de vos produits</p>");
        out.println("<button onclick=\"parent.loadContent('" + req.getContextPath()
                + "/marques')\" style='background: #3498db; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer;'>Accéder</button>");
        out.println("</div>");

        // Card Produits
        out.println(
                "<div style='background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);'>");
        out.println("<h3><i class='fas fa-box-open'></i> Produits</h3>");
        out.println("<p>Gérez votre catalogue de produits</p>");
        out.println("<button onclick=\"parent.loadContent('" + req.getContextPath()
                + "/produits')\" style='background: #2ecc71; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer;'>Accéder</button>");
        out.println("</div>");

        // Card Stocks
        out.println(
                "<div style='background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);'>");
        out.println("<h3><i class='fas fa-warehouse'></i> Stocks</h3>");
        out.println("<p>Suivez et gérez vos niveaux de stock</p>");
        out.println("<button onclick=\"parent.loadContent('" + req.getContextPath()
                + "/stocks')\" style='background: #e74c3c; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer;'>Accéder</button>");
        out.println("</div>");

        out.println("</div>");
        out.println("</div>");
    }
}
