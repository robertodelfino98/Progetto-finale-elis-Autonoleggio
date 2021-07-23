package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Categoria;
import model.Utente;

/**
 * Servlet implementation class PannelloStaffServlet
 */
public class PannelloStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PannelloStaffServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listaCategorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		List<Utente> listaUtenti = DaoFactory.getDaoFactory("jpa").getUtenteDao().getUtentiAccettati();
		request.setAttribute("listaUtenti", listaUtenti);
        request.setAttribute("listaCategorie", listaCategorie);
        request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUt = Integer.parseInt(request.getParameter("utenteDiffida"));
		List<Utente> listaUtenti = DaoFactory.getDaoFactory("jpa").getUtenteDao().getAllUtenti();
		Utente u = null;
		if( listaUtenti!=null ) {
			for(int i=0; i<listaUtenti.size(); i++) {	// ciclo la lista utenti
				if( idUt == listaUtenti.get(i).getId() ) {
					u = listaUtenti.get(i);
					if( u!=null ) {
						if( u.getDiffide() < 3 ) {	// aggiorna le diffide se minori di 3
							DaoFactory.getDaoFactory("jpa").getUtenteDao().aggiornaDiffida(u);
							request.setAttribute("msgErr", "Hai incrementato di 1 il numero di diffide per l'utente "+u.getNome()+" "+u.getCognome()+".");
							break;
						}
						if( u.getDiffide() >= 3 ) {	// arrivati a 3 diffide il sistema manda un messaggio di errore
							request.setAttribute("msgErr", "L'utente "+u.getNome()+" "+u.getCognome()+" ha raggiunto il limite massimo di diffide.");
							break;
						}
					}
				}
			}
		}else {
			request.setAttribute("msgErr", "Non ci sono utenti sul database.");	// se la lista è vuota
		}
		// rimando alla stessa jsp le liste aggiornate e uno dei messaggi del precedente if
		List<Categoria> listaCategorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		List<Utente> listaUtentiNuova = DaoFactory.getDaoFactory("jpa").getUtenteDao().getAllUtenti();
		request.setAttribute("listaCategorie", listaCategorie);
		request.setAttribute("listaUtenti", listaUtentiNuova);
		request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);	// in attesa del nome della jsp apposita
	}
}