package Controllers;

import Controllers.managers.FriendsManager;
import Models.Enums.NotificationType;
import Controllers.managers.NotificationManager;
import Models.FriendRequest;
import Models.Notification;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "NotificationServlet", urlPatterns = {"/notificationServlet"})
public class NotificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        List<Notification> notifications;

        NotificationManager notifManager = (NotificationManager) getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
        try {
            notifications = notifManager.getNotificationsToUser(loggedInUsername);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(reader, RequestData.class);
        String type = requestData.type;
        ResponseData responseData = new ResponseData();
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        String receiver = requestData.receiver;

        try {
            findType(type, receiver,loggedInUsername, requestData, responseData, request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(responseData));
    }

    private void findType(String type, String receiver, String loggedInUsername, RequestData requestData, ResponseData responseData, HttpServletRequest request) throws SQLException {
        Notification note;
        NotificationManager notifManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
        switch (type) {
            case "quiz":
                String quizLink = requestData.quizLink;
                int bestScore = requestData.bestScore;

                responseData.status = "success";
                responseData.message = "Quiz data received.";
                responseData.receiver = receiver;

                note = new Notification(loggedInUsername, receiver, NotificationType.CHALLENGE, quizLink,bestScore, 0, "");
                notifManager.createNotification(note);
                break;
            case "note":
                String message = requestData.message;

                responseData.status = "success";
                responseData.message = "Note received.";
                responseData.receiver = receiver;

                note = new Notification(loggedInUsername, receiver, NotificationType.NOTE, "",0, 0, message);
                notifManager.createNotification(note);
                break;
            case "addFriend":
                try {
                    FriendRequest friendRequest = new FriendRequest(loggedInUsername, receiver);
                    FriendsManager friendManager = (FriendsManager) request.getServletContext().getAttribute(FriendsManager.ATTRIBUTE_NAME);
                    friendManager.sendFriendRequest(loggedInUsername, receiver);

                    int requestId = friendManager.getFriendRequestID(loggedInUsername, receiver);
                    Notification addFriend = new Notification(loggedInUsername, receiver, NotificationType.FRIEND_REQUEST, "",0, requestId, "");
                    notifManager.createNotification(addFriend);

                    responseData.status = "success";
                    responseData.message = "Friend request sent and notification created.";
                    responseData.receiver = receiver;
                } catch (SQLException e) {
                    responseData.status = "error";
                    responseData.message = e.getMessage();
                    responseData.receiver = receiver;
                    e.printStackTrace();
                }
                break;
            case "pending":
                boolean answer = requestData.answer;
                int notificationID = requestData.notificationID;
                FriendsManager friendManager = (FriendsManager) request.getServletContext().getAttribute(FriendsManager.ATTRIBUTE_NAME);
                if(answer){
                    try {
                        friendManager.acceptFriendRequest(loggedInUsername, requestData.receiver);
                        responseData.status = "success";
                        responseData.message = answer ? "Friend request accepted." : "Friend request rejected.";
                        responseData.receiver = receiver;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else{
                    try {
                        friendManager.rejectFriendRequest(requestData.receiver, loggedInUsername);
                        responseData.status = "success";
                        responseData.message = answer ? "Friend request accepted." : "Friend request rejected.";
                        responseData.receiver = receiver;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                break;
            default:
                responseData.status = "error";
                responseData.message = "Invalid request type.";
                responseData.receiver = requestData.receiver;
        }
    }

    private static class RequestData {
        String type;
        String quizLink;
        int bestScore;
        String message;
        String receiver;
        boolean answer;
        int notificationID;
    }

    private static class ResponseData {
        String status;
        String message;
        String receiver;
    }
}