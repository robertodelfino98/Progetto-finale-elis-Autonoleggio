package daoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daoFactory.JpaFactory;
import daoInterface.CategoriaDao;
import model.Automobile;
import model.Categoria;
import model.Utente;

public class JpaCategoriaDao implements CategoriaDao {
	
private static JpaCategoriaDao instance;    		
	
	private JpaCategoriaDao() {
													
	}
	
	public static JpaCategoriaDao getInstance() {
		if(instance == null) {
			instance = new JpaCategoriaDao();			
		}
		return instance;
	}

	@Override
	public Categoria getById(Categoria categoria) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT c FROM Categoria c WHERE c.id = :id");
		q.setParameter("id", categoria.getId());
		return (Categoria) q.getSingleResult();
	}

	@Override
	public List<Categoria> getAllCategorie(){
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT c FROM Categoria c");
		return (List<Categoria>) q.getResultList();
	}

	@Override
	public List<Automobile> getAutomobiliByCategoria(Categoria categoria) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Automobile a INNER JOIN Categoria c ON a.fk_categoria = c.id WHERE c.nome = :nome");
		q.setParameter("nome", categoria.getNomeCategoria());
		return q.getResultList();
	}

	@Override
	public boolean aggiungiCategoria(Categoria categoria) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(categoria);
		et.commit();
		return true;
	}

	@Override
	public boolean cambiaTariffaGiornaliera(Categoria categoria, double tariffaGiornaliera) {
		EntityManager em = JpaFactory.getEntityManager();		
		EntityTransaction et = em.getTransaction();
		Categoria c = JpaCategoriaDao.getInstance().getById(categoria);
		c.setTariffaGiornaliera(tariffaGiornaliera);
		
		et.begin();
		em.merge(c);
		et.commit();
		/*
		Query q = em.createNativeQuery("UPDATE Categoria SET tariffa_giornaliera = :tariffa WHERE categoria.id = :id_categoria" );
		q.setParameter("tariffa", c.getTariffaGiornaliera());
		q.setParameter("id_categoria", c.getId());
		*/
		return true;	
	}

	@Override
	public boolean cambiaTariffaSettimanale(Categoria categoria, double tariffaSettimanale) {
		EntityManager em = JpaFactory.getEntityManager();		
		EntityTransaction et = em.getTransaction();
		Categoria c = JpaCategoriaDao.getInstance().getById(categoria);
		c.setTariffaSettimanale(tariffaSettimanale);
		
		et.begin();
		em.merge(c);
		et.commit();
		/*
		Query q = em.createNativeQuery("UPDATE Categoria SET tariffa_settimanale = :tariffa WHERE categoria.id = :id_categoria" );
		q.setParameter("tariffa", c.getTariffaSettimanale());
		q.setParameter("id_categoria", c.getId());
		*/
		return true;
	}

	@Override
	public boolean cambiaTariffaMensile(Categoria categoria, double tariffaMensile) {
		EntityManager em = JpaFactory.getEntityManager();	
		EntityTransaction et = em.getTransaction();
		Categoria c = JpaCategoriaDao.getInstance().getById(categoria);
		c.setTariffaMensile(tariffaMensile);
		
		et.begin();
		em.merge(c);
		et.commit();
		/*
		Query q = em.createNativeQuery("UPDATE Categoria SET tariffa_mensile = :tariffa WHERE categoria.id = :id_categoria" );
		q.setParameter("tariffa", c.getTariffaMensile());
		q.setParameter("id_categoria", c.getId());
		*/
		return true;
	}

	@Override
	public boolean rimuoviCategoria(Categoria categoria) {
		Categoria c = this.getById(categoria);
		
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if(!em.contains(c)) c=em.merge(c);
		em.remove(c);
		et.commit();
		return true;
	}

		@Override 
	
	public boolean modificaCategoria(Categoria categoriaModificata, Categoria categoriaOriginale){
		
		double tariffaGiornaliera;
		double tariffaSettimanale;
		double tariffaMensile;
		
		if(categoriaModificata.getTariffaGiornaliera() != getById(categoriaOriginale).getTariffaGiornaliera() && categoriaModificata.getTariffaGiornaliera() != 0){
			tariffaGiornaliera = categoriaModificata.getTariffaGiornaliera();
		}else{
			tariffaGiornaliera = getById(categoriaOriginale).getTariffaGiornaliera();
		}

		if(categoriaModificata.getTariffaSettimanale() != getById(categoriaOriginale).getTariffaSettimanale() && categoriaModificata.getTariffaSettimanale() != 0){
			tariffaSettimanale = categoriaModificata.getTariffaSettimanale();
		}else{
			tariffaSettimanale = getById(categoriaOriginale).getTariffaSettimanale();
		}

		if(categoriaModificata.getTariffaMensile() != getById(categoriaOriginale).getTariffaMensile() && categoriaModificata.getTariffaMensile() != 0){
			tariffaMensile = categoriaModificata.getTariffaMensile();
		}else{
			tariffaMensile = getById(categoriaOriginale).getTariffaMensile();
		}

		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Categoria c = JpaCategoriaDao.getInstance().getById(categoriaOriginale);
        if(categoriaModificata.getNomeCategoria() != null && !(categoriaModificata.getNomeCategoria().equals(c.getNomeCategoria()))) {
        	c.setNomeCategoria(categoriaModificata.getNomeCategoria());
        }
		c.setTariffaGiornaliera(tariffaGiornaliera);
		c.setTariffaMensile(tariffaMensile);
		c.setTariffaSettimanale(tariffaSettimanale);
		
		et.begin();
		em.merge(c);
		et.commit();

		/*
		Query q = em.createQuery("UPDATE Categoria SET tariffa_giornaliera = :tariffaGiornaliera, tariffa_settimanale = :tariffaSettimanale,"
								+"  tariffa_mensile = :tariffaMensile WHERE categoria.id = :id");
		q.setParameter("tariffaGiornaliera", tariffaGiornaliera);
		q.setParameter("tariffaSettimanale", tariffaSettimanale);
		q.setParameter("tariffaMensile", tariffaMensile);
		q.setParameter("id", categoriaOriginale.getId());
		*/
		return true;
	}

		@Override
		public Categoria getByName(Categoria categoria) {
			EntityManager em = JpaFactory.getEntityManager();
			Query q = em.createQuery("SELECT c FROM Categoria c WHERE c.nomeCategoria = :categoria");
			q.setParameter("categoria", categoria.getNomeCategoria());
			return (Categoria) q.getSingleResult();
		}
}
