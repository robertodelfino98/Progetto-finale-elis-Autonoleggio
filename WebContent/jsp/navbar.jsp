<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="model.Utente, javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <style>
        <%@ include file = "css/navbar.css"%>
    </style>
</head>
<%Utente u = (Utente) request.getSession().getAttribute("utente");%>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active box foo" aria-current="page" href="Home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="listaauto">Parco Auto</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Chi Siamo</a></li>
                <li class="nav-item"><a class="nav-link" href="jsp/contatti.jsp">Contatti</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Promozioni</a></li>
                <%
                    if (u != null && u.getRuolo().getNomeRuolo().equals("admin")) {
                %>
                <li class="nav-item"><a class="nav-link" href="impostazioni">Impostazioni</a></li>
                <%
                } else if (u != null && u.getRuolo().getNomeRuolo().equals("staff")) {
                %>
                <li class="nav-item"><a class="nav-link" href="confermaregistrazioni">Conferma Utenti</a></li>
                <li class="nav-item"><a class="nav-link" href="listanoleggio">Lista Noleggi</a></li>
                <%
                    }
                    if (u == null) {
                %>
                <li><a href="login" class="btn btn-outline-primary" id="bottone_accedi">Accedi</a></li>
                <li><a href="registrati" class="btn btn-outline-secondary" id="bottone_registrati">Registrati</a></li>

                <%
                } else if(u != null && !u.getRuolo().getNomeRuolo().equals("admin")) {
                %>
                <li><a href="infoutente" class="btn btn-outline-primary"
                       id="bottone_accedi"><%=u.getNome() + u.getCognome()%>
                </a></li>
                <li><a href="logout" class="btn btn-outline-primary" id="bottone_logout">Logout</a></li>
                <%
                    }
                %>

                <!--  <li><a href="#" class="btn btn-outline-secondary pull-right"></a></li>-->

                <%
                    if (u != null && u.getRuolo().getNomeRuolo().equals("admin")) {
                %>
                <li><a href="infoutente" class="btn btn-outline-primary" id="bottone_accedi"><%=u.getEmail()%></a></li>
                <li><a href="logout" class="btn btn-outline-primary" id="bottone_logout">Logout</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>
</body>

</html>
