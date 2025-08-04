package Controllers;

import Models.Account;
import Controllers.managers.AccountManager;
import Models.passwordHasher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Registration", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        httpRequest.getSession().removeAttribute("quiz");
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String salt = passwordHasher.generateSalt();
        try {
            password = passwordHasher.hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String email = httpRequest.getParameter("email");
        String firstName = httpRequest.getParameter("first_name");
        String lastName = httpRequest.getParameter("last_name");
        String imageUrl = "https://www.pngall.com/wp-content/uploads/5/User-Profile-PNG-Free-Download.png";

        Account account = new Account(username, firstName, lastName, password, email, imageUrl, salt);
        httpRequest.setAttribute("first_name", firstName);
        httpRequest.setAttribute("last_name", lastName);
        httpRequest.setAttribute("username", username);

        if (!accountManager.accountExists(username)) {
            accountManager.createNewUser(account);
            httpRequest.getSession().setAttribute("account", account);
            httpRequest.getSession().setAttribute("username", username);
            httpRequest.getSession().setAttribute("loggedInAccount", account);
            httpResponse.sendRedirect("/HomePageServlet");
        } else {
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("RegisterTryAgain.jsp");
            requestDispatcher.forward(httpRequest, httpResponse);
        }
    }
}