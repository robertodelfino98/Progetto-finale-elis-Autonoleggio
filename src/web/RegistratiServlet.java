package web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoFactory.DaoFactory;
import model.Noleggio;
import model.Ruolo;
import model.Utente;
import java.util.Date;
import java.util.ArrayList;

/**
 * Servlet implementation class SignInServlet
 */
public class RegistratiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistratiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/registrati.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cF = request.getParameter("codiceFiscale");
		String cognome = request.getParameter("cognome");
		LocalDate dataNascita = LocalDate.parse(request.getParameter("dataNascita"));
		LocalDate dataRilascioPatente = LocalDate.parse(request.getParameter("dataRilascioPatente"));
		LocalDate dataScadenzaPatente = LocalDate.parse(request.getParameter("dataScadenzaPatente"));
		System.out.println(dataNascita);

		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String nPatente = request.getParameter("nPatente");
		String nPass = request.getParameter("password");
		String nPassConf = request.getParameter("confermaPassword");
		List<Noleggio> noleggi = new ArrayList<>();
		Ruolo cliente = DaoFactory.getDaoFactory("jpa").getRuoloDao().getById(new Ruolo(3,"cliente"));
		
		
		
		//Verifica che le due password coincidano
		if(!nPass.equals(nPassConf)){
			request.setAttribute("msgErr", "Le due password non coincidono");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request, response);
			return;
		}
		
		if(nPass.length()<8) {
			request.setAttribute("msgErr", "La password è troppo corta");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request, response);
			return;
		}
		
		//Verifica che il codice fiscale sia di 16 cifre
		if(cF.length()<14) {
			request.setAttribute("msgErr", "Il codice fiscale non è corretto");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request, response);
			return;
		}

		//controlla le date di nascita ecc
		if(dataNascita.isAfter(LocalDate.now())){
			request.setAttribute("msgErr", "Data di nascita non corretta");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request, response);
			return;
		} else if(!dataNascita.isBefore(LocalDate.now().minusYears(18))){ //TODO ricontrollare
			request.setAttribute("msgErr", "Bisogna essere maggiorenni per registrarsi");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request,response);
			return;
		}

		
		//TODO data di rilascio e di scadenza patente devono essere controllati qui o manualmente dallo staff?
		
		List<Utente>utentiEsistenti = DaoFactory.getDaoFactory("jpa").getUtenteDao().getAllUtenti();
		Utente u = new Utente(cF,cognome,dataNascita,dataRilascioPatente,dataScadenzaPatente,email,nome,nPatente,nPass,noleggi,cliente);
		boolean isIn = false;	// indica se l'utente esiste già o meno
		if(utentiEsistenti != null) {
			//se esistono due utenti 
		for(Utente ut : utentiEsistenti){
				if(ut.getRuolo().getNomeRuolo().equals("cliente") && ut.getCodiceFiscale().equals(u.getCodiceFiscale()) ){
					isIn = true;
					break;
				}
			}
		}
		if(isIn){
			request.setAttribute("msgErr", "il cliente è già presente nel database");
			request.getRequestDispatcher("jsp/registrati.jsp").forward(request, response);
		} else {
			request.setAttribute("msgErr","la sua richiesta è stata inviata per la revisione");
			DaoFactory.getDaoFactory("jpa").getUtenteDao().addUtente(u);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
			return;
		}
	}
}
