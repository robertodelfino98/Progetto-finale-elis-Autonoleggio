package daoJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daoFactory.DaoFactory;
import daoFactory.JpaFactory;
import daoInterface.AutomobileDao;
import model.Automobile;
import model.Categoria;

public class JpaAutomobileDao implements AutomobileDao {
	
	
private static JpaAutomobileDao instance;    		
	
	private JpaAutomobileDao() {
												
	}
	
	public static JpaAutomobileDao getInstance() {
		if(instance == null) {
			instance = new JpaAutomobileDao();			
		}
		return instance;
	}

	@Override
	public List<Automobile> getAllAutomobili() {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createNamedQuery("Automobile.findAll");
		return q.getResultList();
	}

	@Override
	public Automobile getById(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Automobile a WHERE a.id = :id");
		q.setParameter("id", automobile.getId());
		return (Automobile) q.getSingleResult();
	}

	@Override
	public boolean rimuoviAutomobile(Automobile automobile) {
		Automobile a = this.getById(automobile);
		
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		if(!em.contains(a)) a=em.merge(a);
		em.remove(a);
		et.commit();
		return true;
	}

	@Override
	public boolean addAutomobile(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(automobile);
		et.commit();
		return true;
	}

	@Override
	public boolean cambiaDisponibilita(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();

		automobile = getById(automobile);
		
		if(automobile.getDisponibilita()){
			automobile.setDisponibilita(false);
		} else {
			automobile.setDisponibilita(true); 
		}

		et.begin();
		em.merge(automobile);
		et.commit();

		/*Query q = em.createQuery("UPDATE Automobile SET disponibilita = :disponibile WHERE automobile.id = :id");
		q.setParameter("id", automobile.getId());
		q.setParameter("disponibile", automobile.getDisponibilita());*/
		return true;
		
	}

	@Override
	public boolean aggiungiColore(Automobile automobile, String colore) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();

		automobile = getById(automobile);

		automobile.setColore(colore);
		et.begin();
		em.merge(automobile);
		et.commit();
		 
		 
		 /*
		 em.createQuery("UPDATE Automobile SET colore = :colore WHERE automobile.id = :id")
	      .setParameter("colore", colore)
	      .setParameter("id", automobile.getId());
		 */
		 return true;
	
		
	}

	@Override
	public boolean aggiornaChilometri(Automobile automobile, int chilometri) {
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();

		automobile = getById(automobile);

		automobile.setChilometri(chilometri);
		et.begin();
		em.merge(automobile);
		et.commit();

		/*
		Query q = em.createQuery("UPDATE Automobile SET chilometri = :chilometri WHERE automobile.id = :id"); 
		q.setParameter("id", automobile.getId());
		q.setParameter("chilometri", (byte) chilometri);
		*/
		return true;
	}

	@Override

	public List<Automobile> getAutomobiliByMarca(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Automobile a WHERE marca = :marca, disponibilita = :disponibile");
		q.setParameter("marca", automobile.getMarca());
		q.setParameter("disponibile", 1);
		return q.getResultList();
	}

	@Override
	public List<Automobile> getAutomobileByCategoria(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Automobile a WHERE categoria = :categoria, disponibilita = :disponibile");
		q.setParameter("marca", automobile.getCategoria());
		q.setParameter("disponibile", 1);
		return q.getResultList();
	}

	@Override

	public List<Automobile> getAutomobileByMarcaAndCategoria(Automobile automobile) {
		EntityManager em = JpaFactory.getEntityManager();
		Query q = em.createQuery("SELECT a FROM Automobile a WHERE categoria = :categoria, marca = :marca, disponibilita = :disponibile");
		q.setParameter("categoria", automobile.getCategoria());
		q.setParameter("marca", automobile.getMarca());
		q.setParameter("disponibile", 1);
		return q.getResultList();
	}

	@Override 
	
	public boolean modificaAutomobile(Automobile autoModificata, Automobile autoOriginale){
		Categoria c;
		String marca;
		String modello;
		String colore;
		String targa;
		double chilometri;

		if(!autoModificata.getMarca().equals("")){
			marca = autoModificata.getMarca();
		}else{
			marca = autoOriginale.getMarca();
		}

		if(!autoModificata.getModello().equals("")){
			modello = autoModificata.getModello();
		}else{
			modello = autoOriginale.getModello();
		}

		if(!autoModificata.getColore().equals("")){
			colore = autoModificata.getColore();
		}else{
			colore = autoOriginale.getColore();
		}

		if(autoModificata.getChilometri() != getById(autoOriginale).getChilometri() && autoModificata.getChilometri() != 0){
			chilometri = autoModificata.getChilometri();
		}else{
			chilometri = autoOriginale.getChilometri();
		}
		
		if(!autoModificata.getTarga().equals("")){
			targa = autoModificata.getTarga();
		}else{
			targa = autoOriginale.getTarga();
		}
		
		EntityManager em = JpaFactory.getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Automobile a = DaoFactory.getDaoFactory("jpa").getAutomobileDao().getById(autoOriginale);
		a.setMarca(marca);
		a.setModello(modello);
		a.setColore(colore);
		a.setChilometri(chilometri);

		et.begin();
		em.merge(a);
		et.commit();
		return true;
	}
	
}
