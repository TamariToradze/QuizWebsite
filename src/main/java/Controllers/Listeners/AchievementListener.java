package Controllers.Listeners;
import Controllers.managers.AccountManager;
import Controllers.managers.AchievementManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AchievementListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AchievementManager mn = new AchievementManager();
        ServletContext cn = servletContextEvent.getServletContext();
        cn.setAttribute(AchievementManager.ATTRIBUTE_NAME, mn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(AchievementManager.ATTRIBUTE_NAME);
    }
}