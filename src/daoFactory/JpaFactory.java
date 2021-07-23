package daoFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import daoInterface.AutomobileDao;
import daoInterface.CategoriaDao;
import daoInterface.NoleggioDao;
import daoInterface.RuoloDao;
import daoInterface.UtenteDao;
import daoJpa.JpaAutomobileDao;
import daoJpa.JpaCategoriaDao;
import daoJpa.JpaNoleggioDao;
import daoJpa.JpaRuoloDao;
import daoJpa.JpaUtenteDao;

public class JpaFactory extends DaoFactory {
	
	
	public static EntityManager getEntityManager() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Autonoleggio");
		EntityManager em = emf.createEntityManager();
		return em;
		
	}
	
	
	@Override
	public AutomobileDao getAutomobileDao() {
		// TODO Auto-generated method stub
		return JpaAutomobileDao.getInstance();
	}

	@Override
	public CategoriaDao getCategoriaDao() {
		// TODO Auto-generated method stub
		return JpaCategoriaDao.getInstance();
	}

	@Override
	public NoleggioDao getNoleggioDao() {
		// TODO Auto-generated method stub
		return JpaNoleggioDao.getInstance();
	}

	@Override
	public RuoloDao getRuoloDao() {
		// TODO Auto-generated method stub
		return JpaRuoloDao.getInstance();
	}

	@Override
	public UtenteDao getUtenteDao() {
		// TODO Auto-generated method stub
		return JpaUtenteDao.getInstance();
	}

}
