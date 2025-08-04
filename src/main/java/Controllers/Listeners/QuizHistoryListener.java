package Controllers.Listeners;

import Controllers.managers.QuizHistoryManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener that initializes and cleans up the QuizHistoryManager
 * when the web application context starts and stops.
 */
@WebListener
public class QuizHistoryListener implements ServletContextListener {

    /**
     * Called when the web application initialization process is starting.
     * Creates the QuizHistoryManager and stores it in the ServletContext.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Instantiate the manager responsible for quiz history operations
        QuizHistoryManager quizHistoryManager = new QuizHistoryManager();

        // Retrieve the ServletContext to store the manager for later use
        ServletContext ctx = event.getServletContext();
        ctx.setAttribute(QuizHistoryManager.ATTRIBUTE_NAME, quizHistoryManager);
    }

    /**
     * Called when the ServletContext is about to be shut down.
     * Removes the QuizHistoryManager attribute from the context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Remove the manager from the context to aid garbage collection
        event.getServletContext().removeAttribute(QuizHistoryManager.ATTRIBUTE_NAME);
    }
}
