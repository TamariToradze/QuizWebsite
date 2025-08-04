$(document).ready(function() {
    // Initialize variables
    let curr = 0;
    const total = $('.slide').length;
    let animating = false;
    const $slides = $('.slide');
    const $navButtons = $('nav a');

    // Initial setup
    $slides.eq(0).addClass('active');
    $navButtons.eq(0).addClass('active');

    // Quiz button click handler
    $('.quiz-button').click(function(e) {
        e.preventDefault();
        window.location.href = './CreateQuiz.jsp';
    });

    // Function to change slides
    function changeSlide(index) {
        if (animating) return;

        if (index < 0) {
            index = total - 1;
        } else if (index >= total) {
            index = 0;
        }

        if (index !== curr) {
            animating = true;
            curr = index;
            $slides.removeClass('active').eq(index).addClass('active');
            $navButtons.removeClass('active').eq(index).addClass('active');
            updateButtonVisibility();
            setTimeout(function() {
                animating = false;
            }, 500);
        }
    }

    // Function to update button visibility
    function updateButtonVisibility() {
        if (curr === 0) {
            $('.button-container').show();
        } else {
            $('.button-container').hide();
        }
    }

    // Navigation click handlers
    $navButtons.click(function(e) {
        e.preventDefault();
        const index = $(this).parent().index();
        changeSlide(index);
    });

    // Settings menu toggle
    $('.settings-btn').click(function() {
        $('.slide-out-menu').toggleClass('active');
    });

    // Click outside settings menu to close
    $(document).click(function(e) {
        if (!$(e.target).closest('.slide-out-menu, .settings-btn').length) {
            $('.slide-out-menu').removeClass('active');
        }
    });

    // Mouse wheel navigation
    $(window).on('wheel', function(e) {
        if (animating) return;

        if (e.originalEvent.deltaY > 0) {
            changeSlide(curr + 1);
        } else {
            changeSlide(curr - 1);
        }
    });

    // Keyboard navigation
    $(document).keydown(function(e) {
        if (animating) return;

        switch(e.which) {
            case 37: // left
            case 38: // up
                changeSlide(curr - 1);
                break;
            case 39: // right
            case 40: // down
                changeSlide(curr + 1);
                break;
        }
    });

    // Initialize button visibility
    updateButtonVisibility();
});