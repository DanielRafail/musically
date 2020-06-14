//https://www.tutorialrepublic.com/faq/how-to-get-selected-file-name-from-input-type-file-using-jquery.php
//https://tympanus.net/codrops/2015/09/15/styling-customizing-file-inputs-smart-way/
/**
 * So file input button works on firefox
 */
$(document).ready(function () {
    var input = document.getElementsByClassName('file-choser')[0];
    if (typeof input !== "undefined") {
        input.addEventListener('focus', function () {
            input.classList.add('has-focus');
        });
        input.addEventListener('blur', function () {
            input.classList.remove('has-focus');
        });
        $('input[type="file"]').change(function (e) {
            var fileName = e.target.files[0].name;
            document.getElementsByClassName('file-name')[0].textContent = fileName;
        });
    }
});