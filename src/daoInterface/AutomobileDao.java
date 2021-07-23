package daoInterface;

import java.util.List;

import model.Automobile;

public interface AutomobileDao {
	public List<Automobile> getAllAutomobili();
	public Automobile getById(Automobile automobile);
	
	public boolean rimuoviAutomobile(Automobile automobile);
	public boolean addAutomobile(Automobile automobile);
	
	public boolean cambiaDisponibilita(Automobile automobile);
	public boolean aggiungiColore(Automobile automobile, String colore);
	public boolean aggiornaChilometri( Automobile automobile, int chilometri);
	public boolean modificaAutomobile(Automobile autoModificata, Automobile autoOriginale);
	List<Automobile> getAutomobileByCategoria(Automobile automobile);
	List<Automobile> getAutomobileByMarcaAndCategoria(Automobile automobile);
	List<Automobile> getAutomobiliByMarca(Automobile automobile);
}
