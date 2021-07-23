package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Utente;
import utilities.Mailer;

/**
 * Servlet implementation class ConfermaRegistrazioneServlet
 */
public class ConfermaRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaRegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utente> listaUtentiNonApprovati = DaoFactory.getDaoFactory("jpa").getUtenteDao().getUtentiInAttesa();
		request.setAttribute("pendingList", listaUtentiNonApprovati);
		request.getRequestDispatcher("jsp/confermaUtente.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String approvazione = request.getParameter("scelta");	// prende il tipo di scelta da parte dello staff che accetta o meno il nuovo utente
		
		
		int utId = Integer.parseInt(request.getParameter("utId"));
		Utente ut = new Utente(utId); 
		ut = DaoFactory.getDaoFactory("jpa").getUtenteDao().getById(ut);

		//info email
		String to = ut.getEmail();
		String subject="Esito Registrazione";
		String msg = "";

		switch(approvazione){
			case "accettato":
				DaoFactory.getDaoFactory("jpa").getUtenteDao().accettaAccettazione(ut);
				request.setAttribute("msgService", "Utente accettato");
	
				msg="Gentile "+ut.getNome()+" "+ut.getCognome()+", \n"+"La informiamo che la sua"+
				" registrazione è stata confermata.";
				break;

			case "rifiutato":
				DaoFactory.getDaoFactory("jpa").getUtenteDao().cancellaUtente(ut);
				request.setAttribute("msgService", "Utente rifiutato");
				//TODO gli utenti non possono essere eliminati dal db (Cannot delete or update a parent row)
				msg="Gentile "+ut.getNome()+" "+ut.getCognome()+", \n"+"La informiamo che la sua"+
				" registrazione è stata rifiutata.";
				break;
			default:
				System.out.println(approvazione);
				break;
		}

		//invio mail
		ServletContext context = getServletContext();
		Mailer.send(to, subject, msg, context.getInitParameter("super_email"), context.getInitParameter("super_password"));

		// si riprende la lista degli utenti in attesa di approvazione e la si ritorna alla stessa pagina
		List<Utente> listaUtentiNonApprovati = DaoFactory.getDaoFactory("jpa").getUtenteDao().getUtentiInAttesa();
		request.setAttribute("pendingList", listaUtentiNonApprovati);
		request.getRequestDispatcher("jsp/confermaUtente.jsp").forward(request, response);
	}

}
