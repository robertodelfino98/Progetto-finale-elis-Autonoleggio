package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoFactory.DaoFactory;
import model.Categoria;
import model.Ruolo;
import model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String email = request.getParameter("email"); 
		String password = request.getParameter("pass");
		String sadminEmail = context.getInitParameter("super_email");
		String sadminPass = context.getInitParameter("super_password");
		List<Categoria> categorie = DaoFactory.getDaoFactory("jpa").getCategoriaDao().getAllCategorie();
		request.setAttribute("listaCategorie", categorie);
		
		//Utente utente = new Utente(password, email); 
	
		// creato l'utente si controlla se non � null e se le credenziali non siano uguali a quelle dell'admin
	if(email!=null && password!=null) {
			if( (email.equals(sadminEmail)) && (password.equals(sadminPass)) ) {
				Utente utente = new Utente(password, email);
				utente.setRuolo(DaoFactory.getDaoFactory("jpa").getRuoloDao().getById(new Ruolo(1)));
				//utente.setAccettato(true);
				HttpSession session = request.getSession();
				session.setAttribute("utente", utente);
				Cookie ck = new Cookie("utenteEmail", utente.getEmail());
				response.addCookie(ck);
				request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
				
			}else {
				Utente utente = new Utente(password, email);
				utente = DaoFactory.getDaoFactory("jpa").getUtenteDao().login(utente);
				if(utente!=null) {
					// si controlla se l'utente non admin � approvato
					if(utente.getAccettato()  && DaoFactory.getDaoFactory("jpa").getUtenteDao().controllaDiffida(utente)) { 
						//Se l'utente u � stato trovato nel database ed � approvato
						HttpSession session = request.getSession();
						session.setAttribute("utente", utente);
						Cookie ck = new Cookie("utenteEmail", utente.getEmail());
						response.addCookie(ck);
						String loggaNoleggio = (String)session.getAttribute("loggaNoleggio");
						if(loggaNoleggio != null && loggaNoleggio.equals("si")) {
							request.getRequestDispatcher("jsp/noleggio.jsp").forward(request,response);
							return;
						}
						request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
						
					} else if(utente.getAccettato() && !DaoFactory.getDaoFactory("jpa").getUtenteDao().controllaDiffida(utente)) { //se l'utente è stato bannato
						request.setAttribute("msgErr", "Ci dispiace informarla che il suo account è stato chiuso.");
						request.getRequestDispatcher("jsp/index.jsp").forward(request,response);
					}
					 //se lutente tenta di loggarsi ma non � approvato
					else {
						request.setAttribute("msgErr", "La tua registrazione � in fase di revisione");
						request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
						
					}
				}
				//Se nessun utente � stato trovato 
				else {
					request.setAttribute("msgErr", "User non esistente");
					request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
					
				}
			}
		}else if((email==null || email.equals("")) && (password==null || password.equals(""))) {
			request.setAttribute("msgErr", "Inserire credenziali");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);	
			
		}
		
		
		/* VECCHIO CODICE
		String email = request.getParameter("email"); 
		String password = request.getParameter("pass"); 
		Utente utente = new Utente(password, email); 
	
		Utente u = DaoFactory.getDaoFactory("jpa").getUtenteDao().login(utente);
		
		ServletContext context = getServletContext();
		String sadminEmail = ;
		String sadminPass = ;

		HttpSession sessione = request.getSession();


		Utente superAdmin;

		if(u != null && u.getAccettato()) {
			//Se l'utente u � stato trovato nel database
			
			sessione.setAttribute("utente", u);
			Cookie ck = new Cookie("utente", u.getNome()+" "+u.getCognome());
			response.addCookie(ck);

			//
			//if l'utente viene dalla conferma del noleggio {
			//	response.sendRedirect("jsp/ConfermaNoleggio");
			// } else {
			response.sendRedirect("jsp/index.jsp");
			// }
		} else if(u != null && u.getAccettato()) {
			request.setAttribute("msgErr", "La tua registrazione � in fase di revisione");
			request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
		} else if(u==null && (email.equals(sadminEmail) && password.equals(sadminPass))) {
			superAdmin = new Utente(sadminEmail,sadminPass);
			//superAdmin.setRuolo(new Ruolo("superadmin"));
			sessione.setAttribute("utente",superAdmin);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		} else {
			//Se nessun utente � stato trovato 
			request.setAttribute("msgErr", "User non esistente");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
		*/
	}
}