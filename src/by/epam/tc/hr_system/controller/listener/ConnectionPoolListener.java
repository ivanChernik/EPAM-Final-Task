package by.epam.tc.hr_system.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.dao.connection_pool.ConnectionPool;
import by.epam.tc.hr_system.exception.ConnectionPoolException;
import by.epam.tc.hr_system.exception.ConnectionPoolRuntimeException;

public class ConnectionPoolListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger(ConnectionPoolListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			log.fatal("Connection pool cannot be dispose", e);
			throw new ConnectionPoolRuntimeException("Connection pool cannot be dispose", e);
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ConnectionPool.getInstance().initConnectionPool();
		} catch (ConnectionPoolException e) {
			log.fatal("Connection pool cannot be initialized", e);
			throw new ConnectionPoolRuntimeException("Connection pool cannot be initialized", e);
		}
	}

}
