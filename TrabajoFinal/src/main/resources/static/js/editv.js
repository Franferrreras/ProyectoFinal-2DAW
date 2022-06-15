
window.onload = inicio;
function inicio() {
    changetimg();
}


function changetimg() {

    let img = document.getElementById("imgv");

    let list_img = document.getElementsByClassName("minicard--vimg");

    for (let i of list_img) {
        i.addEventListener("click", function () {
            let src = i.children[0].getAttribute("src");

            console.log(src);
            img.removeAttribute("src");
            img.setAttribute("src",src);
        })
    }
}

const toggle = document.querySelector(".toggle")
const menuDashboard = document.querySelector(".menu-dashboard")
const iconoMenu = toggle.querySelector("i")
const enlacesMenu = document.querySelectorAll(".enlace")

toggle.addEventListener("click", () => {
    menuDashboard.classList.toggle("open")

    if(iconoMenu.classList.contains("bx-menu")){
        iconoMenu.classList.replace("bx-menu", "bx-x")
    }else {
        iconoMenu.classList.replace("bx-x", "bx-menu")
    }
})

enlacesMenu.forEach(enlace => {
    enlace.addEventListener("click", () => {
        menuDashboard.classList.add("open")
        iconoMenu.classList.replace("bx-menu", "bx-x")
    })
})