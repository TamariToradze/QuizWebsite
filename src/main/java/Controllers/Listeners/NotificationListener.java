package Controllers.Listeners;

import Controllers.managers.NotificationManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class NotificationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        NotificationManager notificationManager = new NotificationManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(NotificationManager.ATTRIBUTE_NAME, notificationManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(NotificationManager.ATTRIBUTE_NAME);
    }
}
