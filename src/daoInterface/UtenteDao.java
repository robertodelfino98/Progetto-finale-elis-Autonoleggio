package daoInterface;

import java.time.LocalDate;
import java.util.List;

import model.Ruolo;
import model.Utente;

public interface UtenteDao {
	public List<Utente> getAllUtenti();
	public List<Utente> getUtentiInAttesa();
	public List<Utente> getUtentiAccettati();

	
	public boolean addUtente(Utente utente);
	public boolean cancellaUtente(Utente utente);
	public Utente login(Utente utente);
	
	public Utente getById(Utente utente);
	public boolean cambiaRuolo(Utente utente, Ruolo ruolo);
	
	public boolean aggiornaPatente(Utente utente, LocalDate dataDiRilascio, LocalDate dataDiScadenza);
	public boolean controllaDiffida(Utente utente);
	public boolean aggiornaDiffida(Utente utente);
	public boolean accettaAccettazione(Utente utente);
	public boolean aggiornaEmail(Utente utente, String email);
	public boolean aggiornaPassword(Utente utente, String password);
}
