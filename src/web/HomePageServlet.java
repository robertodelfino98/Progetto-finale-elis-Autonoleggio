package web;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import daoFactory.DaoFactory;
import daoFactory.JpaFactory;
import model.Automobile;
import model.Categoria;
import utilities.Utilities;

/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categoria> categorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		request.setAttribute("listaCategorie", categorie);
		request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int idCat = Integer.parseInt(request.getParameter("categoria"));
			LocalDate dI= LocalDate.parse((String)request.getParameter("dataInizio"));
			LocalDate dF= LocalDate.parse((String)request.getParameter("dataFine"));
			List<Automobile> automobiliFiltrate = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getAllAutomobili();
			if( (dI.isAfter(LocalDate.now()) && (dF.isAfter(dI))) ) {
				if(automobiliFiltrate.size() > 0) {
					automobiliFiltrate.removeIf(a ->{
						for (int i = 0; i < a.getNoleggios().size(); i++) {
							if((a.getNoleggios().get(i).getDataInizio().equals(dI) ||  (dI.isAfter(a.getNoleggios().get(i).getDataInizio())
									&& dI.isBefore(a.getNoleggios().get(i).getDataFine()))) || !a.getDisponibilita()){
								return true;
							}
						}
						return false;
					});
					double prezzoStimato = Utilities.calcoloPrezzoStimato(dI, dF, JpaFactory.getDaoFactory("jpa").getCategoriaDao().getById(new Categoria(idCat)));
					HttpSession session = request.getSession();
					session.setAttribute("prezzoStimato", prezzoStimato);
					session.setAttribute("listaAuto", automobiliFiltrate);
					session.setAttribute("dataInizio", dI);
					session.setAttribute("dataFine", dF);
					request.getRequestDispatcher("jsp/noleggio.jsp").forward(request, response);
				} else {
					String msg = "non ci sono automobili disponibili al momento";
					request.setAttribute("msgErr", msg);
					request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
				} 
			} else {
				String msg = "non puoi inserire una data prima o uguale a oggi come data di inizio o una data di fine precedente a quella di inizio";
				request.setAttribute("msgErr", msg);
				request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
			}
			
	}
}
