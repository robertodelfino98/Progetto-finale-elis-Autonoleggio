jQuery( document ).ready( function($) {
    $( window ).scroll( function () {
        if ( $(document).scrollTop() > 150 ) {
            // Navigation Bar
            $('.navbar').removeClass('fadeIn');
            $('body').addClass('shrink');
            $('.navbar').addClass('animated fadeInDown');
        } else {
            $('.navbar').removeClass('fadeInDown');
            $('body').removeClass('shrink');
            $('.navbar').addClass('animated fadeIn');
        }
    });
});


if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
}


window.console = window.console || function(t) {};