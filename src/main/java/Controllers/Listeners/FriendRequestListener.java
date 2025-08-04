package Controllers.Listeners;

import Controllers.managers.FriendsManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FriendRequestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FriendsManager friendsManager = new FriendsManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(FriendsManager.ATTRIBUTE_NAME, friendsManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(FriendsManager.ATTRIBUTE_NAME);
    }
}