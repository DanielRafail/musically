/**
 * Set the first carousel item to active
 */
$(document).ready(function () {
   var carouselActive = document.getElementsByClassName('carousel-inner');
   for(var i=0; i < carouselActive.length;i++){
       carouselActive[i].children[0].classList.add('active');
   }
});