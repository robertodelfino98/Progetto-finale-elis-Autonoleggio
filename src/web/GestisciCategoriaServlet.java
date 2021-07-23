package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Categoria;

/**
 * Servlet implementation class GestisciCategoriaServlet
 */


public class GestisciCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciCategoriaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scelta = request.getParameter("scelta");
		Categoria categoria;
		String nomeCategoria = request.getParameter("nomeCategoria");

		switch(scelta){
			case "aggiungiCategoria":
			 	categoria = new Categoria(request.getParameter("nome"),
											Double.parseDouble(request.getParameter("prezzo_giornaliero")),
											Double.parseDouble(request.getParameter("prezzo_settimanale")),
											Double.parseDouble(request.getParameter("prezzo_mensile")));
				
				if(DaoFactory.getDaoFactory("jpa").getCategoriaDao().aggiungiCategoria(categoria)){
					response.sendRedirect("listaauto"); 
				}else { //TODO addAutomobile() non sarà mai false
					String msg = "Campi non inseriti correttamente!";
					request.setAttribute("esito", msg);
					request.getRequestDispatcher("jsp/pannelloStaff.jsp").forward(request, response);
				}
			break;

			case "modificaCategoria":
				int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

				String pG = request.getParameter("prezzo_giornaliero");
				String pS = request.getParameter("prezzo_settimanale");
				String pM = request.getParameter("prezzo_mensile");
				double prezzoGiornaliero = 0D;
				double prezzoSettimanale = 0D;
				double prezzoMensile = 0D;
				
				if(pG != null && !(pG.equals(""))) {
					prezzoGiornaliero = Double.parseDouble(pG);
				}
				if(pS != null && !(pS.equals(""))) {
					prezzoSettimanale = Double.parseDouble(pS);
				}
				if(pM != null && !(pM.equals(""))) {
					prezzoMensile = Double.parseDouble(pM);
				}
				categoria = new Categoria(
					nomeCategoria,
					prezzoGiornaliero, prezzoSettimanale, prezzoMensile);
				
				Categoria c1 = new Categoria(idCategoria);

				DaoFactory.getDaoFactory("jpa").getCategoriaDao().modificaCategoria(categoria, c1);
				response.sendRedirect("listaauto"); 
				
			break;

		}
	}

}
