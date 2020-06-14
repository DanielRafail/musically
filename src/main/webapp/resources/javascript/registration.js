//Hide the name of the user in the drop down menu
document.addEventListener("DOMContentLoaded", function () {
    if (typeof document.getElementsByTagName('select')[0] !== "undefined") {
        document.getElementsByTagName('option')[0].classList.add('hide');
        document.getElementsByTagName('select')[0].selectedIndex = 0;
    }
});