$(document).ready(function() {
    let currentLevel = 1;
    const maxLevel = 5;

    $('#expander').click(function(e) {
        e.preventDefault();
        
        // Hide the expander button
        $(this).css('opacity', '0');
        
        // Remove previous animation classes
        $(this).removeClass('jello-horizontal');
        
        // Add animation class
        $(this).addClass('jello-horizontal');
        
        // Update current level
        currentLevel = (currentLevel % maxLevel) + 1;
        
        // Update brain levels
        for (let i = 1; i <= maxLevel; i++) {
            const levelClass = `.brain-level-${i}`;
            const opacity = i <= currentLevel ? 1 : 0;
            $(levelClass).css('opacity', opacity);
        }

        // Remove animation class after animation ends
        setTimeout(() => {
            $(this).removeClass('jello-horizontal');
            $(this).css('opacity', '0.3');
        }, 1000);
    });
});
