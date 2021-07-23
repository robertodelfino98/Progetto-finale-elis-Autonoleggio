package daoInterface;

import java.util.List;

import model.Automobile;
import model.Categoria;

public interface CategoriaDao {
	public Categoria getById(Categoria categoria);
	public List<Categoria> getAllCategorie();
	public List<Automobile> getAutomobiliByCategoria(Categoria categoria);
	public Categoria getByName(Categoria categoria); 
	public boolean aggiungiCategoria(Categoria categoria);
	public boolean cambiaTariffaGiornaliera(Categoria categoria, double tariffaGiornaliera);
	public boolean cambiaTariffaSettimanale(Categoria categoria, double tariffaSettimanale);
	public boolean cambiaTariffaMensile(Categoria categoria, double tariffaMensile);
	public boolean rimuoviCategoria(Categoria categoria);
	public boolean modificaCategoria(Categoria categoriaModificata, Categoria categoriaOriginale);
}
