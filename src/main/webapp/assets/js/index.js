// Interactive elements initialization
window.addEventListener('DOMContentLoaded', (event) => {
    // Add smooth scrolling for navigation links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Handle button hover effects
    const buttons = document.querySelectorAll('.top-right-buttons button');
    buttons.forEach(button => {
        button.addEventListener('mouseover', () => {
            button.style.transform = 'scale(1.05)';
        });
        button.addEventListener('mouseout', () => {
            button.style.transform = 'scale(1)';
        });
    });

    // Add responsive behavior for mobile menu
    const mobileMenu = document.getElementById('mobile-menu');
    const menuToggle = document.getElementById('menu-toggle');
    if (mobileMenu && menuToggle) {
        menuToggle.addEventListener('click', () => {
            mobileMenu.classList.toggle('active');
        });
    }
});

// Form validation functions
function validateForm(formId) {
    const form = document.getElementById(formId);
    if (!form) return false;

    const inputs = form.querySelectorAll('input[required], textarea[required]');
    let isValid = true;

    inputs.forEach(input => {
        if (!input.value.trim()) {
            input.classList.add('error');
            isValid = false;
        } else {
            input.classList.remove('error');
        }
    });

    return isValid;
}

// Handle login/signup form submissions
document.addEventListener('submit', (event) => {
    const form = event.target;
    if (form.id === 'login-form' || form.id === 'signup-form') {
        if (!validateForm(form.id)) {
            event.preventDefault();
            return false;
        }
    }
});
