$(document).ready(function() {
    // Get the create quiz button container
    var $createQuizContainer = $('.create-quiz-container');

    // Hide the button initially
    $createQuizContainer.hide();

    // Function to update button visibility
    function updateButtonVisibility() {
        if (currentSlide === 0) { // Slide 1 (index 0)
            $createQuizContainer.show();
        } else {
            $createQuizContainer.hide();
        }
    }

    // Add click handler for create quiz button
    $('.create-quiz-btn').click(function(e) {
        console.log('Create quiz button clicked!');
        window.location.href = '/CreateQuiz.jsp';
        e.preventDefault();
    });
    // Get all slides and navigation buttons
    var $slides = $('.slide');
    var $navButtons = $('nav a');
    var totalSlides = $slides.length;

    // Determine initial slide based on URL hash (e.g., #slide-4)
    var hash = window.location.hash;
    var currentSlide = 0;
    if (hash && hash.startsWith('#slide-')) {
        var hashIndex = parseInt(hash.replace('#slide-', '')) - 1;
        if (!isNaN(hashIndex) && hashIndex >= 0 && hashIndex < totalSlides) {
            currentSlide = hashIndex;
        }
    }
    var isAnimating = false;

    // Settings menu toggle
    $('.settings-btn').click(function(e) {
        e.stopPropagation();
        $('.slide-out-menu').toggleClass('active');
    });

    // Close menu when clicking outside
    $(document).click(function(e) {
        if (!$(e.target).closest('.settings-btn, .slide-out-menu').length) {
            $('.slide-out-menu').removeClass('active');
        }
    });

    // Prevent closing when clicking inside menu
    $('.slide-out-menu').click(function(e) {
        e.stopPropagation();
    });

    // Menu item clicks
    $('.menu-item').click(function(e) {
        e.preventDefault();
        $('.slide-out-menu').removeClass('active');
    });

    // Initialize slide based on currentSlide value (could be set by URL hash)
    $slides.removeClass('active').eq(currentSlide).addClass('active');
    $navButtons.removeClass('active').eq(currentSlide).addClass('active');

    // Show button on first slide
    updateButtonVisibility();

    // Navigation click handler
    $navButtons.click(function(e) {
        e.preventDefault();
        var target = $(this).attr('href');
        var index = parseInt(target.replace('#slide-', '')) - 1;

        // Add console logging
        console.log('Navigation clicked:', target);
        if (index === 2) {
            console.log('Quiz News slide clicked!');
        }

        if (index >= 0 && index < totalSlides) {
            changeSlide(index);
        }
    });

    // Previous/Next click handlers
    $('.go-prev').click(function() {
        changeSlide(currentSlide - 1);
    });

    $('.go-next').click(function() {
        changeSlide(currentSlide + 1);
    });

    // Mouse wheel handler
    $(window).on('wheel', function(e) {
        if (isAnimating) return;

        if (e.originalEvent.deltaY > 0) {
            changeSlide(currentSlide + 1);
        } else {
            changeSlide(currentSlide - 1);
        }
    });

    // Keyboard navigation
    $(document).keydown(function(e) {
        if (isAnimating) return;

        // Left arrow (37) and Up arrow (38) go to previous slide
        if (e.which === 37 || e.which === 38) {
            changeSlide(currentSlide - 1);
        }
        // Right arrow (39) and Down arrow (40) go to next slide
        else if (e.which === 39 || e.which === 40) {
            changeSlide(currentSlide + 1);
        }
    });

    function changeSlide(index) {
        if (isAnimating) return;

        // Handle wrapping around
        if (index < 0) {
            index = totalSlides - 1;
        } else if (index >= totalSlides) {
            index = 0;
        }

        if (index !== currentSlide) {
            isAnimating = true;

            // Update current slide index
            currentSlide = index;

            // Update active slide with fade animation
            $slides.removeClass('active').eq(index).addClass('active');

            // Update active navigation
            $navButtons.removeClass('active').eq(index).addClass('active');

            // Update button visibility
            updateButtonVisibility();

            // Reset animation flag after a short delay
            setTimeout(function() {
                isAnimating = false;
            }, 500);
        }
    }
});