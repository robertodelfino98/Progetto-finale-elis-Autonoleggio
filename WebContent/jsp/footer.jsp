<style>
    <%@ include file = "css/footer.css"%>
</style>
<div class="footer-dark">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-3 item">
                    <h3>Services</h3>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Parco auto</a></li>
                        <li><a href="#">Area Personale</a></li>
                    </ul>
                </div>
                <div class="col-sm-6 col-md-3 item">
                    <h3>About</h3>
                    <ul>
                        <li><a href="#">Su di Noi</a></li>
                        <li><a href="#">Privaci Policy</a></li>
                        <li><a href="#">Term of Service</a></li>
                    </ul>
                </div>
                <div class="col-md-6 item text">
                    <h3>Autonoleggio ELIS</h3>
                    <p>Via tal de tali 55, Roma, Lazio</p>
                    <p><%=request.getServletContext().getInitParameter("super_email") %></p>
                    <p><%=request.getServletContext().getInitParameter("contatto_telefono")%></p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="icone_social">
                    <ul class="social-network social-circle">
                        <li><a href="#" class="icoFacebook" title="Facebook"><i class="fab fa-facebook-f"></i></a></li>
                        <li><a href="#" class="icoTwitter" title="Twitter"><i class="fab fa-twitter"></i></a></li>
                        <li><a href="#" class="icoInstagram" title="Instagram"><i class="fab instagram"></i></a></li>
                    </ul>
                </div>
            </div>
            <p class="copyright">Autonoleggio ELIS © 2021</p>
        </div>
    </footer>
</div>