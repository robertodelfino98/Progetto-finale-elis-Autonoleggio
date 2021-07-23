<%@include file="navbar.jsp"%>
<% String email = request.getServletContext().getInitParameter("super_email");
    String numeroTelefono = request.getServletContext().getInitParameter("contatto_telefono");
%>
<div class="contanier">
    <div class="row">
        <div class="col-12">
            <p>La nostra email è: <%=email%>
            </p>
            <p>Il nostro numero di telefono è: <%=numeroTelefono %>
            </p>
        </div>
    </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>