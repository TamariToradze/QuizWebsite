package Controllers;

import Models.Account;
import Controllers.managers.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LogInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        request.getSession().removeAttribute("quiz");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username", username);
        try {
            if (accountManager.successfulLogin(username, password)) {
                Account account = accountManager.getAccount(username);
                request.setAttribute("username", username);
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("account", account);
                request.getSession().setAttribute("loggedInAccount", account);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePage.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("AuthorisationTryAgain.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
