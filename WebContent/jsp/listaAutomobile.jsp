<%@ include file="navbar.jsp" %>
<%@ page import="java.util.List,model.Categoria,model.Utente" %>
<style>
 <%@ include file="css/listaAutomobile.css"%>
</style>
<%List<Categoria> categorie = (List<Categoria>) request.getAttribute("listaCategorie");
Utente utente = (Utente)request.getSession().getAttribute("utente");
	if( categorie != null && categorie.size()>0 ){
		%>
		<div class="container-fluid">
		<%for(int i =0; i<categorie.size(); i++){
	    %>
	    <div class="row">
            <div class="bottoni_categoria" class="col-12">
                <h3><%=categorie.get(i).getNomeCategoria()%></h3>
                <%if(utente != null && utente.getRuolo().getNomeRuolo().equals("staff")) {
                	%>
                <form action="listaauto" class="bottoni_categoria" method="POST">	<!-- Form per aggiungere nuove categorie -->
					<input type="hidden" name="scelta" value ="addCategoria">
                    <input type="submit" value="Aggiungi Categoria"> <!-- rimanda a pannelloStaff.jsp (serve una nuova servlet) -->
                </form>
               <form action="listaauto" class="bottoni_categoria" method="POST">	<!-- Form per modificare le categorie -->
				   	<input type="hidden" name="scelta" value ="editCategoria">
               		<input type="hidden" name="idCategoria" value ="<%=categorie.get(i).getId()%>">
                    <input type="submit" value="Modifica Categoria"> <!-- rimanda a pannelloStaff.jsp (serve una nuova servlet) -->
                </form>
               <form action="listaauto" class="bottoni_categoria" method="POST">
						<input type ="hidden" name="scelta" value="addAuto">
                    	<input type="submit" value="Aggiungi Automobile">
                </form>
  
                	<% 
                }
                %>
            </div>
        </div>

 			<table class="table table-striped">
        
        		<thead>
            		<tr>
                		<th scope="col">#</th>
                		<th scope="col">Marca</th>
                		<th scope="col">Modello</th>
                		<th scope="col">Data di immatricolazione</th>
                		<th scope="col">Colore</th>
                		<th scope="col">Targa</th>
                		<th scope="col">Disponibilita</th>
                		<%if(utente != null && utente.getRuolo().getNomeRuolo().equals("staff")){
                	%>
                		<th scope="col">Modifica Automobile</th>
                		<th scope="col">Elimina Automobile</th>
                		             <% 
                }
                %>
            		</tr>
        		</thead>
        	<tbody>
        	     <%if(categorie.get(i).getAutomobiles() != null){
    	 for(int y=0;y<categorie.get(i).getAutomobiles().size();y++){
    		 %>
        		<tr>
            	<th scope="row"><%=y %></th>
            	<td><%=categorie.get(i).getAutomobiles().get(y).getMarca() %></td>
            	<td><%=categorie.get(i).getAutomobiles().get(y).getModello() %></td>
            	<td><%=categorie.get(i).getAutomobiles().get(y).getDataImmatricolazione()%></td>
            	<%if(categorie.get(i).getAutomobiles().get(y).getColore()!=null){
            		%>
            	<td><%=categorie.get(i).getAutomobiles().get(y).getColore()%></td>
            	<%
            	}%>
            	<td><%=categorie.get(i).getAutomobiles().get(y).getTarga()%></td>
            	<td><%=(categorie.get(i).getAutomobiles().get(y).getDisponibilita()== true)? "disponibile" : "non disponibile"%></td>
            	 <%if(utente != null && utente.getRuolo().getNomeRuolo().equals("staff")){
                	%>
            	<td>
                	<form action="listaauto" method="POST">
						<input type ="hidden" name="scelta" value="editAuto">
                		<input type ="hidden" name="idAuto" value="<%=categorie.get(i).getAutomobiles().get(y).getId()%>">
                    	<input type ="submit" value="Modifica Automobile">
                	</form>
            	</td>
            	<td>
                	<form action="listaauto" method="POST">
						<input type ="hidden" name="scelta" value="cambioDisponibilita">
                		<input type ="hidden" name="idAuto" value="<%=categorie.get(i).getAutomobiles().get(y).getId()%>">
                    	<input type ="submit" value="Cambia Disponibilità">
                	</form>
            	</td>
            	</tr>
            	<% 
                }
    	 }
                %>
        </tbody>
    </table>
    		 <%
    	
     }else{
    	 %>
    	 <h3>non ci sono automobili</h3>
    	 <%
     }
     
	}
		%>
        
    </div> 
		<%
	}else{
		%>
		<h1>Non ci sono categorie</h1>
		<%
	}
%>
    
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>