import { createUser } from "./api/user.controller.js";

const nombre = document.getElementById("name");
const apellido = document.getElementById("lastName");
const userName = document.getElementById("userName");
const password = document.getElementById("password");
const btnRegistrar = document.getElementById("btnRegistrar");

btnRegistrar.addEventListener("click", (e) => {
    e.preventDefault();
    let usuario = {
        id: Date.now(),
        nombre: nombre.value,
        apellido: apellido.value,
        usuario:userName.value,
        contrasena: password.value
    }

    createUser(usuario);
})
