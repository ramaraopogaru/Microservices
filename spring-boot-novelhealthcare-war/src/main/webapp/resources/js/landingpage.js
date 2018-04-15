$(document).ready(function(){
	// invoke the carousel
	    $('#myCarousel').carousel({
	      interval:6000
	    });


	//scroll slides on swipe for touch enabled devices 

	 	$("#myCarousel").on("touchstart", function(event){
	 
	        var yClick = event.originalEvent.touches[0].pageY;
	    	$(this).one("touchmove", function(event){

	        var yMove = event.originalEvent.touches[0].pageY;
	        if( Math.floor(yClick - yMove) > 1 ){
	            $(".carousel").carousel('next');
	        }
	        else if( Math.floor(yClick - yMove) < -1 ){
	            $(".carousel").carousel('prev');
	        }
	    });
	    $(".carousel").on("touchend", function(){
	            $(this).off("touchmove");
	    });
	});
	    


//to add  start animation on load for first slide 
$(function(){
		$.fn.extend({
			animateCss: function (animationName) {
				var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
				this.addClass('animated ' + animationName).one(animationEnd, function() {
					$(this).removeClass(animationName);
				});
			}
		});
			 $('.item1.active img').animateCss('slideInDown');
			 $('.item1.active h2').animateCss('zoomIn');
			 $('.item1.active p').animateCss('fadeIn');

				$('.item2 img').animateCss('zoomIn');
				$('.item2 h2').animateCss('swing');
				$('.item2 p').animateCss('fadeIn');
				
				$('.item3 img').animateCss('fadeInLeft');
				$('.item3 h2').animateCss('fadeInDown');
				$('.item3 p').animateCss('fadeIn');
			 
});



var myNavBar = {

	    flagAdd: true,

	    elements: [],

	    init: function (elements) {
	        this.elements = elements;
	    },

	    add : function() {
	        if(this.flagAdd) {
	            for(var i=0; i < this.elements.length; i++) {
	                document.getElementById(this.elements[i]).className += " fixed-theme";
	            }
	            this.flagAdd = false;
	        }
	    },

	    remove: function() {
	        for(var i=0; i < this.elements.length; i++) {
	            document.getElementById(this.elements[i]).className =
	                    document.getElementById(this.elements[i]).className.replace( /(?:^|\s)fixed-theme(?!\S)/g , '' );
	        }
	        this.flagAdd = true;
	    }

	};

	/**
	 * Init the object. Pass the object the array of elements
	 * that we want to change when the scroll goes down
	 */
	myNavBar.init(  [
	    "header",
	    "header-container",
	    "brand"
	]);

	/**
	 * Function that manage the direction
	 * of the scroll
	 */
	function offSetManager(){

	    var yOffset = 0;
	    var currYOffSet = window.pageYOffset;

	    if(yOffset < currYOffSet) {
	        myNavBar.add();
	    }
	    else if(currYOffSet == yOffset){
	        myNavBar.remove();
	    }

	}

	/**
	 * bind to the document scroll detection
	 */
	window.onscroll = function(e) {
	    offSetManager();
	}

	/**
	 * We have to do a first detectation of offset because the page
	 * could be load with scroll down set.
	 */
	offSetManager();
	});
