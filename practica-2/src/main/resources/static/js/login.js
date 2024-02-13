import { loginUser } from "./api/user.controller.js";

const userName = document.getElementById("userName");
const password = document.getElementById("password");
const btnLogin = document.getElementById("btnLogin");

btnLogin.addEventListener("click", async (e) => {
    e.preventDefault();
    const response = await loginUser(userName.value, password.value);

    if (response.ok) {
        const jwt = await response.text();
        saveJWT(jwt);
        window.location.href = "../home.html";
    }
})


const saveJWT = (jwt) => {
    localStorage.setItem("jwt", jwt);
}