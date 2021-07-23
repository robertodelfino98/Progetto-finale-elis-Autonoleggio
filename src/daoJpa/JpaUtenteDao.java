package daoJpa;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import daoFactory.DaoFactory;
import daoFactory.JpaFactory;
import daoInterface.UtenteDao;
import model.Ruolo;
import model.Utente;

public class JpaUtenteDao implements UtenteDao {
	
	private static JpaUtenteDao instance;
	
	public JpaUtenteDao() {
		
	}
	
	public static JpaUtenteDao getInstance() {
		if(instance == null) {
			instance = new JpaUtenteDao();
		}
		return instance;
	}

	@Override
	public List<Utente> getAllUtenti() {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT u FROM Utente u");
		return q.getResultList();
			
	}

	@Override
	public boolean addUtente(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(utente);
		et.commit();
		return true;
		
	}

	@Override
	public Utente getById(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT u FROM Utente u WHERE u.id = :id");
		q.setParameter("id", utente.getId());
		return (Utente) q.getSingleResult();
		
	}

	@Override
	public boolean cambiaRuolo(Utente utente, Ruolo ruolo) {
		EntityManager em = JpaFactory.getEntityManager();		
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setRuolo(DaoFactory.getDaoFactory("jpa").getRuoloDao().getById(ruolo));

		et.begin();
		em.merge(u);
		et.commit();
		/*
		u.setRuolo(ruolo);
		Query q = em.createNativeQuery("UPDATE Utente SET fk_ruolo = :id_ruolo WHERE utente.id = :id_utente" );
		q.setParameter("id_ruolo", ruolo.getId());
		q.setParameter("id_utente", u.getId());
		*/
		return true;	
		
	}

	@Override
	public boolean aggiornaPatente(Utente utente, LocalDate dataDiRilascio, LocalDate dataDiScadenza) {
		EntityManager em = JpaFactory.getEntityManager();		
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setDataRilascioPatente(dataDiRilascio);
		u.setDataScadenzaPatente(dataDiScadenza);

		et.begin();
		em.merge(u);
		et.commit();
		
		/*
		Query q = em.createNativeQuery("UPDATE Utente SET data_rilascio_patente = :data_rilascio, data_scadenza_patente = :data_scadenza WHERE utente.id = :id_utente");
		q.setParameter("data_rilascio", Date.from(dataDiRilascio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		q.setParameter("data_scadenza", Date.from(dataDiScadenza.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		q.setParameter("id_utente", u.getId());
		*/
		

		return true;	
		
	}

	@Override
	public boolean aggiornaEmail(Utente utente, String email){
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setEmail(email);

		et.begin();
		em.merge(u);
		et.commit();

		return true;
	}

	@Override
	public boolean aggiornaPassword(Utente utente, String password){
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setPassword(password);

		et.begin();
		em.merge(u);
		et.commit();
		
		return true;
	}


	@Override
	public boolean controllaDiffida(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT u FROM Utente u WHERE u.id = :id");

		q.setParameter("id", utente.getId());

		Utente u = (Utente) q.getSingleResult();
		if(u.getDiffide() < 3 ) return true;
		return false;
	
	}

	@Override
	public boolean aggiornaDiffida(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();	
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setDiffide((byte)(u.getDiffide()+1));

		et.begin();
		em.merge(u);
		et.commit();
		/*
		Query q = em.createNativeQuery("UPDATE Utente SET diffide = :diffida WHERE utente.id = :id_utente");
		q.setParameter("diffida", (u.getDiffide()+1));
		q.setParameter("id_utente", u.getId());
		*/

		return true;
	}

	@Override
	public Utente login(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();
		Query query = em.createQuery("SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password");
		query.setParameter("email", utente.getEmail());
		query.setParameter("password", utente.getPassword());
		try{
			return (Utente)query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	@Override
	public boolean accettaAccettazione(Utente utente) {
		EntityManager em = JpaFactory.getEntityManager();	
		EntityTransaction et = em.getTransaction();
		Utente u = getById(utente);

		u.setAccettato(true);

		et.begin();
		em.merge(u);
		et.commit();

		return true;
		
		/*Query q = em.createNativeQuery("UPDATE Utente SET accettazione = :accettazione WHERE utente.id = :id_utente");
		q.setParameter("accettazione", true);
		q.setParameter("id_utente", u.getId());
		return true;
		*/
	}

	@Override
	public List<Utente> getUtentiInAttesa() {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT u FROM Utente u WHERE u.accettato = 0");
		return q.getResultList();
	}

	@Override
	public List<Utente> getUtentiAccettati() {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT u FROM Utente u WHERE u.accettato = 1");
		return q.getResultList();
	}

	@Override
	public boolean cancellaUtente (Utente utente) {
		Utente u = this.getById(utente);
		
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(u);
		et.commit();
		return true;
	}

}
