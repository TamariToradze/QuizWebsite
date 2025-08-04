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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SuggestionServlet", urlPatterns = {"/SuggestionServlet"})
public class SuggestionServlet extends HttpServlet {
    /*
 - Create servlet for handling user search suggestions
- Implement GET method for search functionality
- Retrieve search query parameter
- Integrate with AccountManager for account data
- Filter accounts based on:
  * First name
  * Last name
  * Username
  * Email
- Exclude current user from results
- Store results in ArrayList
- Forward to SearchResult.jsp with results
- Add proper exception handling for SQL errors
- Add logging for search queries and results
- Use @WebServlet annotation for URL mapping
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String table = request.getParameter("query");
        System.out.println(table);
        List<Account> sg = new ArrayList<>();
        String name = (String) request.getSession().getAttribute("username");
        try {
            AccountManager mg = new AccountManager();
            List<Account> accs = mg.getAccounts();
            for (Account acc : accs) {
                if((acc.getFirstName().startsWith(table) ||
                        acc.getLastName().startsWith(table) ||
                        acc.getUserName().startsWith(table) ||
                        acc.getEmail().startsWith(table)) &&
                        !acc.getUserName().equals(name)){
                    sg.add(acc);
                }
            }

            request.setAttribute("results", sg);
            System.out.println(sg);
            //forward to /SearchResult.jsp
            RequestDispatcher d = request.getRequestDispatcher("/SearchResult.jsp");
            d.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}