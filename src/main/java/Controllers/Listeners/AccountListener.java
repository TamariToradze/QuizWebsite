package Controllers.Listeners;
import Controllers.managers.AccountManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AccountListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AccountManager accountManager = new AccountManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(AccountManager.ATTRIBUTE_NAME, accountManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(AccountManager.ATTRIBUTE_NAME);
    }
}
