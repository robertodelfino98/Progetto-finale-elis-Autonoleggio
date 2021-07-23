<%@include file="navbar.jsp"%>
<style>
 <%@ include file = "css/noleggio.css"%>
</style>
<%@page import="model.Automobile,java.util.List"  %>
<div class="container">
    <div class="row" >
        <div class="col-12"  style="background-image: url(./img/img_parcoauto/mercedes.png);  background-repeat: no-repeat;
  background-size: 100%;">
  <%List<Automobile> automobili = (List<Automobile>) request.getSession().getAttribute("listaAuto");
  	if(automobili!= null && automobili.size() > 0){
  		if(u != null){
  		for(int i = 0; i<automobili.size();i++){
  			String msg =(String)request.getAttribute("msgErr");
  			if(msg != null){
  				%>
  				<p class="alert alert-danger"><%=msg%></p>
  				<%
  			}
  			%>
  			
  			            <ul>
                <li class="booking-card">
                    <div class="book-container">
                        <div class="content" >
                        	<form action="noleggio" method="POST">
                        		<input type ="hidden" name ="noleggio" value=<%=automobili.get(i).getId()%>>
                        		<input type = "submit" value = "Prenota Ora">
                        	</form>
                        </div>
                    </div>
                    <div class="informations-container">
                        <h2 class="title"><%=automobili.get(i).getMarca() +" "+ automobili.get(i).getModello()   %></h2>
                        <% if(automobili.get(i).getColore() != null){ %>
                             <p class="sub-title">
                              <%= automobili.get(i).getColore() +" "+ (automobili.get(i).getNeopatentati()? "per neopatentati": "") %>                          
                               </p>
                            <%    
                            }else{ 
                             %>
                             <p class="sub-title">
                               <%= (automobili.get(i).getNeopatentati()? "per neopatentati": "") %>
                            </p>                         
                            <% } %>

                             <p class="price">

                        <% double prezzoStimato = (double) session.getAttribute("prezzoStimato"); %>
                        <h3>A partire da </h3>
                        <p> <%= prezzoStimato %> </p>
                        <svg class="icon" style="width: 24px; height: 24px" viewBox="0 0 24 24">
                            <path fill="currentColor"
                                  d="M3,6H21V18H3V6M12,9A3,3 0 0,1 15,12A3,3 0 0,1 12,15A3,3 0 0,1 9,12A3,3 0 0,1 12,9M7,8A2,2 0 0,1 5,10V14A2,2 0 0,1 7,16H17A2,2 0 0,1 19,14V10A2,2 0 0,1 17,8H7Z"/>
                        </svg>
                        </p>
                        <div class="more-information">
                            <div class="info-and-date-container">
                                <div class="box data_inizio">
                                    <p>Tipologia rifornimento</p>
                                    <div class="benzina">
                                        <%= "Benzina" %>
                                     </div>
                                </div>
                                <div class="box date_fine">
                                    <p>Kilometri</p>
                                    <div class="kilometri">
                                        <%= automobili.get(i).getChilometri() %>  
                                     </div>
                                </div>
                            </div>
                        </div>
                        <p class="disclaimer">
                            Politica carburante serbatoio allo stesso livello
                        </p>
                        <p>Chilometraggio illimitato</p>
                    </div>
                </li>
            </ul>
  			<%
  		}
  	}else{
  		request.getSession().setAttribute("loggaNoleggio", "si");
  		%>
  		<h1>Devi fare la login</h1>
  		<%
  	}
  	}else{
  		%>
  		<h3>Non ci sono autobomobili disponibili che corrispondo ai criteri di ricerca</h3>
  		<%
  	}
  %>

        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>

</body>
</html>