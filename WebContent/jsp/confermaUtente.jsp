<%@page import="java.util.List,model.Utente"%>
<%@ include file="navbar.jsp" %>
<%List<Utente> listaUtenti = (List<Utente>)request.getAttribute("pendingList");
if(listaUtenti != null) {
	%>
	<div class="container-fluid">
		<div class="row">
		<table class="table table-striped">
		<thead>
		 <tr>
		 <th>Nome</th>
		 <th>Cognome</th>
		 <th>Email</th>
		 <th>Data di nascita</th>
		 <th>Codice Fiscale</th>
		 <th>Numero Patente</th>
		 <th>Data rilascio patente</th>
		 <th>Data scadenza patente</th>
         <th>Accetta</th>
         <th>Rifiuta</th>
		 </tr>
		</thead>
			
    <%
        for(int i=0;i<listaUtenti.size();i++){
			%>
			<tbody>
                <tr>
                    <td><%=listaUtenti.get(i).getNome() %></td>
                    <td><%=listaUtenti.get(i).getCognome() %></td>
                    <td><%=listaUtenti.get(i).getEmail()%></td>
                    <td><%=listaUtenti.get(i).getDataDiNascita()%></td>
                    <td><%=listaUtenti.get(i).getCodiceFiscale()%></td>
                    <td><%=listaUtenti.get(i).getNumeroPatente()%></td>
                    <td><%=listaUtenti.get(i).getDataRilascioPatente()%></td>
                    <td><%=listaUtenti.get(i).getDataScadenzaPatente()%></td>
                    <td>
                    	<form action ="confermaregistrazioni" method="POST">
                    	    <input type="hidden" name="scelta" value="accettato">
                            <input type="hidden" name="utId" value="<%=listaUtenti.get(i).getId()%>">
                        	<input type="submit" value="Accetta Registrazione">
                    	</form>
                    </td>
                    <td>
                        <form action="confermaregistrazioni" method="POST">
                            <input type="hidden" name="scelta" value="rifiutato">
                            <input type="hidden" name="utId" value="<%=listaUtenti.get(i).getId()%>">
                            <input type="submit" value="Rifiuta Registrazione">
                        </form>
                    </td>
                </tr>
			</tbody>
          <% 
        }
    %>
    	</table>
        </div>
    </div>
	<%
}
%>
<%@ include file ="footer.jsp" %>
</body>
</html>