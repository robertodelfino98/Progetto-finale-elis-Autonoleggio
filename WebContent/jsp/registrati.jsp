<%@include file="navbar.jsp"%>
<style>
    <%@include file="css/registrati.css"%>
</style>
<%String msg = (String)request.getAttribute("msgErr");%>
<div class="container" id="container-login">
    <div class="row" id="row-card">
        <div class="col-12">
            <h1 class="titolo">Registrati</h1>

            <h3 class="titolo">Informazioni Personali</h3>
            <form action="registrati" method="post" >
                <input type="text" class="form-control" placeholder="Inserisci Nome" name="nome"/>
                <input type="text" class="form-control" placeholder="Inserisci Cognome" name="cognome" required/>
                <input type="text" class="form-control" placeholder="Inserisci Data di Nascita" name="dataNascita"
                       onfocus="(this.type='date')" onblur="(this.type='text')" required/>
                <input type="text" class="form-control" placeholder="Inserisci Codice Fiscale" name="codiceFiscale"
                       required/>
                <input type="email" class="form-control" placeholder="Inserisci Email" name="email" required/>
                <input type="password" class="form-control" placeholder="Inserisci password" name="password" required/>
                <input type="password" class="form-control" placeholder="Conferma password" name="confermaPassword"
                       required/>

                <h3 class="titolo">Informazioni Patente</h3>

                <input type="text" class="form-control" placeholder="Numero Patente" name="nPatente" required/>
                <input type="text" class="form-control" placeholder="Data di Rilascio" name="dataRilascioPatente"
                       onfocus="(this.type='date')" onblur="(this.type='text')" required/>
                <input type="text" class="form-control" placeholder="Data di Scadenza" name="dataScadenzaPatente"
                       onfocus="(this.type='date')" onblur="(this.type='text')" required/>
                <div>
                    <a class="forgot text-muted" href="#">Sei gia registrato? Fai il login qui</a>
                </div>
                <input type="submit" class="form-control" placeholder="Submit" id="bottone_submit"/>
            </form>
            <%if(msg != null){
            	%>
            	<h3><%=msg%></h3>
            	<%
              } 
            %>
            
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
</body>
</html>