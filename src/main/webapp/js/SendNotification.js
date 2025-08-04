document.addEventListener("DOMContentLoaded", function () {
// Get the modal
let challengeModal = document.getElementById("challengeModal");
let noteModal = document.getElementById("noteModal");

// Get the buttons that open the modal
let btnSendNote = document.getElementById("sendNote");
let btnChallenge = document.getElementById("challenge");

// Get the <span> elements that close the modal
    let closeButtons = document.getElementsByClassName("close");

    let receiverId = document.getElementById("receiverId").value;

    // When the user clicks on the button, open the modal
    btnSendNote.onclick = function () {
        console.log("Send Note button clicked");
        noteModal.style.display = "block";
        challengeModal.style.display = "none";
    }

    btnChallenge.onclick = function () {
        console.log("Challenge button clicked");
        challengeModal.style.display = "block";
        noteModal.style.display = "none";
    }

    // When the user clicks on <span> (x), close the modal
    Array.from(closeButtons).forEach(button => {
        button.onclick = function () {
            console.log("Close button clicked");
            challengeModal.style.display = "none";
            noteModal.style.display = "none";
        }
    });

    // When the user clicks anywhere outside the modal, close it
    window.onclick = function (event) {
        if (event.target === challengeModal) {
            challengeModal.style.display = "none";
        }
        if (event.target === noteModal) {
            noteModal.style.display = "none";
        }
    }

    // Handle challenge
    let sendQuizBtn = document.getElementById("sendQuizBtn");
    let quizLink = document.getElementById("quizInput");
    let bestScore = document.getElementById("scoreInput");

    sendQuizBtn.onclick = function () {
        let quiz = quizLink.value.trim();
        let score = bestScore.value.trim();
        if (quiz !== "" && score !== "") {
            console.log("Sending quiz data", { quiz, score, receiverId });

            fetch('notificationServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: "quiz",
                    quizLink: quiz,
                    bestScore: score,
                    receiver: receiverId
                })
            })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => { throw new Error(text) });
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Success:', data);
                    challengeModal.style.display = "none";
                    quizLink.value = "";
                    bestScore.value = "";
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred while sending the challenge. Please try again.');
                });
        } else {
            alert('Please fill out both the quiz link and the high score.');
        }
    }

    //Handle note
    let sendNoteBtn = document.getElementById("sendNoteBtn");
    let messageInp = document.getElementById("messageInput");

    sendNoteBtn.onclick = function () {
        let message = messageInp.value.trim();
        if (message !== "") {
            console.log("Message sent:", message);

            fetch('notificationServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: "note",
                    message: message,
                    receiver: receiverId
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                    noteModal.style.display = "none";
                    messageInp.value = "";
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred while sending the note. Please try again.');
                });
        } else {
            alert('Please write a message before sending.');
        }
    }


    // Handle friend request
    let friend = document.getElementById("addFriend");

    friend.onclick = function () {
        fetch('notificationServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: "addFriend",
                request: 1,
                receiver: receiverId
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log('Request Sent:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('An error occurred while sending the friend request. Please try again.');
            });
    }

});