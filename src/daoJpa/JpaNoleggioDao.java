package daoJpa;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daoFactory.JpaFactory;
import daoInterface.NoleggioDao;
import model.Automobile;
import model.Noleggio;
import model.Utente;

public class JpaNoleggioDao implements NoleggioDao {
	
	private static JpaNoleggioDao instance;
	
	private JpaNoleggioDao() {
		
	}
	
	public static JpaNoleggioDao getInstance() {
		
		if(instance == null) {
			instance = new JpaNoleggioDao();
		}
		return instance;
		
	}

	@Override
	public Noleggio getById(Noleggio noleggio) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT n FROM Noleggio n WHERE n.id = :id");
		q.setParameter("id", noleggio.getId());
		return (Noleggio) q.getSingleResult();
	}

	@Override
	public boolean effettuaNoleggio(Noleggio noleggio) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(noleggio);
		et.commit();
		return true;
	}

	@Override
	public List<Noleggio> getAllNoleggi() {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT n FROM Noleggio n");
		return q.getResultList();
	}

	@Override
	public boolean avvia(Noleggio noleggio) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Noleggio n = getById(noleggio);

		n.setAvviato(true);

		et.begin();
		em.merge(n);
		et.commit();
		/*
		Query q = em.createNativeQuery("UPDATE Noleggio SET avviato = :avviato WHERE noleggio.id = :id_noleggio");
		q.setParameter("avviato", (1));
		q.setParameter("id_noleggio", noleggio.getId());
		*/
		return true;
	}

	@Override
	public boolean termina(Noleggio noleggio) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Noleggio n = getById(noleggio);

		n.setTerminato(true);

		et.begin();
		em.merge(n);
		et.commit();
		
		/*
		Query q = em.createNativeQuery("UPDATE Noleggio SET terminato = :terminato WHERE noleggio.id = :id_noleggio");
		q.setParameter("terminato", (1));
		q.setParameter("id_noleggio", noleggio.getId());
		*/
		
		return true;
	}

	@Override
	public boolean aggiornaDataDiConsegna(Noleggio noleggio){
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Noleggio n = getById(noleggio);

		n.setDataConsegna(noleggio.getDataConsegna());

		et.begin();
		em.merge(n);
		et.commit();
		
		/*
		Query q = em.createNativeQuery("UPDATE Noleggio SET data_consegna = :dataConsegna WHERE noleggio.id = :id");
		q.setParameter("dataConsegna", noleggio.getDataConsegna());
		q.setParameter("id", noleggio.getId());
		*/
		
		return true;
	}

	@Override
	public boolean aggiornaCostoTotale(Noleggio noleggio, double costo) {
		Noleggio n = this.getById(noleggio);

		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();

		n.setCostoTotale(costo);

		et.begin();
		em.merge(n);
		et.commit();
		return true;
	}

	@Override
	public boolean eliminaNoleggio(Noleggio noleggio) {
		Noleggio n = this.getById(noleggio);
		
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if(!em.contains(n)) n=em.merge(n);
		em.remove(n);
		et.commit();
		return true;
	}
}
