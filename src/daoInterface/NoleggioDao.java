package daoInterface;

import java.time.LocalDate;
import java.util.List;

import model.Automobile;
import model.Noleggio;
import model.Utente;

public interface NoleggioDao {

	public Noleggio getById(Noleggio noleggio);
	public boolean effettuaNoleggio(Noleggio noleggio);
	List<Noleggio> getAllNoleggi();
	public boolean aggiornaDataDiConsegna(Noleggio noleggio);
	public boolean avvia(Noleggio noleggio);
	public boolean termina(Noleggio noleggio);
	public boolean eliminaNoleggio(Noleggio noleggio);
	public boolean aggiornaCostoTotale(Noleggio noleggio, double costo);
}
