
window.onload = inicio;

READY_STATE_COMPLETE = 4;
HTTP_STATUS_OK = 200;

function inicio() {
    cargar_grafica_fetch();
    selected();
}

let chart = null;

function cargarGrafica(list_json) {

    let jun = 0;
    let fbr = 0;
    let mrz = 0;
    let abrl = 0;
    let may = 0;
    let jn = 0;
    let jl = 0;
    let ags = 0;
    let spt = 0;
    let oct = 0;
    let nov = 0;
    let dic = 0;

    let id_employee;
    
    if (document.getElementById("employee_id")) {
        id_employee = document.getElementById("employee_id").getAttribute("value");
    } else {
        id_employee = document.getElementById("employee_id2").getAttribute("value");
    }
    
    for (let i of list_json) {
        let date = new Date(i.reservaDate);
        console.log(i)
        if (i.comercial.id == id_employee) {
    
            if (date.getMonth() == 0) {
                jun += i.priceBuy;
            }
    
            if (date.getMonth() == 1) {
                fbr += i.priceBuy;
            }
    
            if (date.getMonth() == 2) {
                mrz += i.priceBuy;
            }
    
            if (date.getMonth() == 3) {
                abrl += i.priceBuy;
            }
    
            if (date.getMonth() == 4) {
                may += i.priceBuy;
            }
            
            if (date.getMonth() == 5) {
                jn += i.priceBuy;
            }
    
            if (date.getMonth() == 6) {
                jl += i.priceBuy;
            }
    
            if (date.getMonth() == 7) {
                ags += i.priceBuy;
            }
    
            if (date.getMonth() == 8) {
                spt += i.priceBuy;
            }
    
            if (date.getMonth() == 9) {
                oct = i.priceBuy;
            }
    
            if (date.getMonth() == 10) {
                nov += i.priceBuy;
            }
    
            if (date.getMonth() == 11) {
                dic += i.priceBuy;
            }

            //console.log(i.reservaDate);

        } 
    }

    // jun = Math.floor(Math.random() * 100);
    console.log(jun);
    console.log(fbr);
    console.log(mrz);

    let miCanvas = document.getElementById("grafica").getContext("2d");
    
    if (chart == null) {
        chart = new Chart(miCanvas,{

            type:"bar",
            data:{
                labels:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
                datasets: [
                    {
                        label:"Grafica Ventas Anual",
                        backgroundColor:['rgb(66, 134, 244)','rgb(74, 135, 72)','rgb(229, 89, 50)'],
                        data:[jun,fbr,mrz,abrl,may,jn,jl,ags,spt,oct,nov,dic]
                    }
                ]
            }
        });
    } else {
        chart.data.datasets[0].data[0] = jun;
        chart.data.datasets[0].data[1] = fbr;
        chart.data.datasets[0].data[2] = mrz;
        chart.data.datasets[0].data[3] = abrl;
        chart.data.datasets[0].data[4] = may;
        chart.data.datasets[0].data[5] = jn;
        chart.data.datasets[0].data[6] = jl;
        chart.data.datasets[0].data[7] = ags;
        chart.data.datasets[0].data[8] = spt;
        chart.data.datasets[0].data[9] = oct;
        chart.data.datasets[0].data[10] = nov;
        chart.data.datasets[0].data[11] = dic;
        chart.update();
    }



    //___________________________________________________
    // chart.data.datasets[0].data[0] = jun;
    // chart.data.datasets[0].data[0] = fbr;
    // chart.data.datasets[0].data[0] = mrz;
    // chart.data.datasets[0].data[0] = abrl;

    // console.log(chart.data.datasets[0].data[0]);
    

    //________________________________________________________
    // if (cont == 0) {
    //     var chart = new Chart(miCanvas,{
    
    //         type:"bar",
    //         data:{
    //             labels:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
    //             datasets: [
    //                 {
    //                     label:"Grafica Ventas Anual",
    //                     backgroundColor:['rgb(66, 134, 244)','rgb(74, 135, 72)','rgb(229, 89, 50)'],
    //                     data:[0,f0,0,0,0,0,0,0,0,0,0,0]
    //                 }
    //             ]
    //         }
    //     });
    // }

}


function cargar_grafica_fetch() {
    console.log("cargar_gráfica_fetch");

    // let num_aleatorio = Math.floor(Math.random() * 100) + 1;
    // console.log(num_aleatorio);
    fetch(`http://localhost:8080/api`)
    .then((response) => {
        if (response.ok) {
        return response.json();
        }
    })
    .then((data) => {
        list = data;
        //console.log(data);

        cargarGrafica(data);
    })
    .catch((err) => console.log(err));
}



function selected() {
    if (document.getElementById("employee_id2")) {

        let opt = document.getElementsByClassName("slct");
        let empl = document.getElementById("employee_id2").getAttribute("value");
        console.log("heeeeeeeeeey")
        console.log(empl);
        for (let i of opt) {
            console.log(i);
            if (i.value == empl) {
                i.setAttribute("selected", "selected");
            }
        }
        
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
