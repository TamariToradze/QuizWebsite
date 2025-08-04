package Controllers;

import Controllers.managers.AccountManager;
import Models.Account;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "EditProfileServlet", urlPatterns = {"/EditProfile"})
@MultipartConfig(maxFileSize = 16177215) // 15MB max file size
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        
        // Get the current user from session
        Account currentUser = (Account) request.getSession().getAttribute("loggedInAccount");
        
        if (currentUser == null) {
            response.sendRedirect("/EditProfile.jsp");
            return;
        }

        // Set the account data in request scope
        request.setAttribute("account", currentUser);
        
        // Forward to the edit profile view
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/EditProfile.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        
        // Get the current user from session
        Account currentUser = (Account) request.getSession().getAttribute("loggedInAccount");
        
        if (currentUser == null) {
            response.sendRedirect("Authorisation.jsp");
            return;
        }

        // Update all profile fields
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String location = request.getParameter("location");

        // Update profile picture if new image is uploaded
        Part imagePart = request.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + imagePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("/uploads/profile-images/") + fileName;
            
            try (InputStream fileContent = imagePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(uploadPath));
                currentUser.setImageUrl("/uploads/profile-images/" + fileName);
            }
        }

        // Update all other fields
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
      

        // Save updates to database
        accountManager.updateAccount(currentUser);
        
        // Redirect back to profile page
        response.sendRedirect("profile");
    }
}
