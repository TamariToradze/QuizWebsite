package Controllers.Listeners;

import Controllers.managers.LeaderboardManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LeaderboardListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LeaderboardManager manager = new LeaderboardManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(LeaderboardManager.ATTRIBUTE_NAME, manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(LeaderboardManager.ATTRIBUTE_NAME);
    }
}