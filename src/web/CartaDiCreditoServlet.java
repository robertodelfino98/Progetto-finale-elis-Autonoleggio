package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.smtp.SMTPTransport;

import model.Automobile;
import model.Utente;
import utilities.Mailer;

/**
 * Servlet implementation class CartaDiCreditoServlet
 */

public class CartaDiCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartaDiCreditoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/cartadicredito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		Utente u = (Utente)sessione.getAttribute("utente");

		double prezzoStimato = (double)sessione.getAttribute("prezzoStimato");
		LocalDate dataInizio = (LocalDate)(sessione.getAttribute("dataInizio"));
		LocalDate dataFine = (LocalDate)(sessione.getAttribute("dataFine"));
		Automobile a = (Automobile)sessione.getAttribute("autoNoleggiata");

		//TODO aggiungere info del noleggio

		// invio email
		String to = u.getEmail();
		String subject="Informazioni Noleggio";
		String msg="Gentile "+u.getNome()+" "+u.getCognome()+", \n"+"La informiamo che il suo"+
		"noleggio è andato a buon fine con queste informazioni:\n"+
		"Automobile: "+a.getModello()+", "+a.getMarca()+"\nTarga: "+a.getTarga()+
		"\nPrezzo Stimato: "+prezzoStimato+"\nData Inizio: "+dataInizio+"\nData Fine: "+dataFine+
		"\n\nLe auguriamo una buona giornata.";
		
		ServletContext context = getServletContext();
		Mailer.send(to, subject, msg, context.getInitParameter("super_email"), context.getInitParameter("super_password"));
		response.sendRedirect("Home");
	}
}