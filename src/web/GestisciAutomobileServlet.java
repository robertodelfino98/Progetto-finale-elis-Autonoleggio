package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import utilities.ScritturaSuFile;
import model.Automobile;
import java.time.LocalDate;
import java.util.List;

import daoFactory.DaoFactory;

/**
 * Servlet implementation class GestisciAutomobileServlet
 */

public class GestisciAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciAutomobileServlet() {
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

		int scelta = Integer.parseInt(request.getParameter("scelta"));
		
		Automobile a;
		
		List<Automobile>auto = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getAllAutomobili();
		
		switch(scelta){

			case 1:
				LocalDate dataImmatricolazione = LocalDate.parse(request.getParameter("dataImmatricolazione"));
				String nomeCategoria = request.getParameter("nomeCategoria");
				Categoria categoria = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getByName(new Categoria(nomeCategoria));
				a = new Automobile(categoria, 
								request.getParameter("marca"),
								request.getParameter("modello"),
								request.getParameter("colore"),
								Double.parseDouble(request.getParameter("chilometri")),
								request.getParameter("targa"),
								dataImmatricolazione);
				
				if( (auto.size()+1) <ScritturaSuFile.letturaCapienza()){
					DaoFactory.getDaoFactory("jpa").getAutomobileDao().addAutomobile(a);
					response.sendRedirect("listaauto");
					return;
				} else { 
					String msg = "Il numero di macchine ha raggiunto il limite di capienza";
					request.setAttribute("esito", msg);
					response.sendRedirect("listaauto");
					return;
				}
			case 2:
				int idAuto = Integer.parseInt(request.getParameter("idAuto"));
				Categoria c;
				Automobile automobile = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getById(new Automobile(idAuto));
				if(!automobile.getCategoria().getNomeCategoria().equals(request.getParameter("nomeCategoria"))) {
			
					c = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getByName(new Categoria(request.getParameter("nomeCategoria")));
				}else {
					
					c = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getByName(new Categoria(automobile.getCategoria().getNomeCategoria()));
				}
				a = new Automobile(c,  
						request.getParameter("marca"),
						request.getParameter("modello"),
						request.getParameter("colore"),
						Double.parseDouble(request.getParameter("chilometri")),
						request.getParameter("targa"),
								automobile.getDataImmatricolazione());
				Automobile a1 = new Automobile(idAuto);
				if(DaoFactory.getDaoFactory("jpa").getAutomobileDao().modificaAutomobile(a, a1)){
					response.sendRedirect("listaauto"); 
				}else { //TODO addAutomobile(a) non sarà mai false!
					String msg = "Campi non inseriti correttamente!";
					request.setAttribute("esito", msg);
					response.sendRedirect("listaauto");
					return;
				}

			break;

		}
		
		
	}

}
