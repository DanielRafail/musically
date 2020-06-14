var purchasable = {
    "arr": "",
};

document.addEventListener("DOMContentLoaded", function () {
    addContentEditable();   
});

/**
 * Makes content editable if you are an admin for tracks and albums 
 * !! NOT USED !!
 */
function addContentEditable() {
    if (!(document.getElementById("nav-container-manager") === null)) {
        var allElements = document.getElementsByClassName('information-editable');
        var arr = [];
        for (var i = 0; i < allElements.length; i++) {
            arr.push(allElements[i]);
        }
        purchasable.arr = arr;
        arr.forEach(function (value) {
            value.contentEditable = "true";
        });
    }
}

/*
function acceptEdits(e) {
    var focused = false;
    purchasable.arr.forEach(function (value) {
        focused = document.activeElement === value;
        if (focused) {
            if (e.which == 13) {
                e.preventDefault();
                document.activeElement.blur();
            } else
                value.children[0].textContent += e.key;
        }
    });
}

*/