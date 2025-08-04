package Controllers.Listeners;

import Controllers.managers.QuizManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListener that manages the lifecycle of QuizManager.
 * This listener ensures that the QuizManager is properly initialized when the
 * web application starts and cleaned up when it shuts down.
 */
@WebListener
public class QuizListener implements ServletContextListener {

    /**
     * Called when the servlet context is initialized (application startup).
     * Creates a new QuizManager instance and stores it in the servlet context
     * for use throughout the application lifecycle.
     *
     * @param servletContextEvent the event containing the servlet context
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Create a new QuizManager instance for application-wide use
        QuizManager applicationQuizManager = new QuizManager();

        // Get the servlet context from the event
        ServletContext applicationContext = servletContextEvent.getServletContext();

        // Store the QuizManager in servlet context so it can be accessed by servlets
        applicationContext.setAttribute(QuizManager.ATTRIBUTE_NAME, applicationQuizManager);
    }

    /**
     * Called when the servlet context is destroyed (application shutdown).
     * Removes the QuizManager from the servlet context to ensure proper cleanup
     * and prevent memory leaks.
     *
     * @param servletContextEvent the event containing the servlet context
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Remove the QuizManager from servlet context during application shutdown
        // This helps prevent memory leaks and ensures proper cleanup
        servletContextEvent.getServletContext().removeAttribute(QuizManager.ATTRIBUTE_NAME);
    }
}