package com.conference.my.controller.listener;

import com.conference.my.controller.Controller;
import com.conference.my.model.dao.DAOFactory;
import com.conference.my.model.entity.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Context listener.
 *
 * @author D.Kolesnikov
 */
public class ContextListener implements ServletContextListener {

  private static final Logger LOGGER = LogManager.getLogger(Controller.class);

  public void contextDestroyed(ServletContextEvent event) {
  }

  public void contextInitialized(ServletContextEvent event) {
    log("Servlet context initialization starts");

    ServletContext servletContext = event.getServletContext();
//		initLog4J(servletContext);
//		initCommandContainer();
//		initI18N(servletContext);
    initStartPage(servletContext);

    log("Servlet context initialization finished");
  }

  private void initStartPage(ServletContext servletContext) {
    log("initialization started");
    try {
      Class.forName("com.conference.my.model.entity.Event");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    final List<Event> events = DAOFactory.getEventDAO().getFutureEvents();
    servletContext.setAttribute("initEvents", events);
  }

//	/**
//	 * Initializes i18n subsystem.
//	 */
//	private void initI18N(ServletContext servletContext) {
//      LOGGER.debug("I18N subsystem initialization started");
//
//		String localesValue = servletContext.getInitParameter("locales");
//		if (localesValue == null || localesValue.isEmpty()) {
//          LOGGER.warn("'locales' init parameter is empty, the default encoding will be used");
//		} else {
//			List<String> locales = new ArrayList<String>();
//			StringTokenizer st = new StringTokenizer(localesValue);
//			while (st.hasMoreTokens()) {
//				String localeName = st.nextToken();
//				locales.add(localeName);
//			}
//
//          LOGGER.debug("Application attribute set: locales --> " + locales);
//			servletContext.setAttribute("locales", locales);
//		}
//
//      LOGGER.debug("I18N subsystem initialization finished");
//	}

//	/**
//	 * Initializes log4j framework.
//	 *
//	 * @param servletContext
//	 */
//	private void initLog4J(ServletContext servletContext) {
//		log("Log4J initialization started");
//		try {
//			PropertyConfigurator.configure(servletContext.getRealPath(
//							"WEB-INF/log4j.properties"));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		log("Log4J initialization finished");
//	}


  private void log(String msg) {
    System.out.println("[ContextListener] " + msg);
  }

}
