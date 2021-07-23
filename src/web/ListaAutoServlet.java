package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoFactory.DaoFactory;
import model.Automobile;
import model.Categoria;
import model.Utente;

/**
 * Servlet implementation class ParcoAutoServlet
 */
public class ListaAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente u = (Utente) session.getAttribute("utente");
		List<Categoria> categorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		if( (u!=null && u.getRuolo().getNomeRuolo().equalsIgnoreCase("cliente")) || u==null ) {
			if( categorie!=null ) {
				for(int i=0; i<categorie.size(); i++) {
					if( categorie.get(i).getAutomobiles()!=null ) {
						for(int j=0; j<categorie.get(i).getAutomobiles().size(); j++) {
							if( !categorie.get(i).getAutomobiles().get(j).getDisponibilita() ) {
								categorie.get(i).getAutomobiles().remove(j);
							}
						}
					}
				}
			}
		}
		request.setAttribute("listaCategorie", categorie);
		request.getRequestDispatcher("jsp/listaAutomobile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO parco auto riporta qui (elimina categoria ed elimina auto)
		// TODO la gestione automobili va in un'altra servlet (?????????)
        // mi riprende i parametri addCategoria o modificaCattegoria 
		// se un delle e diversa da null va alla PannelloStaff.jsp

		// QUESTA SERVLET RIMANDA A DUE SERVLET DIVERSE CHIAMATE "GestisciCategoria" e "GestisciAutomobile"
		// lo switch gestisce le azioni in base alla scelta fatta nella jsp

		String scelta = request.getParameter("scelta");
		// prendo la lista delle categorie da mandare alle jsp dopo un azione ( evito duplicati di codice )
		List<Categoria> categorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		request.setAttribute("listaCategorie", categorie);

		switch(scelta) {

			case "addCategoria":
				request.setAttribute("addCategoria", "si");
				request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
				return;
	

			case "addAuto":
				request.setAttribute("addAuto", "si");
				request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
				return;
			

			case "editCategoria":
				request.setAttribute("idCat", request.getParameter("idCategoria"));
				request.setAttribute("modCategoria", "si");
				request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
				return;
		

			case "editAuto":
				request.setAttribute("idAuto", request.getParameter("idAuto"));
				request.setAttribute("modAuto", "si");
				request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
				return;
			
			
			case "cambioDisponibilita":	// questo deve essere modificato per rendere le auto disponibili o no
				Automobile a = new Automobile(Integer.parseInt(request.getParameter("idAuto")));
				a = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getById(a);
				DaoFactory.getDaoFactory("jpa").getAutomobileDao().cambiaDisponibilita(a);
				request.getRequestDispatcher("jsp/listaAutomobile.jsp").forward(request, response);
				return;
				

		}
	}
}