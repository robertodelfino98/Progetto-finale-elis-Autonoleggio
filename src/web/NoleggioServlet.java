package web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;

/**
 * Servlet implementation class NoleggioServlet
 */
public class NoleggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoleggioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/noleggio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Automobile a = new Automobile(Integer.parseInt(request.getParameter("noleggio")));
		Automobile a1 = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getById(a);
		request.getSession().setAttribute("autoNoleggiata", a1);
		LocalDate dataInizioNoleggio = (LocalDate)(request.getSession().getAttribute("dataInizio"));
		LocalDate dataFineNoleggio = (LocalDate)(request.getSession().getAttribute("dataFine"));
		double prezzo = (double)request.getSession().getAttribute("prezzoStimato");
		if(!a1.getNeopatentati()) {
			Utente u = (Utente)request.getSession().getAttribute("utente");
			int neopatentato = LocalDate.now().getYear() - u.getDataRilascioPatente().getYear(); 
			if(neopatentato <2) {
				request.setAttribute("msgErr", "Auto non disponibile per neopatentati");
				request.getRequestDispatcher("jsp/noleggio.jsp").forward(request, response);
				return;
			}else {
				Noleggio n = new Noleggio(dataFineNoleggio, dataInizioNoleggio,(Utente)request.getSession().getAttribute("utente"),a1,prezzo);		
				DaoFactory.getDaoFactory("jpa").getNoleggioDao().effettuaNoleggio(n);
				response.sendRedirect("cartadicredito");
				return;
			}
		}
		Noleggio n = new Noleggio(dataFineNoleggio, dataInizioNoleggio,(Utente)request.getSession().getAttribute("utente"),a1,prezzo);		
		DaoFactory.getDaoFactory("jpa").getNoleggioDao().effettuaNoleggio(n);
		response.sendRedirect("cartadicredito");
	}
	
	



}
