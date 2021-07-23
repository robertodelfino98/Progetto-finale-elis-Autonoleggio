package daoInterface;

import java.util.List;

import model.Ruolo;

public interface RuoloDao {
	
	public boolean addRuolo(Ruolo ruolo);
	public Ruolo getById(Ruolo ruolo);
	public List<Ruolo> getAllRuoli();
}
