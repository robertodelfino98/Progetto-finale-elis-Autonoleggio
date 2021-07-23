package web;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Noleggio;
import utilities.ScritturaSuFile;

/**
 * Servlet implementation class ListaNoleggioServlet
 */
public class ListaNoleggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaNoleggioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Noleggio>noleggi = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getAllNoleggi();

		//Riprende tutti i noleggi non ancora terminati
		List<Noleggio>noleggiNonTerminati = noleggi.stream().
			filter(n -> !n.isTerminato()).collect(Collectors.toList());
			
		request.setAttribute("listaNoleggi", noleggiNonTerminati);
		request.getRequestDispatcher("jsp/listaNoleggio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("avvia") != null) {
			int idavviato = Integer.parseInt(request.getParameter("avvia"));
			Noleggio n = new Noleggio(idavviato);
			Noleggio noleggioAvviato = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getById(n);
			noleggioAvviato.setAvviato(true);
			DaoFactory.getDaoFactory("jpa").getNoleggioDao().avvia(noleggioAvviato);
			List<Noleggio>noleggi = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getAllNoleggi();

			//Riprende tutti i noleggi non ancora terminati
			List<Noleggio>noleggiNonTerminati = noleggi.stream().
				filter(n1 -> !n.isTerminato()).collect(Collectors.toList());
				
			request.setAttribute("listaNoleggi", noleggiNonTerminati);
			request.getRequestDispatcher("jsp/listaNoleggio.jsp").forward(request, response);
		}else if(request.getParameter("termina") != null && 
			DaoFactory.getDaoFactory("jpa").getNoleggioDao().getById(new Noleggio(Integer.parseInt(request.getParameter("termina")))).isAvviato()) {
			int idNolleggioTerminato = Integer.parseInt(request.getParameter("termina"));
			Noleggio n = new Noleggio(idNolleggioTerminato);
			Noleggio noleggioTerminato = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getById(n);
			noleggioTerminato.setTerminato(true);
			LocalDate dataConsegna = LocalDate.now();
			noleggioTerminato.setDataConsegna(dataConsegna);
			DaoFactory.getDaoFactory("jpa").getNoleggioDao().termina(noleggioTerminato);
			DaoFactory.getDaoFactory("jpa").getNoleggioDao().aggiornaDataDiConsegna(noleggioTerminato);
			if(dataConsegna.isAfter(noleggioTerminato.getDataFine())) {
				double mora = ScritturaSuFile.letturaPenale();
				double prezzoAggiornato = noleggioTerminato.getCostoTotale() + ((noleggioTerminato.getCostoTotale()*mora)/100);
				DaoFactory.getDaoFactory("jpa").getNoleggioDao().aggiornaCostoTotale(noleggioTerminato, prezzoAggiornato);
				request.setAttribute("prezzoTot", prezzoAggiornato);
			}else{
				request.setAttribute("prezzoTot", noleggioTerminato.getCostoTotale());
			}
			List<Noleggio>noleggi = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getAllNoleggi();

			//Riprende tutti i noleggi non ancora terminati
			List<Noleggio>noleggiNonTerminati = noleggi.stream().
				filter(n1 -> !n.isTerminato()).collect(Collectors.toList());
				
			request.setAttribute("listaNoleggi", noleggiNonTerminati);
			request.getRequestDispatcher("jsp/listaNoleggio.jsp").forward(request, response);
		}	
	}
}
