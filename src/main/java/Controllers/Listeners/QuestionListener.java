package Controllers.Listeners;
import Controllers.managers.QuestionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class QuestionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        QuestionManager questionManager = new QuestionManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(QuestionManager.ATTRIBUTE_NAME, questionManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(QuestionManager.ATTRIBUTE_NAME);
    }
}
