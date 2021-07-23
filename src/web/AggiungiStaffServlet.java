package web;
import utilities.Mailer;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoFactory.DaoFactory;
import model.Utente;
import utilities.Utilities;
import model.Ruolo;
public class AggiungiStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AggiungiStaffServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/creaStaff.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = Utilities.generatePassword();
		Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setRuolo(DaoFactory.getDaoFactory("jpa").getRuoloDao().getById(new Ruolo(2)));   // 2 = staff
        utente.setAccettato(true);
        DaoFactory.getDaoFactory("jpa").getUtenteDao().addUtente(utente);

        // TODO va inviata l'email all'utente staff con le credenziali di accesso
        // invio email
		String to = email;
		String subject="Credenziali Di Accesso";
		String msg="Le sue credenziali di accesso: \nEmail: "+email+"\nPassword: "+password+"\nBenvenuto.";
		
        ServletContext context = getServletContext();
		Mailer.send(to, subject, msg, context.getInitParameter("super_email"), context.getInitParameter("super_password"));

        request.getRequestDispatcher("jsp/impostazioniAdmin.jsp").forward(request, response);
	}
}
