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
        out.println("            padding: 0;");
        out.println("        }");
        out.println("        #content-frame {");
        out.println("            width: 100%;");
        out.println("            height: 100vh;");
        out.println("            border: none;");
        out.println("        }");
        out.println("        .active {");
        out.println("            background-color: rgba(255,255,255,0.2);");
        out.println("        }");
        out.println("    </style>");
        out.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css'>");
        out.println("    <script>");
        out.println("        function loadContent(url) {");
        out.println("            document.getElementById('content-frame').src = url;");
        out.println("            // Mise à jour de l'élément actif");
        out.println("            document.querySelectorAll('.nav-item').forEach(item => {");
        out.println("                item.classList.remove('active');");
        out.println("            });");
        out.println("            event.currentTarget.parentElement.classList.add('active');");
        out.println("        }");
        out.println("    </script>");
        out.println("</head>");
        out.println("<body>");
        
        // Sidebar Navigation
        out.println("<div class='sidebar'>");
        out.println("    <div class='sidebar-header'>");
        out.println("        <h2>Gestion de Stock</h2>");
        out.println("    </div>");
        out.println("    <nav class='sidebar-nav'>");
        out.println("        <div class='nav-item active'>");
        out.println("            <a href='javascript:void(0)' onclick=\"loadContent('" + req.getContextPath() + "/dashboard')\">");
        out.println("                <i class='fas fa-home'></i>");
        out.println("                <span>Tableau de bord</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='javascript:void(0)' onclick=\"loadContent('" + req.getContextPath() + "/marques')\">");
        out.println("                <i class='fas fa-tags'></i>");
        out.println("                <span>Marques</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='javascript:void(0)' onclick=\"loadContent('" + req.getContextPath() + "/produits')\">");
        out.println("                <i class='fas fa-box-open'></i>");
        out.println("                <span>Produits</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("        <div class='nav-item'>");
        out.println("            <a href='javascript:void(0)' onclick=\"loadContent('" + req.getContextPath() + "/stocks')\">");
        out.println("                <i class='fas fa-warehouse'></i>");
        out.println("                <span>Stocks</span>");
        out.println("            </a>");
        out.println("        </div>");
        out.println("    </nav>");
        out.println("</div>");
        
        // Main Content Area with iframe
        out.println("<div class='main-content'>");
        out.println("    <iframe id='content-frame' src='" + req.getContextPath() + "/dashboard'></iframe>");
        out.println("</div>");
        
        out.println("</body>");
        out.println("</html>");
    }
}