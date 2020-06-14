var seemore = {
    "container": "",
    "number": 6,
    "total": 0
};


document.addEventListener("DOMContentLoaded", function () {
    getTotal();
    hideAll();
    document.getElementsByClassName('see-more')[0].addEventListener("click", displayMore);
});

/**
 * Find the total amount of items on the page
 */
function getTotal() {
    seemore.container = document.getElementsByClassName('selection-container');
    var number = 0;
    for (var i = 0; i < seemore.container[0].children.length; i++) {
        number++;
    }
    seemore.total = number;
}

/**
 * Hide all items on the page except for first 6
 */
function hideAll() {
    if (seemore.number < seemore.total) {
        for (var i = seemore.number; i < seemore.total; i++) {
            seemore.container[0].children[i].classList.add('hide');
        }
        seemore.number += 3;
    } else {
        document.getElementsByClassName('see-more-container')[0].classList.add('hide');
    }
}

/**
 * Display 6 more items on the page per click
 *  @param {EventTarget} e click event
 */
function displayMore(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    for (var i = 0; i < seemore.number; i++) {
        seemore.container[0].children[i].classList.remove('hide');
    }
    seemore.number += 3;
    if (seemore.number < seemore.total) {
        document.getElementsByClassName('see-more-container')[0].classList.add('hide');
    }
}