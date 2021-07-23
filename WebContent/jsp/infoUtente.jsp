<%@page import="utilities.ScritturaSuFile"%>
<%@page import="java.util.List"%>
<%@include file="navbar.jsp" %>
<%@ page import="model.Noleggio,java.time.LocalDate" %>
<style>
    <%@include file="css/infoUtente.css"%>
</style>
<div class="container emp-profile">
    <form method="post">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="img/user.png" alt=""/> 
                    <div class="file btn btn-lg btn-primary">
                        Change Photo
                        <input type="file" name="file"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h5>
                        <%
                        if (!u.getRuolo().getNomeRuolo().equals("admin")) {
                        %>
                        <%=u.getNome()%>
                        <%
                        }
                        %>
                    </h5>
                    <h6>
                        <%=u.getRuolo().getNomeRuolo()%>
                    </h6>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true">Info Utente</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                               aria-controls="profile" aria-selected="false">Storico Noleggi</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <input type="button" class="profile-edit-btn" name="btnAddMore" id="btnEdit"
                       value="Edit Profile"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <%
                        if (!u.getRuolo().getNomeRuolo().equals("admin")) {
                        %>
                        <!-- L'admin non visualizza il proprio nome -->
                        <div class="row">
                            <div class="col-md-6">
                                <label>Nome</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getNome()%>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>cognome</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getCognome()%>
                                </p>
                            </div>
                        </div>
                        <%
                        }
                        %>
                        <div class="row">
                            <div class="col-md-6">
                                <label>email</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getEmail()%>
                                </p>
                            </div>
                        </div>
                        <div class="row form_a_scomparsa d-none">
                            <form action = "infoutente" method="POST">
                            <div class="col-md-6">
                            
                                <label>Nuova Email</label>
                                <input class="d-block" type="text" name="email" placeholder="Nuova email" id="newMail"
                                       required>
                            </div>
                            <div class="col-md-6">
                                <label>Conferma nuova Email</label>
                                <div>
                                    <input type="text" name="email" placeholder="Conferma nuova email"
                                           id="newMailConferma" required>
                                    <input type="hidden" name="uId" value=<%=u.getId()%>>
                                    <input type="hidden" name="scelta" value="modEmail">
                                    <input type="button" class="btn btn-primary btn-sm mail disabled" value="Modifica"
                                           METHOD="post">
                                </div>
                            </div>
                            </form>
                        </div>
                        <%
                        if (!u.getRuolo().getNomeRuolo().equals("admin")) {
                        %>
                        <!-- Solo lo staff visualizza la propria password -->
                        <div class="row">
                            <div class="col-md-6">
                                <label>Password</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getPassword()%>
                                </p>
                            </div>
                        </div>
                        <%
                        }
                        %>
                        <div class="row form_a_scomparsa d-none">
                            <form action = "infoutente" method="POST">
                            <div class="col-md-6">
                                <label>Nuova Password</label>
                                <input class="d-block" type="password" name="password" placeholder="Nuova password"
                                       id="newPass" required>
                            </div>
                            <div class="col-md-6">
                                <label>Conferma nuova Password</label>
                                <div>
                                    <input type="text" name="nuova password" placeholder="Nuova password"
                                           id="newPassConferma" required>
                                    <input type="hidden" name="uId" value=<%=u.getId()%>>
                                    <input type="hidden" name="scelta" value="modPass">
                                    <input type="button" class="btn btn-primary btn-sm pass disabled" value="Modifica"
                                           METHOD="post">
                                </div>
                            </div>
                            </form>
                        </div>
                        <%
                        if (u.getRuolo().getNomeRuolo().equals("cliente")) {
                        %>
                        <!-- Solo il cliente visualizza tutte le info -->
                        <div class="row">
                            <div class="col-md-6">
                                <label>Data di Nascita</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getDataDiNascita()%>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Codice Fiscale</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getCodiceFiscale()%>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Numero Patente</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getNumeroPatente()%>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Data Emissione</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getDataRilascioPatente()%>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Data Scadenza</label>
                            </div>
                            <div class="col-md-6">
                                <p><%=u.getDataScadenzaPatente()%>
                                </p>
                            </div>
                        </div>
                        <%
                        }
                        %>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <%
                                    List<Noleggio> noleggi = (List<Noleggio>) request.getAttribute("listaNoleggi");
                                                                            if (noleggi != null && noleggi.size() > 0) {
                                    %>
                                    <div class="table-responsive">
                                        <table class="table table-striped">

                                            <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Marca</th>
                                                <th scope="col">Modello</th>
                                                <th scope="col">Categoria</th>
                                                <th scope="col">Targa</th>
                                                <th scope="col">Inizio</th>
                                                <th scope="col">Fine</th>
                                                <th scope="col">Data Consegna</th>
                                                <th scope="col">Prezzo Totale</th>
                                                <th scope="col">Annulla Noleggio</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                                                      <%
                                                                                      for (Noleggio nol : noleggi) {
                                                                                      %>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td><%=nol.getAutomobile().getMarca()%>
                                                </td>
                                                <td><%=nol.getAutomobile().getModello()%>
                                                </td>
                                                <td><%=nol.getAutomobile().getCategoria().getNomeCategoria()%>
                                                </td>
                                                <td><%=nol.getAutomobile().getTarga()%>
                                                </td>
                                                <td><%=nol.getDataInizio()%>
                                                </td>
                                                <td><%=nol.getDataFine()%>
                                                </td>
                                                <%
                                                if (nol.getDataConsegna() != null) {
                                                %>
                                                <td><%=nol.getDataConsegna()%>
                                                </td>
                                                <%
                                                } else {
                                                %>
                                                <td>Non ancora consegnato</td>
                                                <%
                                                }
                                                %>
                                                <td><%=nol.getCostoTotale()%>
                                                </td>
                                                <%
                                                int y = nol.getDataFine().getDayOfMonth() - LocalDate.now().getDayOfMonth();
                                                if (y < ScritturaSuFile.letturaGiornoAnnulamentoNoleggi()) {
                                                %>
                                                <td>
                                                    <form action="infoutente" method="POST">
                                                        <input type="hidden" name="uId" value=<%=u.getId()%>>
                                                        <input type="hidden" name="nolId" value=<%=nol.getId()%>>  
                                                        <input type="hidden" name="scelta" value="annulla">
                                                        <input type="submit" value="Annulla Noleggio">
                                                    </form>
                                                </td>
                                                <%
                                                    }
                                                %>
                                                <%
                                                    } //il for finisce qui
                                                %>

                                            </tr>
                                            </tbody>
                                        </table>
                                        <%
                                        } else {
                                        %>
                                        <h3>Non hai effatuato noleggi</h3>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script>
 <%@ include file ="js/infoUtente.js"%>
</script>
</script>
</body>
</html>
