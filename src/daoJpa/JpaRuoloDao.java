package daoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import daoFactory.JpaFactory;
import daoInterface.RuoloDao;
import model.Ruolo;

public class JpaRuoloDao implements RuoloDao {
	
	private static JpaRuoloDao instance;
	
	public JpaRuoloDao() {
		
	}
	
	public static JpaRuoloDao getInstance() {
		if(instance == null) {
			instance = new JpaRuoloDao();
		}
		return instance;
	}

	@Override
	public boolean addRuolo(Ruolo ruolo) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(ruolo);
		transaction.commit();
		return true;
	}

	@Override
	public Ruolo getById(Ruolo ruolo) {
		
		EntityManager em = JpaFactory.getEntityManager();
		Query query = em.createQuery("SELECT r FROM Ruolo r WHERE r.id = :id");
		query.setParameter("id", ruolo.getId());
		
		return (Ruolo) query.getSingleResult();
	}

	@Override
	public List<Ruolo> getAllRuoli() {
		EntityManager em = JpaFactory.getEntityManager();
		Query query = em.createQuery("SELECT r FROM Ruolo r");
		
		return (List<Ruolo>) query.getSingleResult();
	}

}
