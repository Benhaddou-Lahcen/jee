package org.example.WEB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Gestion de Stock</title>");
        out.println("    <style>");
        out.println("        :root {");
        out.println("            --sidebar-width: 250px;");
        out.println("            --primary-color: #3498db;");
        out.println("            --secondary-color: #2ecc71;");
        out.println("            --danger-color: #e74c3c;");
        out.println("            --dark-color: #2c3e50;");
        out.println("            --light-color: #ecf0f1;");
        out.println("        }");
        out.println("        * {");
        out.println("            margin: 0;");
        out.println("            padding: 0;");
        out.println("            box-sizing: border-box;");
        out.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        out.println("        }");
        out.println("        body {");
        out.println("            display: flex;");
        out.println("            min-height: 100vh;");
        out.println("            background-color: #f5f5f5;");
        out.println("        }");
        out.println("        .sidebar {");
        out.println("            width: var(--sidebar-width);");
        out.println("            background-color: var(--dark-color);");
        out.println("            color: white;");
        out.println("            padding: 20px 0;");
        out.println("            height: 100vh;");
        out.println("            position: fixed;");
        out.println("        }");
        out.println("        .sidebar-header {");
        out.println("            padding: 0 20px 20px;");
        out.println("            border-bottom: 1px solid rgba(255,255,255,0.1);");
        out.println("        }");
        out.println("        .sidebar-nav {");
        out.println("            padding: 20px 0;");
        out.println("        }");
        out.println("        .nav-item {");
        out.println("            padding: 10px 20px;");
        out.println("            transition: all 0.3s;");
        out.println("        }");
        out.println("        .nav-item:hover {");
        out.println("            background-color: rgba(255,255,255,0.1);");
        out.println("        }");
        out.println("        .nav-item a {");
        out.println("            color: white;");
        out.println("            text-decoration: none;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            gap: 10px;");
        out.println("        }");
        out.println("        .nav-item i {");
        out.println("            width: 20px;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .main-content {");
        out.println("            margin-left: var(--sidebar-width);");
        out.println("            flex: 1;");
        out.println("            padding: 30px;");
        out.println("        }");
        out.println("        .dashboard-header {");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .dashboard-cards {");
        out.println("            display: grid;");
        out.println("            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));");
        out.println("            gap: 20px;");
        out.println("        }");
        out.println("        .card {");
        out.println("            background-color: white;");
        out.println("            border-radius: 8px;");
        out.println("            padding: 20px;");
        out.println("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        .card-header {");
        out.println("            display: flex;");
        out.println("            justify-content: space-between;");
        out.println("            align-items: center;");
        out.println("            margin-bottom: 15px;");
        out.println("        }");
        out.println("        .card-title {");
        out.println("            font-size: 1.2rem;");
        out.println("            color: var(--dark-color);");
        out.println("        }");
        out.println("        .card-icon {");
        out.println("            font-size: 1.5rem;");
        out.println("        }");
        out.println("        .card-body {");
        out.println("            color: #7f8c8d;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            display: inline-block;");
        out.println("            padding: 8px 15px;");
        out.println("            border-radius: 4px;");
        out.println("            text-decoration: none;");
        out.println("            margin-top: 10px;");
        out.println("            font-weight: 500;");
        out.println("            transition: all 0.3s;");
        out.println("        }");
        out.println("        .btn-primary {");
        out.println("            background-color: var(--primary-color);");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-primary:hover {");
        out.println("            background-color: #2980b9;");
        out.println("        }");
        out.println("    </style>");
        out.println(
                "    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css'>");
        out.println("</head>");
        out.println("<body>");

        // Sidebar Navigation
        out.println("<div class='sidebar'>");
        out.println("    <div class='sidebar-header'>");
        out.println("        <h2>Gestion de Stock</h2>");
        out.println("    </div>");
        out.println("    <nav class='sidebar-nav'>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='" + req.getContextPath() + "/'>");
        out.println("                <i class='fas fa-home'></i>");
        out.println("                <span>Tableau de bord</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='" + req.getContextPath() + "/marques'>");
        out.println("                <i class='fas fa-tags'></i>");
        out.println("                <span>Marques</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='" + req.getContextPath() + "/produits'>");
        out.println("                <i class='fas fa-box-open'></i>");
        out.println("                <span>Produits</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='" + req.getContextPath() + "/stocks'>");
        out.println("                <i class='fas fa-warehouse'></i>");
        out.println("                <span>Stocks</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("    </nav>");
        out.println("</div>");

        // Main Content
        out.println("<div class='main-content'>");
        out.println("    <div class='dashboard-header'>");
        out.println("        <h1>Tableau de bord</h1>");
        out.println("        <p>Bienvenue dans votre application de gestion de stock</p>");
        out.println("    </div>");

        out.println("    <div class='dashboard-cards'>");
        out.println("        <div class='card'>");
        out.println("            <div class='card-header'>");
        out.println("                <h3 class='card-title'>Marques</h3>");
        out.println("                <i class='fas fa-tags card-icon'></i>");
        out.println("            </div>");
        out.println("            <div class='card-body'>");
        out.println("                <p>Gérez les marques de vos produits</p>");
        out.println(
                "                <a href='" + req.getContextPath() + "/marques' class='btn btn-primary'>Accéder</a>");
        out.println("            </div>");
        out.println("        </div>");

        out.println("        <div class='card'>");
        out.println("            <div class='card-header'>");
        out.println("                <h3 class='card-title'>Produits</h3>");
        out.println("                <i class='fas fa-box-open card-icon'></i>");
        out.println("            </div>");
        out.println("            <div class='card-body'>");
        out.println("                <p>Gérez votre catalogue de produits</p>");
        out.println(
                "                <a href='" + req.getContextPath() + "/produits' class='btn btn-primary'>Accéder</a>");
        out.println("            </div>");
        out.println("        </div>");

        out.println("        <div class='card'>");
        out.println("            <div class='card-header'>");
        out.println("                <h3 class='card-title'>Stocks</h3>");
        out.println("                <i class='fas fa-warehouse card-icon'></i>");
        out.println("            </div>");
        out.println("            <div class='card-body'>");
        out.println("                <p>Suivez et gérez vos niveaux de stock</p>");
        out.println(
                "                <a href='" + req.getContextPath() + "/stocks' class='btn btn-primary'>Accéder</a>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}