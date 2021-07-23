package web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Utente;
import model.Noleggio;

/**
 * Servlet implementation class InfoUtenteServlet
 */
public class InfoUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoUtenteServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = (Utente)request.getSession().getAttribute("utente");
		if(u.getRuolo().getNomeRuolo().equals("cliente")) {
			request.setAttribute("listaNoleggi",u.getNoleggios());
		}else {
			List<Noleggio> noleggi =  DaoFactory.getDaoFactory("jpa").getNoleggioDao().getAllNoleggi();
			request.setAttribute("listaNoleggi", noleggi);
		}
		request.getRequestDispatcher("jsp/infoUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scelta = request.getParameter("scelta");
		
		Utente u = new Utente(Integer.parseInt(request.getParameter("uId")));
		u = DaoFactory.getDaoFactory("jpa").getUtenteDao().getById(u);

		switch (scelta) {
		case "modPass":
			DaoFactory.getDaoFactory("jpa").getUtenteDao().aggiornaEmail(u, request.getParameter("password"));
			break;

		case "modEmail":
			DaoFactory.getDaoFactory("jpa").getUtenteDao().aggiornaEmail(u, request.getParameter("email"));
			break;

		case "annulla":
			Noleggio n = new Noleggio(Integer.parseInt(request.getParameter("nolId")));
			DaoFactory.getDaoFactory("jpa").getNoleggioDao().eliminaNoleggio(n);
			response.sendRedirect("Home");
			break;
		}
		
	}

}
