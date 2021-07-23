<%@page import="utilities.ScritturaSuFile"%>
<%@ include file="navbar.jsp" %>
<%@ page import="java.util.List, model.Utente, model.Noleggio" %>
<%List<Noleggio> listaNoleggi = (List<Noleggio>)request.getAttribute("listaNoleggi");
List<Utente> listaStaff = (List<Utente>)request.getAttribute("listaStaff");%>

    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1>Membri dello staff</h1>
          <table class="table table-striped">
            <thead>
    <%if( listaStaff!=null && listaStaff.size()>0 ) {%>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nome</th>
                <th scope="col">Cognome</th>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">Elimina Staff</th>
              </tr>
            </thead>
            <tbody>
    	<%for( Utente st : listaStaff ) {%>
              <tr>
                <td>1</td>
                <td><%=st.getNome()%></td>
                <td><%=st.getCognome()%></td>
                <td><%=st.getEmail()%></td>
                <td><%=st.getPassword()%></td>
                <td>
                  <form action="impostazioni" method="POST">
                    <input type="submit" value="Elimina Staff" />
                    <input type="hidden" name="action" value="deleteStaff"/>
                    <input type="hidden" name="idStaff" value=<%=st.getId()%>/>
                  </form>
                </td>
              </tr>
		<%}
	}else {%>
		<tr>
			<td>Non esistono utenti Staff</td>
		</tr>
	<%}%>
            </tbody>
          </table>
          <div class="container-fluid" id="container-background">
            <div class="row">
              <div class="col-12 text-center">
                <a href="aggiungistaff">Crea Nuovo Membro dello Staff</a>
              </div>
            </div>
          </div>
          <h1>Impostazioni Autonoleggio</h1>
          <table class="table table-striped">
            <tbody>
              <tr>
                <td>Posti Auto/Capienza Autonoleggio</td>
                <td>
                  <form action="impostazioni" method="POST">
                    <input
                      name="capienzaAuto"
                      type="number"
                      value=<%=ScritturaSuFile.letturaCapienza() %>
                      min="0"
                      max="1000"
                      step="1"
                    />
                    <input type="hidden" name="action" value="modificaCapienza">
                    <input type="submit" value="modifica" />
                  </form>
                </td>
              </tr>
              <tr>
                <td>Entità Penale</td>

                <td>
                  <form action="impostazioni" method="POST">
                    <input
                      name="valoreMora"
                      type="number"
                      value=<%=ScritturaSuFile.letturaPenale()%>
                      min="0"
                      max="1000"
                      step="1"
                    />
                    <input type="hidden" name="action" value="modificaPenale">  
                     
                    <input type="submit" value="modifica"/>
                  </form>
                </td>
              </tr>
              <tr>
                <td>nome Autonoleggio</td>
                <td>
                  <form action="impostazioni" method="POST">
                    <input type="text" name="nomeAutonoleggio" value=<%=ScritturaSuFile.letturaNomeAutonoleggio()%> />
                    <input type="hidden" name="action" value="modificaNomeAutonoleggio"> 
                    <input type="submit" value="modifica" />
                  </form>
                </td>
              </tr>
              <tr>
              <td>numero giorni massimi di annulamento noleggio</td>
              <td> <form action="impostazioni" method="POST">
                    <input
                      name="valoreGiorni"
                      type="number"
                      value=<%=ScritturaSuFile.letturaGiornoAnnulamentoNoleggi() %>
                      min="0"
                      max="1000"
                      step="1"
                    />
                    <input type="hidden" name="action" value="annulamentoGiorniNoleggio">  
                     
                    <input type="submit" value="modifica"/>
                  </form>
                </td> 
              </tr>
            </tbody>
          </table>
          <h1>Statistiche</h1>
          <table class="table table-striped">
            <thead>
	<%if( listaNoleggi!=null && listaNoleggi.size()>0 ) {%>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Modello</th>
                <th scope="col">Marca</th>
                <th scope="col">Categoria</th>
                <th scope="col">Numero di Noleggi</th>
                <th scope="col">Income Generato</th>
              </tr>
            </thead>
            <tbody>
		<%for( Noleggio nol : listaNoleggi ) {
			int nolCnt = 0;
			double totNol = 0.0;%>
              <tr>
                <td>1</td>
                <td><%=nol.getAutomobile().getModello()%></td>
                <td><%=nol.getAutomobile().getMarca()%></td>
                <td><%=nol.getAutomobile().getCategoria().getNomeCategoria()%></td>
                <td><%for(int i = 0; i < nol.getAutomobile().getNoleggios().size(); i++) {
                		if( nol.getAutomobile().getNoleggios().get(i) != null ) nolCnt++;
                	}%><%=nolCnt%></td>
                <td><%for(int i = 0; i < nol.getAutomobile().getNoleggios().size(); i++) {
                		totNol += nol.getAutomobile().getNoleggios().get(i).getCostoTotale();
                	}%><%=totNol%></td>
              </tr>
		<%}
	}else {%>
		<tr>
			<th scope="col">Non sono stati effettuati noleggi</th>
		</tr>
	<%}%>
            </tbody>
          </table>
        </div>
      </div>
    </div>


    <script>
      $("input[type='number']").inputSpinner();
    </script>
  </body>
</html>