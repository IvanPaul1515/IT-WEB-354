import { createEndPoint } from "./api/endPoint.controller.js";


const estado = document.getElementById("status");
const method = document.getElementById("method");
const contentType = document.getElementById("contentType");
const charset = document.getElementById("charset");
const header = document.getElementById("headers");
const body = document.getElementById("body");
const url = document.getElementById("url");
const nombre = document.getElementById("nombre");
const descripcion = document.getElementById("description");
const timeDemora = document.getElementById("timeDemora");
const cbxJWT = document.getElementById("cbxJWT");
const timeExpired = document.getElementById("timeExpire");
const btnSend = document.getElementById("btnSend");



btnSend.addEventListener("click", async (e) => {
    e.preventDefault();

    const fecha = obtenerFecha();
    const timeExp = calcTimeExpired(timeExpired.value, fecha)

    const request = {
        id: Date.now(),
        estado: estado.value,
        method: method.value,
        contentType: contentType.value,
        charset: charset.value,
        header: header.value,
        body: body.value,
        url: url.value,
        nombre: nombre.value,
        descripcion: descripcion.value,
        timeDemora: timeDemora.value,
        cbxJWT: cbxJWT.checked, // Si es un checkbox, obtén el estado "checked"
        timeExpired: timeExp,
        fechaCreacion:fecha
    }


    const data = await createEndPoint(request);
    console.log(data)


})



const obtenerFecha = () => {
    const fecha = new Date();

    // Obtiene el día, el mes y el año
    const dia = fecha.getDate().toString().padStart(2, '0');
    const mes = (fecha.getMonth() + 1).toString().padStart(2, '0'); // Los meses se cuentan desde 0 (enero) hasta 11 (diciembre)
    const anio = fecha.getFullYear();

    // Formatea la fecha como "dd/mm/yyyy"
    const fechaFormateada = `${dia}/${mes}/${anio}`;

    return fechaFormateada;
}

const calcTimeExpired = (time, fecha) => {
    if (fecha.match(/^\d{2}\/\d{2}\/\d{4}$/)) {
        // Divide la fecha en día, mes y año
        const [dia, mes, anio] = fecha.split('/').map(Number);

        // Crea una nueva fecha a partir de la fecha dada
        const fechaOriginal = new Date(anio, mes - 1, dia);

        // Calcula el tiempo adicional según el valor de "time"
        let tiempoAdicional = 0;
        switch (time) {
            case "1 año":
                tiempoAdicional = 12; // 12 meses en un año
                break;
            case "1 mes":
                tiempoAdicional = 1;
                break;
            case "1 semana":
                tiempoAdicional = 7; // 7 días en una semana
                break;
            case "1 hora":
                tiempoAdicional = 1 / 24; // 1 hora en fracción de días
                break;
            case "1 dia":
                tiempoAdicional = 1;
                break;
            default:
                return fecha; // Valor de "time" no válido, devuelve la misma fecha
        }

        // Calcula la nueva fecha sumando el tiempo adicional
        fechaOriginal.setDate(fechaOriginal.getDate() + tiempoAdicional);

        // Obtiene el nuevo día, mes y año
        const nuevoDia = fechaOriginal.getDate().toString().padStart(2, '0');
        const nuevoMes = (fechaOriginal.getMonth() + 1).toString().padStart(2, '0');
        const nuevoAnio = fechaOriginal.getFullYear();

        // Formatea la nueva fecha como "dd/mm/yyyy"
        const fechaNueva = `${nuevoDia}/${nuevoMes}/${nuevoAnio}`;

        return fechaNueva;
    } else {
        // Devuelve la misma fecha si no se cumple la condición
        return fecha;
    }
};
