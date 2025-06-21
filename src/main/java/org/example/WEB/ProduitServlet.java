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

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Gestion des Produits</title>");
        out.println(
                "    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println(
                "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
        out.println("    <style>");
        out.println("        body { background-color: #f8f9fa; padding-top: 20px; }");
        out.println(
                "        .card { border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); margin-bottom: 30px; }");
        out.println("        .table-responsive { border-radius: 8px; overflow: hidden; }");
        out.println("        .product-img { width: 50px; height: 50px; object-fit: cover; border-radius: 4px; }");
        out.println("        .form-container { background: white; border-radius: 10px; padding: 25px; }");
        out.println(
                "        .header-container { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }");
        out.println("        .price-badge { font-size: 0.9rem; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");

        // Header
        out.println("    <div class='header-container'>");
        out.println("        <div>");
        out.println("            <h1><i class='fas fa-box-open me-2'></i>Gestion des Produits</h1>");
        out.println("            <p class='text-muted'>Gérez votre catalogue de produits</p>");
        out.println("        </div>");
        out.println("        <a href='" + req.getContextPath() + "/' class='btn btn-outline-secondary'>");
        out.println("            <i class='fas fa-arrow-left me-2'></i>Retour");
        out.println("        </a>");
        out.println("    </div>");

        // Liste des produits
        List<Produit> produits = produitService.listerProduits();
        out.println("    <div class='card'>");
        out.println("        <div class='card-header bg-primary text-white'>");
        out.println("            <h3 class='mb-0'><i class='fas fa-list me-2'></i>Liste des produits</h3>");
        out.println("        </div>");
        out.println("        <div class='card-body'>");

        if (produits.isEmpty()) {
            out.println("            <div class='alert alert-info'>Aucun produit enregistré</div>");
        } else {
            out.println("            <div class='table-responsive'>");
            out.println("                <table class='table table-hover align-middle'>");
            out.println("                    <thead class='table-light'>");
            out.println("                        <tr>");
            out.println("                            <th>Référence</th>");
            out.println("                            <th>Dénomination</th>");
            out.println("                            <th>Marque</th>");
            out.println("                            <th class='text-end'>Prix</th>");
            out.println("                            <th class='text-end'>Actions</th>");
            out.println("                        </tr>");
            out.println("                    </thead>");
            out.println("                    <tbody>");

            for (Produit p : produits) {
                out.println("                        <tr>");
                out.println("                            <td><strong>" + p.getReference() + "</strong></td>");
                out.println("                            <td>" + p.getDenomination() + "</td>");
                out.println("                            <td>"
                        + (p.getMarque() != null ? p.getMarque().getNom() : "N/A") + "</td>");
                out.println("                            <td class='text-end'>");
                out.println("                                <span class='badge bg-success price-badge'>"
                        + String.format("%.2f", p.getPrix()) + " €</span>");
                out.println("                            </td>");
                out.println("                            <td class='text-end'>");
                out.println("                                <a href='" + req.getContextPath() + "/produits/edit?ref="
                        + p.getReference() + "' class='btn btn-sm btn-warning me-2'>");
                out.println("                                    <i class='fas fa-edit me-1'></i>Modifier");
                out.println("                                </a>");
                out.println("                                <form method='post' action='" + req.getContextPath()
                        + "/produits/delete' class='d-inline'>");
                out.println("                                    <input type='hidden' name='reference' value='"
                        + p.getReference() + "'>");
                out.println("                                    <button type='submit' class='btn btn-sm btn-danger'>");
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
        out.println("        <div class='col-lg-8 mx-auto'>");
        out.println("            <div class='form-container card'>");
        out.println("                <div class='card-header bg-success text-white'>");
        out.println(
                "                    <h3 class='mb-0'><i class='fas fa-plus-circle me-2'></i>Ajouter un produit</h3>");
        out.println("                </div>");
        out.println("                <div class='card-body'>");
        out.println("                    <form method='post'>");
        out.println("                        <div class='row'>");
        out.println("                            <div class='col-md-6 mb-3'>");
        out.println("                                <label for='reference' class='form-label'>Référence</label>");
        out.println(
                "                                <input type='text' class='form-control' id='reference' name='reference' required>");
        out.println("                            </div>");
        out.println("                            <div class='col-md-6 mb-3'>");
        out.println(
                "                                <label for='denomination' class='form-label'>Dénomination</label>");
        out.println(
                "                                <input type='text' class='form-control' id='denomination' name='denomination' required>");
        out.println("                            </div>");
        out.println("                        </div>");
        out.println("                        <div class='row'>");
        out.println("                            <div class='col-md-4 mb-3'>");
        out.println("                                <label for='prix' class='form-label'>Prix (€)</label>");
        out.println(
                "                                <input type='number' step='0.01' class='form-control' id='prix' name='prix' required>");
        out.println("                            </div>");
        out.println("                            <div class='col-md-4 mb-3'>");
        out.println("                                <label for='poids' class='form-label'>Poids (kg)</label>");
        out.println(
                "                                <input type='number' step='0.01' class='form-control' id='poids' name='poids' required>");
        out.println("                            </div>");
        out.println("                            <div class='col-md-4 mb-3'>");
        out.println("                                <label for='volume' class='form-label'>Volume (m³)</label>");
        out.println(
                "                                <input type='number' step='0.01' class='form-control' id='volume' name='volume' required>");
        out.println("                            </div>");
        out.println("                        </div>");
        out.println("                        <div class='mb-3'>");
        out.println("                            <label for='nomMarque' class='form-label'>Marque</label>");
        out.println(
                "                            <select class='form-select' id='nomMarque' name='nomMarque' required>");
        out.println(
                "                                <option value='' selected disabled>Sélectionnez une marque</option>");

        for (Marque m : marqueService.listerMarques()) {
            out.println(
                    "                                <option value='" + m.getNom() + "'>" + m.getNom() + "</option>");
        }

        out.println("                            </select>");
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
        out.println("            if (!confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')) {");
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

@WebServlet("/produits/delete")
class DeleteProduitServlet extends HttpServlet {

    @EJB
    private ProduitService produitService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reference = req.getParameter("reference");

        if (reference != null) {
            produitService.supprimerProduit(reference);
        }
        resp.sendRedirect(req.getContextPath() + "/produits");
    }
}

@WebServlet("/produits/edit")
class EditProduitServlet extends HttpServlet {

    @EJB
    private ProduitService produitService;

    @EJB
    private MarqueService marqueService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reference = req.getParameter("ref");
        Produit produit = produitService.listerProduits().stream()
                .filter(p -> p.getReference().equals(reference))
                .findFirst()
                .orElse(null);

        if (produit == null) {
            resp.sendRedirect(req.getContextPath() + "/produits");
            return;
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Modifier Produit</title>");
        out.println(
                "    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println(
                "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
        out.println("    <style>");
        out.println("        body { background-color: #f8f9fa; padding-top: 20px; }");
        out.println(
                "        .form-container { background: white; border-radius: 10px; padding: 25px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }");
        out.println(
                "        .header-container { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");

        // Header
        out.println("    <div class='header-container'>");
        out.println("        <div>");
        out.println("            <h1><i class='fas fa-edit me-2'></i>Modifier Produit</h1>");
        out.println("            <p class='text-muted'>Modifiez les informations du produit</p>");
        out.println("        </div>");
        out.println("        <a href='" + req.getContextPath() + "/produits' class='btn btn-outline-secondary'>");
        out.println("            <i class='fas fa-arrow-left me-2'></i>Retour");
        out.println("        </a>");
        out.println("    </div>");

        // Formulaire d'édition
        out.println("    <div class='row justify-content-center'>");
        out.println("        <div class='col-lg-8'>");
        out.println("            <div class='form-container'>");
        out.println("                <form method='post' action='" + req.getContextPath() + "/produits/update'>");
        out.println("                    <input type='hidden' name='originalReference' value='" + produit.getReference()
                + "'>");

        out.println("                    <div class='row mb-3'>");
        out.println("                        <div class='col-md-6'>");
        out.println("                            <label for='reference' class='form-label'>Référence</label>");
        out.println(
                "                            <input type='text' class='form-control' id='reference' name='reference' value='"
                        + produit.getReference() + "' required>");
        out.println("                        </div>");
        out.println("                        <div class='col-md-6'>");
        out.println("                            <label for='denomination' class='form-label'>Dénomination</label>");
        out.println(
                "                            <input type='text' class='form-control' id='denomination' name='denomination' value='"
                        + produit.getDenomination() + "' required>");
        out.println("                        </div>");
        out.println("                    </div>");

        out.println("                    <div class='row mb-3'>");
        out.println("                        <div class='col-md-4'>");
        out.println("                            <label for='prix' class='form-label'>Prix (€)</label>");
        out.println(
                "                            <input type='number' step='0.01' class='form-control' id='prix' name='prix' value='"
                        + produit.getPrix() + "' required>");
        out.println("                        </div>");
        out.println("                        <div class='col-md-4'>");
        out.println("                            <label for='poids' class='form-label'>Poids (kg)</label>");
        out.println(
                "                            <input type='number' step='0.01' class='form-control' id='poids' name='poids' value='"
                        + produit.getPoids() + "' required>");
        out.println("                        </div>");
        out.println("                        <div class='col-md-4'>");
        out.println("                            <label for='volume' class='form-label'>Volume (m³)</label>");
        out.println(
                "                            <input type='number' step='0.01' class='form-control' id='volume' name='volume' value='"
                        + produit.getVolume() + "' required>");
        out.println("                        </div>");
        out.println("                    </div>");

        out.println("                    <div class='mb-3'>");
        out.println("                        <label for='nomMarque' class='form-label'>Marque</label>");
        out.println("                        <select class='form-select' id='nomMarque' name='nomMarque' required>");

        List<Marque> marques = marqueService.listerMarques();
        for (Marque m : marques) {
            String selected = (produit.getMarque() != null && produit.getMarque().getNom().equals(m.getNom()))
                    ? "selected"
                    : "";
            out.println("                            <option value='" + m.getNom() + "' " + selected + ">" + m.getNom()
                    + "</option>");
        }

        out.println("                        </select>");
        out.println("                    </div>");

        out.println("                    <div class='d-grid gap-2 d-md-flex justify-content-md-end'>");
        out.println("                        <a href='" + req.getContextPath()
                + "/produits' class='btn btn-secondary me-md-2'>");
        out.println("                            <i class='fas fa-times me-1'></i>Annuler");
        out.println("                        </a>");
        out.println("                        <button type='submit' class='btn btn-primary'>");
        out.println("                            <i class='fas fa-save me-1'></i>Enregistrer");
        out.println("                        </button>");
        out.println("                    </div>");
        out.println("                </form>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");

        out.println(
                "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
        out.println("</body>");
        out.println("</html>");
    }
}

@WebServlet("/produits/update")
class UpdateProduitServlet extends HttpServlet {

    @EJB
    private ProduitService produitService;

    @EJB
    private MarqueService marqueService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String originalReference = req.getParameter("originalReference");
        String reference = req.getParameter("reference");
        String denomination = req.getParameter("denomination");
        double prix = Double.parseDouble(req.getParameter("prix"));
        double poids = Double.parseDouble(req.getParameter("poids"));
        double volume = Double.parseDouble(req.getParameter("volume"));
        String nomMarque = req.getParameter("nomMarque");

        // Récupérer le produit existant
        Produit produit = produitService.listerProduits().stream()
                .filter(p -> p.getReference().equals(originalReference))
                .findFirst()
                .orElse(null);

        if (produit == null) {
            resp.sendRedirect(req.getContextPath() + "/produits");
            return;
        }

        // Récupérer la marque
        Marque marque = marqueService.listerMarques().stream()
                .filter(m -> m.getNom().equals(nomMarque))
                .findFirst()
                .orElse(null);

        if (marque != null) {
            // Mettre à jour les propriétés du produit
            produit.setReference(reference);
            produit.setDenomination(denomination);
            produit.setPrix(prix);
            produit.setPoids(poids);
            produit.setVolume(volume);
            produit.setMarque(marque);

            // Mettre à jour le produit
            produitService.modifierProduit(produit);
        }

        resp.sendRedirect(req.getContextPath() + "/produits");
    }
}