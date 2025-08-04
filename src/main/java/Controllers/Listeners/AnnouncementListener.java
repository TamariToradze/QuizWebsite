package Controllers.Listeners;

import Controllers.managers.AnnouncementManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AnnouncementListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AnnouncementManager mng = new AnnouncementManager();
        ServletContext cn = servletContextEvent.getServletContext();
        cn.setAttribute(AnnouncementManager.ATTRIBUTE_NAME, mng);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(AnnouncementManager.ATTRIBUTE_NAME);
    }
}