<%@include file="navbar.jsp"%>
<style>
    <%@include file="css/login.css"%>
</style>

<h3></h3>

<div class="container" id="container-login">
    <div class="row" id="row-card">
        <div class="col-12" id="col-card">
            <h1>LOGIN</h1>
            <%String msg = (String) request.getAttribute("msgErr");
    			if(msg != null){
			%>
            <p class="alert alert-danger"><%=msg%></p>
            <%
    			}
			%>
            <p class="text-muted"> Please enter your login and password!</p>
            <form action="login" method="post">
                <div>
                    <input type="text" name="email" placeholder="email" required>
                </div>
                <div>
                    <input type="password" name="pass" placeholder="Password" required>
                </div>
           
            <div>
                <a class="forgot text-muted" href="registrati">Non sei ancora registrato? Clicca qui</a>
            </div>
            <div>
                <input type="hidden" name="test" value="test"> <!--test per l'homepage-->
                <input type="submit" value="Login">
            </div>
            </form>
            <div class="col-md-12">
                <ul class="social-network social-circle">
                    <li><a href="#" class="icoFacebook" title="Facebook"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a href="#" class="icoTwitter" title="Twitter"><i class="fab fa-twitter"></i></a></li>
                    <li><a href="#" class="icoInstagram" title="Instagram"><i class="fab instagram"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@include file = "footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
        crossorigin="anonymous"></script>
</body>
</html>