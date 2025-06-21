<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des Marques</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-container { margin-bottom: 20px; padding: 15px; background: #f9f9f9; border-radius: 5px; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Gestion des Marques</h1>

    <%-- Affichage des messages d'erreur/succès --%>
    <c:if test="${not empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="success">${successMessage}</div>
    </c:if>

    <%-- Formulaire d'ajout de marque --%>
    <div class="form-container">
        <h2>Ajouter une nouvelle marque</h2>
        <form action="${pageContext.request.contextPath}/marques" method="post">
            <input type="hidden" name="action" value="create">
            <label for="nom">Nom :</label>
            <input type="text" id="nom" name="nom" required>
            
            <label for="origine">Origine :</label>
            <input type="text" id="origine" name="origine" required>
            
            <button type="submit">Ajouter</button>
        </form>
    </div>

    <%-- Formulaire d'ajout de produit à une marque --%>
    <div class="form-container">
        <h2>Ajouter un produit à une marque</h2>
        <form action="${pageContext.request.contextPath}/marques" method="post">
            <input type="hidden" name="action" value="addProduct">
            
            <label for="marque">Marque :</label>
            <select id="marque" name="marque" required>
                <option value="">-- Sélectionnez une marque --</option>
                <c:forEach items="${marques}" var="marque">
                    <option value="${marque.nom}">${marque.nom} (${marque.origine})</option>
                </c:forEach>
            </select>
            
            <label for="reference">Référence :</label>
            <input type="text" id="reference" name="reference" required>
            
            <label for="denomination">Dénomination :</label>
            <input type="text" id="denomination" name="denomination" required>
            
            <label for="prix">Prix :</label>
            <input type="number" step="0.01" id="prix" name="prix" required>
            
            <label for="poids">Poids (kg) :</label>
            <input type="number" step="0.01" id="poids" name="poids" required>
            
            <label for="volume">Volume (m³) :</label>
            <input type="number" step="0.01" id="volume" name="volume" required>
            
            <button type="submit">Ajouter Produit</button>
        </form>
    </div>

    <%-- Liste des marques existantes --%>
    <h2>Liste des Marques</h2>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Origine</th>
                <th>Nombre de Produits</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${marques}" var="marque">
                <tr>
                    <td>${marque.nom}</td>
                    <td>${marque.origine}</td>
                    <td>${not empty marque.listeProduits ? marque.listeProduits.size() : 0}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/marques?action=delete&nom=${marque.nom}" 
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette marque?')">Supprimer</a>
                        <a href="${pageContext.request.contextPath}/produits?filterMarque=${marque.nom}">Voir produits</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>