var inventory = {
    "add": "",
    "addTrack": "",
    "remove": "",
    "find": "",
    "addContainer": "",
    "addTrackContainer": "",
    "removeContainer": "",
    "findContainer": "",
    "firstTime": "true"
};


document.addEventListener("DOMContentLoaded", function () {
    inventory.add = document.getElementsByClassName('inventory-add')[0];
    inventory.addTrack = document.getElementsByClassName('inventory-add-track')[0];
    inventory.remove = document.getElementsByClassName("inventory-remove")[0];
    inventory.find = document.getElementsByClassName("inventory-find")[0];
    inventory.addContainer = document.getElementById('add-container');
    inventory.addTrackContainer = document.getElementById('add-track-container');
    inventory.removeContainer = document.getElementById('remove-container');
    inventory.findContainer = document.getElementById('find-container');

    inventory.add.addEventListener("click", buttonClick);
    inventory.addTrack.addEventListener("click", buttonClick);
    inventory.remove.addEventListener("click", buttonClick);
    inventory.find.addEventListener("click", buttonClick);
    document.getElementsByClassName('remove-search')[0].addEventListener("click", function () {
        document.getElementById('inventory-table-remove').classList.remove('inventory-table-remove');
    });
});

/**
 * Verify the click event and decide what to do based on the target. 
 * Shows and Hides some of them
 *  @param {EventTarget} e click event
 */
function buttonClick(e) {
    console.log("button clicked");
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    displayAll(e);
    e.classList.add("inventory-button-selected");
    e.disabled = true;
    if (e.classList.contains('inventory-add')) {
        drawAdd();
    } else if (e.classList.contains('inventory-add-track')) {
        drawAddTrack();
    } else if (e.classList.contains('inventory-remove')) {
        drawRemove();
    } else if (e.classList.contains('inventory-find')) {
        findAll();
    }
}

/**
 * Show labels and input for the "add" container
 */
function drawAdd() {
    inventory.addContainer.classList.remove('notSelected');
    inventory.addTrackContainer.classList.add('notSelected');
    inventory.removeContainer.classList.add('notSelected');
    inventory.findContainer.classList.add('notSelected');

}

/**
 * Show add tab
 */
function drawAddTrack() {
    inventory.addContainer.classList.add('notSelected');
    inventory.addTrackContainer.classList.remove('notSelected');
    inventory.removeContainer.classList.add('notSelected');
    inventory.findContainer.classList.add('notSelected');
}

/**
 * Show remove tab
 */
function drawRemove() {
    inventory.addContainer.classList.add('notSelected');
    inventory.addTrackContainer.classList.add('notSelected');
    inventory.removeContainer.classList.remove('notSelected');
    inventory.findContainer.classList.add('notSelected');
}

/**
 * Show find tab
 */
function findAll() {
    inventory.addContainer.classList.add('notSelected');
    inventory.addTrackContainer.classList.add('notSelected');
    inventory.removeContainer.classList.add('notSelected');
    inventory.findContainer.classList.remove('notSelected');
}

/**
 * Reset the page when an item is clicked and then verify actions needed to do afterwards
 */
function displayAll(e) {
    inventory.add.classList.remove("inventory-button-selected");
    inventory.addTrack.classList.remove("inventory-button-selected");
    inventory.remove.classList.remove("inventory-button-selected");
    inventory.find.classList.remove("inventory-button-selected");
    inventory.add.disabled = false;
    inventory.addTrack.disabled = false;
    inventory.remove.disabled = false;
    inventory.find.disabled = false;
    resize();
    firstclick(e);
}
/**
 * Called when an item is clicked for the first time (doesn't do anything afterwards)
 * Calls resize and shows selected item and makes button deeper color
 *  @param {EventTarget} e click event
 */
function firstclick(e) {
    var add = document.getElementById('add-container');
    var remove = document.getElementById('remove-container');
    var find = document.getElementById('find-container');
    var array = [add, remove, find];
    array.forEach(function (value) {
        if (inventory.firstTime === 'true') {
            inventory.firstTime = 'false';
            if (e === inventory.add) {
                add.classList.add("inventory-first-click");
                return;
            } else if (e === inventory.remove) {
                remove.classList.add("inventory-first-click");
                return;
            } else if (e === inventory.find) {
                find.classList.add("inventory-first-click");
                return;
            }
        } else {
            value.classList.remove('inventory-first-click');
        }
    });
}

/**
 * Resize buttons once one has been clicked
 */
function resize() {
    var array = [inventory.find, inventory.findContainer, inventory.remove, inventory.add, inventory.addTrack];
    array.forEach(function (value) {
        if (!value.classList.contains('inventory-button-clicked')) {
            value.classList.add("inventory-button-clicked");
        }
    });
}

/**
 * Hide or unhide a radio button depending on whether we are adding a song or album
 * !! NOT USED !!
 */
function radioClick(e) {
    if (e === 'Song') {
        document.getElementsByClassName('song-only-field')[0].classList.remove('album-selected');
        document.getElementsByClassName('album-selected-input')[0].disabled = false;
    } else if (e === 'Album') {
        document.getElementsByClassName('song-only-field')[0].classList.add('album-selected');
        document.getElementsByClassName('album-selected-input')[0].disabled = true;
        document.getElementsByClassName('album-selected-input')[0].value = '';
    }
}


