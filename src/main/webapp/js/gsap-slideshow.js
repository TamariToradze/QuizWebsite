//First the variables our app is going to use need to be declared

//References to DOM elements
var $window = $(window);
var $document = $(document);
//Only links that starts with #
var $navButtons = $("nav a").filter("[href^=#]");
var $navGoPrev = $(".go-prev");
var $navGoNext = $(".go-next");
var $slidesContainer = $(".slides-container");
var $slides = $(".slide");
var $currentSlide = $slides.first();

//Animating flag - is our app animating
var isAnimating = false;

//The height of the window
var pageHeight = $window.innerHeight();

//Key codes for up and down arrows on keyboard. We'll be using this to navigate change slides using the keyboard
var keyCodes = {
    UP  : 38,
    DOWN: 40
}

//Wait for DOM to be ready
$(document).ready(function() {
    //Set initial height
    $slidesContainer.height(pageHeight);
    $slides.height(pageHeight);
    
    //Initialize first slide
    $currentSlide = $slides.first();
    $currentSlide.addClass('active-slide');
    
    //Set initial active navigation item
    $navButtons.first().addClass('active');
});

/*
*   Adding event listeners
* */

$window.on("resize", onResize).resize();
$window.on("mousewheel DOMMouseScroll", onMouseWheel);
$document.on("keydown", onKeyDown);
$navButtons.on("click", onNavButtonClick);
$navGoPrev.on("click", goToPrevSlide);
$navGoNext.on("click", goToNextSlide);

/*
*   Internal functions
* */

/*
*   When a button is clicked - first get the button href, and then slide to the container, if there's such a container
* */
function onNavButtonClick(event)
{
    //The clicked button
    var $button = $(this);

    //The slide the button points to
    var $slide = $($button.attr("href"));

    //If the slide exists, we go to it
    if($slide.length)
    {
        goToSlide($slide);
        event.preventDefault();
    }
}

/*
*   Getting the pressed key. Only if it's up or down arrow, we go to prev or next slide and prevent default behaviour
*   This way, if there's text input, the user is still able to fill it
* */
function onKeyDown(event)
{
    var PRESSED_KEY = event.keyCode;

    if(PRESSED_KEY == keyCodes.UP)
    {
        goToPrevSlide();
        event.preventDefault();
    }
    else if(PRESSED_KEY == keyCodes.DOWN)
    {
        goToNextSlide();
        event.preventDefault();
    }
}

/*
*   When user scrolls with the mouse, we have to change slides
* */
function onMouseWheel(event)
{
    //Normalize event wheel delta
    var delta = event.originalEvent.wheelDelta / 30 || -event.originalEvent.detail;

    //If the user scrolled up, it goes to previous slide, otherwise - to next slide
    if(delta < -1)
    {
        goToNextSlide();
    }
    else if(delta > 1)
    {
        goToPrevSlide();
    }

    event.preventDefault();
}

/*
*   If there's a previous slide, slide to it
* */
function goToPrevSlide()
{
    if($currentSlide.prev().length)
    {
        goToSlide($currentSlide.prev());
    }
}

/*
*   If there's a next slide, slide to it
* */
function goToNextSlide()
{
    if($currentSlide.next().length)
    {
        goToSlide($currentSlide.next());
    }
}

/*
*   Actual transition between slides
* */
function goToSlide($slide)
{
    //If the slides are not changing and there's such a slide
    if(!isAnimating && $slide.length)
    {
        //setting animating flag to true
        isAnimating = true;
        
        //Animate the current slide out
        gsap.to($currentSlide, {
            duration: 0.5,
            opacity: 0,
            onComplete: function() {
                $currentSlide.removeClass('active-slide');
            }
        });
        
        //Animate the new slide in
        $slide.addClass('active-slide');
        gsap.to($slide, {
            duration: 0.5,
            opacity: 1,
            onComplete: function() {
                $currentSlide = $slide;
                isAnimating = false;
            }
        });

        //Animate the navigation
        gsap.to($navButtons.filter(".active"), {
            duration: 0.3,
            className: "-=active"
        });

        gsap.to($navButtons.filter("[href=#" + $slide.attr("id") + "]"), {
            duration: 0.3,
            className: "+=active"
        });
    }
}

/*
*   Once the sliding is finished, we need to restore "isAnimating" flag.
*   You can also do other things in this function, such as changing page title
* */
function onSlideChangeEnd()
{
    isAnimating = false;
}

/*
*   When user resize it's browser we need to know the new height, so we can properly align the current slide
* */
function onResize(event)
{
    //This will give us the new height of the window
    var newPageHeight = $window.innerHeight();

    /*
    *   If the new height is different from the old height ( the browser is resized vertically ), the slides are resized
    * */
    if(pageHeight !== newPageHeight)
    {
        pageHeight = newPageHeight;

        //Setting height via GSAP
        gsap.set([$slidesContainer, $slides], {
            height: pageHeight + "px"
        });

        //The current slide should be always on the top
        gsap.set($slidesContainer, {
            scrollTo: {y: pageHeight * $currentSlide.index()}
        });
    }
}
