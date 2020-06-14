
var credit = {
    "chosen": false
};

document.addEventListener("DOMContentLoaded", function () {
    document.getElementsByClassName('back')[0].addEventListener('click', buttonClick);
    document.getElementsByClassName('continue')[0].addEventListener('click', buttonClick);
    document.getElementsByClassName('checkout')[0].addEventListener('click', buttonClick);
    document.getElementsByClassName('visa')[0].addEventListener('click', choseCard);
    //document.getElementsByClassName('paypal')[0].addEventListener('click', choseCard);
    document.getElementsByClassName('master-card')[0].addEventListener('click', choseCard);
});

/**
 * Verify you clicked on a card
 *  @param {EventTarget} e click event
 */

function choseCard(e) {
    e = e || window.event;
    e = e.target || e.srcElement;
    if (e.classList.contains('visa') || e.classList.contains('master-card') /*|| e.classList.contains('paypal')*/) {
        credit.chosen = true;
    }
}

/**
 * Verify you clicked on a button, and decide what to show depending on the button
 *  @param {EventTarget} e click event
 */
function buttonClick(e) {
    e = e || window.event;
    e.preventDefault();
    e = e.target || e.srcElement;
    if (e.classList.contains('back')) {
        showCards();
    } else if (e.classList.contains('continue')) {
        showInfo();
    }
}

/**
 * Show credit card input screen and hide credit card selection screen
 */
function showInfo() {
    if (credit.chosen) {
        document.getElementsByClassName('cardError')[0].classList.add('hide');
        document.getElementById('creditcardInformation').classList.remove('hide');
        document.getElementById('creditCardOption').classList.add('hide');
    } else {
        document.getElementsByClassName('cardError')[0].classList.remove('hide');
    }
}

/**
 * Show credit card selection screen and hide credit card input screen
 */
function showCards() {
    document.getElementById('creditcardInformation').classList.add('hide');
    document.getElementById('creditCardOption').classList.remove('hide');
    document.getElementsByClassName('input-name')[0].textContent = "";
    document.getElementsByClassName('input-number')[0].textContent = "";
    document.getElementsByClassName('input-cvc')[0].textContent = "";
    document.getElementsByClassName('input-expiration')[0].textContent = "";
}


