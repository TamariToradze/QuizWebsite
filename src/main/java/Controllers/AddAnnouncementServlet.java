package Controllers;

import Controllers.managers.AnnouncementManager;
import Models.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAnnouncementServlet", urlPatterns = {"/AddAnnouncementServlet"})
public class AddAnnouncementServlet extends HttpServlet {
    //- Retrieve username from session
//- Create and save new Announcement objects
//- Add redirect to HomePageServlet after creation
//- Add logging for announcement text

//Forward to AddAnnouncement.jsp
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("AddAnnouncement.jsp").forward(httpServletRequest, httpServletResponse);
    }
//Process announcement submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String anntext = request.getParameter("announcement");
        String nm = request.getSession().getAttribute("username").toString();
        AnnouncementManager annMan = (AnnouncementManager) request.getServletContext().getAttribute(AnnouncementManager.ATTRIBUTE_NAME);
        Announcement c = new Announcement(nm, anntext);
        annMan.createAnnouncement(c);
        System.out.println("Announcement Text: " + anntext);

        response.sendRedirect("HomePageServlet");
    }
}