package servleti;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bioskop.dao.ConnectionManager;

public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0)  { 
    	ConnectionManager.open();

    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
