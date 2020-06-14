var reports = {
    "parent": "",
    "top": "",
    "zero": "",
    "sales": ""
};

document.addEventListener("DOMContentLoaded", function () {
    setup();
    for (var i = 0; i < reports.parent.length; i++) {
        reports.parent[i].addEventListener("click", parentContainerClicked);
    }
    for (var i = 0; i < reports.top.length; i++) {
        reports.top[i].addEventListener("click", topContainerClicked);
    }
    for (var i = 0; i < reports.zero.length; i++) {
        reports.zero[i].addEventListener("click", zeroContainerClicked);
    }
    for (var i = 0; i < reports.sales.length; i++) {
        reports.sales[i].addEventListener("click", salesContainerClicked);
    }
});

/**
 * Called when parent container is clicked. Shows child based on which one is clicked
 *  @param {EventTarget} e click event
 */
function parentContainerClicked(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    parentContainerFocused();
    e.classList.add("focused-item");
    e.disabled = true;
    if (e.classList.contains('report-sales')) {
        document.getElementsByClassName('report-sales-container')[0].classList.remove('hide');
    } else if (e.classList.contains('report-top')) {
        document.getElementsByClassName('report-top-container')[0].classList.remove('hide');
    } else if (e.classList.contains('report-zero')) {
        document.getElementsByClassName('report-zero-container')[0].classList.remove('hide');
    }
}

/**
 * Called when sales child is clicked. Shows child for sales
 *  @param {EventTarget} e click event
 */
function salesContainerClicked(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    salesContainerFocused();
    e.classList.add("focused-item");
    e.disabled = true;
    if (e.classList.contains('report-sales-total')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-sales-client')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-sales-client')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-sales-artist')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-sales-track')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-sales-album')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    }
}

/**
 * Called when top container is clicked. Shows child for top container
 *  @param {EventTarget} e click event
 */
function topContainerClicked(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    topContainerFocused();
    e.classList.add("focused-item");
    e.disabled = true;
    if (e.classList.contains('report-top-seller')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-top-client')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    }
}

/**
 * Called when zero container child is clicked. Shows child for zero container
 *  @param {EventTarget} e click event
 */
function zeroContainerClicked(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    zeroContainerFocused();
    e.classList.add("focused-item");
    e.disabled = true;
    if (e.classList.contains('report-zero-track')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    } else if (e.classList.contains('report-zero-client')) {
        document.getElementsByClassName('report-results')[0].classList.remove('hide');
    }
}

/**
 * Called when parent is focusable (clickable)
 */
function parentContainerFocused() {
    for (var i = 0; i < reports.parent.length; i++) {
        reports.parent[i].classList.remove('focused-item');
        reports.parent[i].disabled = false;
    }
    salesContainerHide();
    zeroContainerHide();
    topContainerHide();
}

/**
 * Called when sales is focusable (clickable)
 */
function salesContainerFocused() {
    for (var i = 0; i < reports.sales.length; i++) {
        reports.sales[i].classList.remove('focused-item');
        reports.sales[i].disabled = false;
    }
}

/**
 * Called when top container is focusable (clickable)
 */
function topContainerFocused() {
    for (var i = 0; i < reports.top.length; i++) {
        reports.top[i].classList.remove('focused-item');
        reports.top[i].disabled = false;
    }
}

/**
 * Called when zero container is focusable (clickable)
 */
function zeroContainerFocused() {
    for (var i = 0; i < reports.zero.length; i++) {
        reports.zero[i].classList.remove('focused-item');
        reports.zero[i].disabled = false;
    }
}
/**
 * Hide zero container and its children
 */
function zeroContainerHide() {
    zeroContainerFocused();
    document.getElementsByClassName('report-zero-container')[0].classList.add('hide');
    document.getElementsByClassName('report-results')[0].classList.add('hide');
}
/**
 * Hide top container and its children
 */
function topContainerHide() {
    topContainerFocused();
    document.getElementsByClassName('report-top-container')[0].classList.add('hide');
    document.getElementsByClassName('report-results')[0].classList.add('hide');
}
/**
 * Hide sales container and its children
 */
function salesContainerHide() {
    salesContainerFocused();
    document.getElementsByClassName('report-sales-container')[0].classList.add('hide');
    document.getElementsByClassName('report-results')[0].classList.add('hide');
}

/**
 * Setup function called on DOMloaded to fill up values with proper htlm elements
 */
function setup() {
    reports.parent = [];
    parentarray = document.getElementsByClassName('report-nav-container')[0].children[0];
    reports.parent.push(parentarray.children[parentarray.children.length - 2]);
    reports.parent.push(parentarray.children[parentarray.children.length - 3]);
    reports.parent.push(parentarray.children[parentarray.children.length - 4]);

    reports.top = [];
    toparray = document.getElementsByClassName('report-top-container')[0].children[0];
    for (var i = 0; i < toparray.children.length; i++) {
        reports.top.push(toparray.children[i]);
    }

    reports.sales = [];
    salesarray = document.getElementsByClassName('report-sales-container')[0].children[0];
    for (var i = 0; i < salesarray.children.length; i++) {
        reports.sales.push(salesarray.children[i]);
    }

    reports.zero = [];
    zeroarray = document.getElementsByClassName('report-zero-container')[0].children[0];
    for (var i = 0; i < zeroarray.children.length; i++) {
        reports.zero.push(zeroarray.children[i]);
    }

}

