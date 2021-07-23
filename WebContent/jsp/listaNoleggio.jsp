<%@include file="navbar.jsp" %>
<%@ page import = "model.Noleggio, java.util.List" %>
<% List<Noleggio> noleggi = (List<Noleggio>) request.getAttribute("listaNoleggi");
   Double prezzo = (Double) request.getAttribute("prezzoTot");
%>

<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
           <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Nome</th>
          <th scope="col">Cognome</th>
          <th scope="col">Codice Fiscale</th>
          <th scope="col">Data di Nascita</th>
          <th scope="col">Patente</th>
          <th scope="col">Marca</th>
          <th scope="col">Modello</th>
          <th scope="col">Targa</th>
          <th scope="col">Categoria</th>
          <th scope="col">Data Inizio</th>
          <th scope="col">Data Fine</th>
          <th scope="col">Prezzo stimato</th>
          <th scope="col">Avvia Noleggio</th>
          <th scope="col">Termina Noleggio</th>
          <%if(prezzo != null){
        	  %>
          <th scope="col" class="box_a_scomparsa d-none">Prezzo finale</th>
     		<%} %>
        </tr>
      </thead>

      <%
       if(noleggi != null){
    	      for(Noleggio n : noleggi){
      %>

      <tbody>
        <tr>
          <th scope="row">  <%= n.getId()%> </th>
          <td>  <%= n.getUtente().getNome() %>            </td>
          <td>  <%= n.getUtente().getCognome()  %>        </td>
          <td>  <%= n.getUtente().getCodiceFiscale() %>   </td>
          <td>  <%= n.getUtente().getDataDiNascita()  %>  </td>
          <td>  <%= n.getUtente().getNumeroPatente() %>  </td>  
          <td>  <%= n.getAutomobile().getMarca() %>       </td>
          <td>  <%= n.getAutomobile().getModello() %>     </td>
          <td>  <%= n.getAutomobile().getTarga() %>       </td>
          <td>  <%= n.getAutomobile().getCategoria().getNomeCategoria() %> </td>
          <td>  <%= n.getDataInizio() %>   </td>
          <td>  <%= n.getDataFine()   %>   </td>
          <td>  <%= n.getCostoTotale() %>  </td>
          <td>
          <%if(!n.isAvviato()){
        	  %>
        	    <form method="POST" action="listanoleggio">
          			<input type = "hidden"  name ="avvia" value=<%=n.getId()%>>
          			<input type="submit" value="Avvia Noleggio">
          		</form>
        	  <% 
          }
          %>

		  </td>
		  <td>
          <form method="POST" action="listanoleggio">
          <input type = "hidden" name ="termina" value=<%=n.getId()%>>
          <input type="submit" value="termina Noleggio">
          </form>
		  </td>
		  <%
		  	if(prezzo != null){
		  %>
       	  <td class="box_a_scomparsa d-none"><%=prezzo%></td>
        </tr> 
        <%
		  	}
      }
       }
      %>
      </tbody>
    </table>
            </div>
        </div>
    </div>
</div>
  
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script>
	<%@ include file = "js/listaNoleggi.js"%>
</script>

  </body>
</html>
</body>
</html>