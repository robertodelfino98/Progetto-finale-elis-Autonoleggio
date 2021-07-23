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
import model.Automobile;
import model.Categoria;

/**
 * Servlet implementation class AggiungiAutoServervlet
 */

public class AggiungiAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiAutoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Categoria> listaCategorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		request.setAttribute("listaCategorie", listaCategorie);
		request.getRequestDispatcher("jsp/aggiungiAuto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		LocalDate dataImmatricolazione = LocalDate.parse(request.getParameter("dataImmatricolazione"));
		Categoria categoria = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getById(new Categoria(idCategoria));
		Automobile a= new Automobile(categoria,  //mettere id della categoria
						request.getParameter("marca"),
						request.getParameter("modello"),
						request.getParameter("colore"),
						Double.parseDouble(request.getParameter("chilometri")),
						request.getParameter("targa"),
						dataImmatricolazione);
		if(DaoFactory.getDaoFactory("jpa").getAutomobileDao().addAutomobile(a)){
			  response.sendRedirect("Home"); 
		  }else { //TODO addAutomobile() non sarà mai false
			  String msg = "Campi non inseriti correttamente!";
			  request.setAttribute("esito", msg);
			  request.getRequestDispatcher("jsp/AggiungiAuto.jsp").forward(request, response);
		  }
		
		
	}

}
