
window.onload = inicio;

function inicio() {
    addActive();
}

function addActive() {
    let items = document.getElementsByClassName("carousel-item");
    items[0].classList.add("active");
}

