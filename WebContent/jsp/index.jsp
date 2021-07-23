<%@include file="navbar.jsp"%>
<%@ page import="java.util.List, model.Categoria" %>
<%
    List<Categoria> categorie = (List<Categoria>) request.getAttribute("listaCategorie");
	String msg = (String) request.getAttribute("msgErr");
%>
<style>
<%@include file = "css/index.css"%>
</style>
<header>
    <div class="container-fluid" id="container-video">
        <div class="row">
            <div class="col-12">
                <div class="embed-responsive embed-responsive-16by9">
                    <video autoplay muted loop id="myVideo">
                        <embed src="/video/video.mp4" width="300" height="300">
                    </video>
                </div>
            </div>
        </div>
        <div class="row" id="row-form">
                     <div class="col-2 col-lg-3"></div>
            <div class="col-8 col-lg-6">
                <% if (categorie != null) {
                	if(u==null || u.getRuolo().getNomeRuolo().equals("cliente")){
                		
                	
                %>
                <h3>Prenota la tua auto</h3>
                <form class="selectbox" method="post" action="Home" name="select_autonoleggio">
                    <select class="form-select" aria-label="Default select example" name="categoria" required>
                        <option selected value="0">Categoria</option>
                        <%
                            for (int i = 0; i < categorie.size(); i++) {
                        %>
                        <option value=<%=categorie.get(i).getId()%>><%=categorie.get(i).getNomeCategoria()%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                    <input type="date" name="dataInizio" required>
                    <input type="date" name="dataFine" required>
                    <input type="submit" class="btn btn-outline-primary" id="bottone_cerca_automobile" value="Cerca Automobile">
                </form>
    
               <%
                	}
                }else {
                %>
                <h3>Non ci sono auto da poter noleggiare</h3>
                <%
                    }
                 if(msg != null){
                	 %>
                	 	<h4><%=msg%></h4>
                	 <%
                 }
                %>
                
            </div>
        </div>
    </div>
</header>

<%@include file = "footer.jsp" %>

</body>
</html>