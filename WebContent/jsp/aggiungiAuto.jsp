
<%@page import="model.Automobile, model.Categoria, java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="ISO-8859-1">
        <title>  Insert title here   </title>
    </head>

    <body>

        <%
        List<Categoria> listaCategorie = (List<Categoria>) request.getAttribute("listaCategorie");  
        %>

        <form action="AggiungiAuto" method="POST">
            Inserisci marca: <input type="text" name="marca"> <br/>
            Inserisci modello: <input type="text" name="modello"> <br/>
            Inserisci targa: <input type="text" name="targa"> <br/>
            Inserisci categoria: 
                                <select name="categoria">
                                    <option selected> </option> 
                                    <% 
                                        for(Categoria c : listaCategorie){
                                    %>
                                            <option> <%= c.getNomeCategoria() %> </option>
                                   
                                </select>
                                <input type="hidden" name="idCategoria" value="<%=c.getId()%>"/>
                                     <%
                                        }
                                     %>
                                 <br/>
            Inserisci chilometri: <input type="number" name="chilometri"> <br/>
            Inserisci data immatricolazione: <input type="date" name="dataImmatricolazione"> <br/>
            Inserisci colore: <input type = "text" name = "colore"> <br/>
            <input type="submit" value="Aggiungi Auto">
        </form>
    </body>
</html>
