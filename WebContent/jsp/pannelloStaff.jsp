<!DOCTYPE html>
<%@page import="daoFactory.DaoFactory"%>
<html lang="en">
<%@ page import="model.Categoria,model.Utente,java.util.List,model.Automobile" %>
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex, nofollow">

    <title>Pannello Staff</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <style>
    	<%@include file="css/pannelloStaff.css"%>
    </style>
</head>
<body>
<%List<Categoria> listaCategoria = (List<Categoria>)request.getAttribute("listaCategorie");
int idAuto = 0;
int idCategoria = 0;
//if(request.getAttribute("modAuto")!=null && ((String)(request.getAttribute("modAuto"))).equals("si") ){
    try{
	idAuto = Integer.parseInt((String)request.getAttribute("idAuto"));
    }catch(NumberFormatException e){
    	e.printStackTrace();
    	System.out.println("Automobile");
    }
//}

//if(request.getAttribute("modCategoria")!=null && ((String)request.getAttribute("modCategoria")).equals("si")){
    try{
    idCategoria	= Integer.parseInt((String)request.getAttribute("idCat"));
    } catch(NumberFormatException e){
        e.printStackTrace();
        System.out.println("Categoria!!!!!");
    }
//}
%>
<div class="container emp-profile">
    <div class="row">
        <div class="col-12 col-md-6">
            <div class="profile-head">
                <h5>
                    Pannelli di controllo
                </h5>
                <h6>
                    Aggiungi/modifica
                </h6>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                           aria-controls="home" aria-selected="true">Automobili</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                           aria-controls="profile" aria-selected="false">Categorie</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="container">
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-md-8">
                                <h3 class="titolo">Aggiungi Auto</h3>
                                <form action="gestisciautomobile" method="POST">
                                    <input type="text" class="form-control" placeholder="Marca" name="marca"/>
                                    <input type="text" class="form-control" placeholder="Modello"
                                           name="modello"/>
                                    <input type="hidden" name="scelta" value="1">
                                    <input type="text" class="form-control" placeholder="Targa" name="targa"/>
                                    <select class="form-select" aria-label=".form-select-lg example" name = "nomeCategoria">
                                        <option selected>Categoria</option>
                                        <%
                                        for(Categoria c : listaCategoria){
                                        %>
                                        <option> <%= c.getNomeCategoria() %></option>
                                    <%
                                    }
                                    %>
                                    </select>                      
                                    <input type="number" class="form-control" placeholder="Chilometri"
                                           name="chilometri"/>
                                    <input type="text" class="form-control"
                                           placeholder="Data di Immatricolazione" name="dataImmatricolazione"
                                           onfocus="(this.type='date')" onblur="(this.type='text')"/>
                                    <input type="text" class="form-control" placeholder="Colore" name="colore"/>
                                    <input type="submit" class="form-control" value="Aggiungi"/>
                                </form>
                                <h3 class="titolo">Modifica Auto</h3>
                                <form action="gestisciautomobile" method="POST">
                                    <input type="text" class="form-control" placeholder="Marca" name="marca"/>
                                    <input type="text" class="form-control" placeholder="Modello"
                                           name="modello"/>
                                    <input type="text" class="form-control" placeholder="Targa" name="targa"/>
                                    <select class="form-select" aria-label=".form-select-lg example" name = "nomeCategoria">
                                        <option selected>Categoria</option>
                                        <%
                                        for(Categoria c : listaCategoria){
                                        %>
                                        <option> <%= c.getNomeCategoria() %></option>
                                    <%
                                    }
                                    %>
                                    </select>  
                                    <input type="number" class="form-control" placeholder="Chilometri"
                                           name="chilometri"/>
                                    <input type="text" class="form-control" placeholder="Colore" name="colore"/>
                                    <input type="hidden" name="scelta" value="2">
                                 	<input type="hidden" name="idAuto" value="<%=idAuto%>">
                                    <input type="submit" class="form-control" value="Modifica"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <div class="container">
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-md-8">
                                <h3 class="titolo">Aggiungi Categoria</h3>
                                <form action="gestiscicategoria" method="POST">
                                    <input type="text" class="form-control" placeholder="Nome" name="nome"/>
                                    <input type="number" class="form-control" placeholder="prezzo giornaliero"
                                           name="prezzo_giornaliero"/>
                                    <input type="number" class="form-control" placeholder="prezzo settimanale"
                                           name="prezzo_settimanale"/>
                                    <input type="number" class="form-control" placeholder="prezzo mensile"
                                           name="prezzo_mensile"/>
                                    <input type="hidden" name="scelta" value="aggiungiCategoria">
                                    <input type="submit" class="form-control" value="Aggiungi"/>
                                </form>
                                <h3 class="titolo">Modifica Categoria</h3>
                                <form action="gestiscicategoria" method="POST">
                                    <input type="text" class="form-control" name="nomeCategoria" placeholder="nome della categoria">
                                    <input type="hidden" name="idCategoria" value="<%=idCategoria%>"/>
                                    <input type="number" class="form-control" placeholder="prezzo giornaliero"
                                           name="prezzo_giornaliero"/>
                                    <input type="number" class="form-control" placeholder="prezzo settimanale"
                                           name="prezzo_settimanale"/>
                                    <input type="number" class="form-control" placeholder="prezzo mensile"
                                           name="prezzo_mensile"/>

                                    <input type="hidden" name="scelta" value="modificaCategoria">
                                    
                                    <input type="submit" class="form-control" value="Modifica"/>
                                </form>
                                <h3 class="titolo">Aggiungi Diffide</h3>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Nome</th>
                                                        <th scope="col">Cognome</th>
                                                        <th scope="col">Email</th>
                                                        <th scope="col">Codice fiscale</th>
                                                        <th scope="col">Diffide</th>
                                                        <th scope="col">modifica diffide</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%List<Utente> utenti = (List<Utente>) request.getAttribute("listaUtenti");
                                                        if(utenti!=null){
                                                            for(int i = 0; i
                                                            <utenti.size
                                                            (); i++){
                                                            %>
                                                            <tr>
                                                                <th scope="row"><%=i%></th>
                                                                <td><%=utenti.get(i).getNome()%></td>
                                                                <td><%=utenti.get(i).getCognome() %></td>
                                                                <td><%=utenti.get(i).getEmail()%></td>
                                                                <td><%=utenti.get(i).getCodiceFiscale()%></td>
                                                                <td><%=utenti.get(i).getDiffide() %></td>
                                                                <td>
                                                                    <%if( utenti.get(i).getDiffide() < 3 ) {%>
                                                                    <form action="pannellostaff" method="POST">
                                                                        <!-- manda l'id dell'utente da diffidare: ogni click vale +1 -->
                                                                        <input type="hidden" name="utenteDiffida"
                                                                               value=<%="utenti.get(i).getId()"%>>
                                                                        <input type="submit" value="Aggiungi diffida">
                                                                    </form>
                                                                    <%}%>
                                                                </td>

                                                                <%
                                                                }
                                                        }
                                                                %>
                                                            </tr>
                                                    </tbody>
                                                </table>
                                                <%String msg = (String) request.getAttribute("msgErr");
                                                if(msg != null){
                                                %>
                                                <h3><%=msg%></h3>
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
            </div>
        </div>
    </div>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>