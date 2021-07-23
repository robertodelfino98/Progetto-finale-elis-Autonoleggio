package daoFactory;

import daoInterface.AutomobileDao;
import daoInterface.CategoriaDao;
import daoInterface.NoleggioDao;
import daoInterface.RuoloDao;
import daoInterface.UtenteDao;

public abstract class DaoFactory {
	public abstract AutomobileDao getAutomobileDao();
	public abstract CategoriaDao getCategoriaDao();
	public abstract NoleggioDao getNoleggioDao();
	public abstract RuoloDao getRuoloDao();
	public abstract UtenteDao getUtenteDao();
	
	
	public static DaoFactory getDaoFactory(String tipoImplementazione) {
		
		return new JpaFactory();
	
	}

}
