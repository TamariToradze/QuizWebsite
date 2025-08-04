<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - Quiz App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body, html {
            height: 100%;
        }
        .profile-container {
            background: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .glass-card {
            background: rgba(255, 255, 255, 0.7);
            border-radius: 25px;
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.18);
            backdrop-filter: blur(8px);
            -webkit-backdrop-filter: blur(8px);
            border: 1px solid rgba(255, 255, 255, 0.18);
            padding: 2.5rem 2rem 2rem 2rem;
            max-width: 700px;
            width: 100%;
        }
        .profile-header {
            text-align: center;
            margin-bottom: 2rem;
        }
        .profile-avatar {
            width: 130px;
            height: 130px;
            object-fit: cover;
            border-radius: 50%;
            border: 4px solid #fff;
            box-shadow: 0 4px 24px rgba(63, 55, 201, 0.19);
            margin-bottom: 1rem;
        }
        .profile-header h2 {
            font-weight: 700;
            margin-top: 0.5rem;
        }
        .profile-header p.lead {
            color: #5f5f5f;
            font-size: 1.1rem;
        }
        .stats-row {
            margin-bottom: 2rem;
        }
        .stats-card {
            background: rgba(255,255,255,0.85);
            border-radius: 18px;
            box-shadow: 0 2px 12px rgba(63, 55, 201, 0.08);
            padding: 1.2rem 0.8rem;
            margin: 0 0.5rem;
            transition: transform 0.2s, box-shadow 0.2s;
            text-align: center;
        }
        .stats-card:hover {
            transform: translateY(-6px) scale(1.04);
            box-shadow: 0 8px 24px rgba(63, 55, 201, 0.18);
        }
        .stats-icon {
            font-size: 2rem;
            color: #3f37c9;
            margin-bottom: 0.3rem;
        }
        .stats-value {
            font-size: 1.8rem;
            font-weight: 700;
            color: #3f37c9;
        }
        .stats-label {
            color: #6c757d;
            font-size: 1rem;
        }
        .section-card {
            background: rgba(255,255,255,0.95);
            border-radius: 15px;
            box-shadow: 0 2px 8px rgba(63, 55, 201, 0.10);
            padding: 1.5rem;
            margin-bottom: 1.2rem;
        }
        .edit-btn, .edit-btn:visited {
            background: linear-gradient(135deg, #4361ee 0%, #3f37c9 100%);
            border: none;
            padding: 0.5rem 1.5rem;
            border-radius: 25px;
            color: white !important;
            font-weight: bold;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .edit-btn:hover {
            transform: translateY(-2px) scale(1.04);
            box-shadow: 0 4px 18px rgba(63, 55, 201, 0.18);
        }
        .btn-outline-secondary.edit-btn {
            background: linear-gradient(135deg, #e0e0e0 0%, #bdbdbd 100%);
            color: #3f37c9 !important;
            border: none;
        }
        .form-control:focus {
            border-color: #3f37c9;
            box-shadow: 0 0 0 0.2rem rgba(63, 55, 201, 0.15);
        }
        @media (max-width: 768px) {
            .glass-card {
                padding: 1.2rem 0.5rem;
            }
            .stats-card {
                margin-bottom: 1rem;
            }
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <div class="glass-card mx-auto">
            <div class="profile-header">
                <img src="${profile.imageUrl}" alt="Profile Picture" class="profile-avatar">
                <h2>${profile.firstName} ${profile.lastName}</h2>
                <p class="lead">@${profile.userName}</p>
            </div>
            <div class="row stats-row justify-content-center">
                <div class="col-12 col-md-4 mb-3 mb-md-0">
                    <div class="stats-card">
                        <div class="stats-icon"><i class="fas fa-user-friends"></i></div>
                        <div class="stats-value">${profile.friends.size()}</div>
                        <div class="stats-label">Friends</div>
                    </div>
                </div>
                <div class="col-12 col-md-4 mb-3 mb-md-0">
                    <div class="stats-card">
                        <div class="stats-icon"><i class="fas fa-trophy"></i></div>
                        <div class="stats-value">${profile.achievementIds.size()}</div>
                        <div class="stats-label">Achievements</div>
                    </div>
                </div>
                <div class="col-12 col-md-4">
                    <div class="stats-card">
                        <div class="stats-icon"><i class="fas fa-clipboard-list"></i></div>
                        <div class="stats-value">${profile.achievementIds.size()}</div>
                        <div class="stats-label">Quizzes Taken</div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12 col-md-6 mb-3">
                    <div class="section-card">
                        <h5 class="mb-3"><i class="fas fa-id-card me-2"></i>Account Information</h5>
                        <p><i class="fas fa-envelope me-2"></i>${profile.email}</p>
                        <p><i class="fas fa-user-friends me-2"></i>${profile.friends.size()} Friends</p>
                        <p><i class="fas fa-crown me-2"></i>${profile.achievementIds.size()} Achievements</p>
                    </div>
                </div>
                <div class="col-12 col-md-6 mb-3">
                    <div class="section-card">
                        <h5 class="mb-3"><i class="fas fa-cog me-2"></i>Profile Settings</h5>
                        <form action="profile" method="post" class="mb-3">
                            <div class="input-group">
                                <input type="url" name="imageUrl" value="${profile.imageUrl}"
                                    class="form-control" placeholder="Enter profile picture URL">
                                <button type="submit" class="btn btn-primary edit-btn">Update Picture</button>
                            </div>
                        </form>
                        <div class="d-grid gap-2">
                            <a href="edit-profile.jsp" class="edit-btn">Edit Profile</a>
                            <a href="change-password.jsp" class="btn btn-outline-secondary edit-btn">Change Password</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
