window.onload=inicio;

READY_STATE_COMPLETE = 4;
HTTP_STATUS_OK = 200;

function inicio() {
    cargar_api2();
}

function heartSelected() {

    let btn = document.getElementsByClassName("btnheart");

    for (let i of btn) {
        console.log(i);
        i.addEventListener("click",function() {
            console.log(i.children[0].children[0]);
            i.children[0].children[0].classList.add("icon___2c4Kt-selected");
        });
    }
}


function heartFill(list_json) {

    let btns = document.getElementsByClassName("btnheart");

    for (let j of btns) {
        for (let i of list_json) {
            console.log(j.getAttribute("value"));
            if (j.getAttribute("value") == i.vehicle.id) {
                j.children[0].setAttribute("hidden","true");
                j.children[1].removeAttribute("hidden");
                // j.children[0].children[0].classList.add("icon___2c4Kt-selected");
            }
        }
    }
}


function cargar_api2() {
    console.log("cargar_api2");

    // let num_aleatorio = Math.floor(Math.random() * 100) + 1;
    // console.log(num_aleatorio);
    if (document.getElementById("lg") != null) {
        fetch(`http://localhost:8080/api/2`)
        .then((response) => {
            if (response.ok) {
            return response.json();
            }
        })
        .then((data) => {
            list = data;
            //console.log(data);
            heartFill(data);
            
        })
        .catch((err) => console.log(err));
    } else {
        console.log("No exist api")
    }

}

if (document.querySelector(".toggle") != null) {
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
}