package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoFactory.DaoFactory;
import model.Noleggio;
import model.Utente;
import utilities.ScritturaSuFile;

import java.util.List;
import java.util.stream.Collectors;
public class ImpostazioniAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ImpostazioniAdminServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Noleggio> listaNoleggi = DaoFactory.getDaoFactory("jpa").getNoleggioDao().getAllNoleggi();
		request.setAttribute("listaNoleggi", listaNoleggi);
		
		List<Utente> listaUtenti = DaoFactory.getDaoFactory("jpa").getUtenteDao().getAllUtenti();
		
		List<Utente>listaStaff = listaUtenti.stream()
		.filter(u -> u.getRuolo().getNomeRuolo().equals("staff"))
		.collect(Collectors.toList());

		request.setAttribute("listaStaff", listaStaff);
		
		request.getRequestDispatcher("jsp/impostazioniAdmin.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
			case "deleteStaff":
				Utente u = new Utente (Integer.parseInt(request.getParameter("idStaff")));
				DaoFactory.getDaoFactory("jpa").getUtenteDao().cancellaUtente(u);
				break;

			case "modificaCapienza":
				ScritturaSuFile.scritturaCapienza(Integer.parseInt(request.getParameter("capienzaAuto")));
				break;

			case "modificaPenale":
				ScritturaSuFile.scritturaPenale(Integer.parseInt(request.getParameter("valoreMora")));
				break;
				
			case "modificaNomeAutonoleggio":
				ScritturaSuFile.scritturaNomeAutonoleggio(request.getParameter("nomeAutonoleggio"));
				break;
				
			case "annulamentoGiorniNoleggio":
				ScritturaSuFile.scritturaGiornoAnnulamentoNoleggi(Integer.parseInt(request.getParameter("valoreGiorni")));
				break;
		}
		response.sendRedirect("impostazioni");
	}
}
