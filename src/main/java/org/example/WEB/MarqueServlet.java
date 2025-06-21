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

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Gestion des Marques</title>");
        out.println(
                "    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println(
                "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
        out.println("    <style>");
        out.println("        body { background-color: #f8f9fa; }");
        out.println("        .card { border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }");
        out.println("        .table-responsive { border-radius: 8px; overflow: hidden; }");
        out.println("        .btn-action { width: 100px; }");
        out.println("        .form-container { background: white; border-radius: 10px; padding: 25px; }");
        out.println("        .brand-logo { width: 40px; height: 40px; background-color: #0d6efd; color: white; ");
        out.println("                     display: flex; align-items: center; justify-content: center; ");
        out.println("                     border-radius: 50%; margin-right: 10px; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container py-5'>");

        // Header
        out.println("    <div class='d-flex justify-content-between align-items-center mb-5'>");
        out.println("        <div class='d-flex align-items-center'>");
        out.println("            <div class='brand-logo'><i class='fas fa-tag'></i></div>");
        out.println("            <h1 class='mb-0'>Gestion des Marques</h1>");
        out.println("        </div>");
        out.println("        <a href='" + req.getContextPath() + "/' class='btn btn-outline-secondary'>");
        out.println("            <i class='fas fa-arrow-left me-2'></i>Retour");
        out.println("        </a>");
        out.println("    </div>");

        // Liste des marques
        List<Marque> marques = marqueService.listerMarques();
        out.println("    <div class='card mb-5'>");
        out.println("        <div class='card-header bg-primary text-white'>");
        out.println("            <h3 class='mb-0'><i class='fas fa-list me-2'></i>Liste des marques</h3>");
        out.println("        </div>");
        out.println("        <div class='card-body'>");

        if (marques.isEmpty()) {
            out.println("            <div class='alert alert-info'>Aucune marque enregistrée</div>");
        } else {
            out.println("            <div class='table-responsive'>");
            out.println("                <table class='table table-hover align-middle'>");
            out.println("                    <thead class='table-light'>");
            out.println("                        <tr>");
            out.println("                            <th>Nom</th>");
            out.println("                            <th>Origine</th>");
            out.println("                            <th class='text-end'>Actions</th>");
            out.println("                        </tr>");
            out.println("                    </thead>");
            out.println("                    <tbody>");

            for (Marque m : marques) {
                out.println("                        <tr>");
                out.println("                            <td><strong>" + m.getNom() + "</strong></td>");
                out.println("                            <td>" + m.getOrigine() + "</td>");
                out.println("                            <td class='text-end'>");
                out.println("                                <form method='post' action='" + req.getContextPath()
                        + "/marques/delete' class='d-inline'>");
                out.println("                                    <input type='hidden' name='nom' value='" + m.getNom()
                        + "'>");
                out.println(
                        "                                    <button type='submit' class='btn btn-danger btn-sm btn-action'>");
                out.println("                                        <i class='fas fa-trash-alt me-1'></i>Supprimer");
                out.println("                                    </button>");
                out.println("                                </form>");
                out.println("                            </td>");
                out.println("                        </tr>");
            }

            out.println("                    </tbody>");
            out.println("                </table>");
            out.println("            </div>");
        }
        out.println("        </div>");
        out.println("    </div>");

        // Formulaire d'ajout
        out.println("    <div class='row'>");
        out.println("        <div class='col-lg-6 mx-auto'>");
        out.println("            <div class='form-container card'>");
        out.println("                <div class='card-header bg-success text-white'>");
        out.println(
                "                    <h3 class='mb-0'><i class='fas fa-plus-circle me-2'></i>Ajouter une marque</h3>");
        out.println("                </div>");
        out.println("                <div class='card-body'>");
        out.println("                    <form method='post'>");
        out.println("                        <div class='mb-3'>");
        out.println("                            <label for='nom' class='form-label'>Nom de la marque</label>");
        out.println(
                "                            <input type='text' class='form-control' id='nom' name='nom' required>");
        out.println("                        </div>");
        out.println("                        <div class='mb-3'>");
        out.println("                            <label for='origine' class='form-label'>Origine</label>");
        out.println(
                "                            <input type='text' class='form-control' id='origine' name='origine' required>");
        out.println("                        </div>");
        out.println("                        <div class='d-grid'>");
        out.println("                            <button type='submit' class='btn btn-success'>");
        out.println("                                <i class='fas fa-save me-2'></i>Enregistrer");
        out.println("                            </button>");
        out.println("                        </div>");
        out.println("                    </form>");
        out.println("                </div>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </div>");

        out.println("</div>");

        // Scripts
        out.println(
                "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
        out.println("<script>");
        out.println("    // Confirmation avant suppression");
        out.println("    document.querySelectorAll('form[action*=\"delete\"]').forEach(form => {");
        out.println("        form.addEventListener('submit', function(e) {");
        out.println("            if (!confirm('Êtes-vous sûr de vouloir supprimer cette marque ?')) {");
        out.println("                e.preventDefault();");
        out.println("            }");
        out.println("        });");
        out.println("    });");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
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