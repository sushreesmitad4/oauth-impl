package org.omnypay.mobileapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OmnypayListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent contextEvent) {

		/*ApplicationContext context = new ClassPathXmlApplicationContext("classpath:business-context.xml");
		contextEvent.getServletContext().setAttribute("businessContext", context);
		*/
		
	}

}
