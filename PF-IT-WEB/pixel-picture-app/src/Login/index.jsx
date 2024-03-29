import React, { useState } from "react";
import { loginUser } from "../API/usersAPI";
import logo from "../img/pixel-logo.png";

export const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
    // Aquí puedes agregar la lógica de autenticación
    console.log("Usuario:", username, "Contraseña:", password);

    loginUser(username, password);
  };

  return (
      <div className="min-h-screen flex flex-col items-center justify-center bg-blue-800 text-center">
        <img src={logo} alt="Logo" className="mb-8 w-32 h-32"/> {/* Agrega la imagen aquí */}
        <div className="bg-white p-8 rounded shadow-md w-96">
          <h2 className="text-2xl font-bold mb-4">Iniciar Sesión</h2>
          <form onSubmit={handleLogin}>
            <div className="mb-4">
              <label htmlFor="username" className="block text-gray-700 text-sm font-bold mb-2">
                Nombre de usuario
              </label>
              <input
                  type="text"
                  id="username"
                  name="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  className="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500"
                  required
              />
            </div>
            <div className="mb-4">
              <label htmlFor="password" className="block text-gray-700 text-sm font-bold mb-2">
                Contraseña
              </label>
              <input
                  type="password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full px-3 py-2 border rounded-md focus:outline-none focus:border-blue-500"
                  required
              />
            </div>
            <button
                type="submit"
                className="bg-blue-800 text-white px-4 py-2 rounded-md hover:bg-opacity-80 focus:outline-none"
            >
              Iniciar Sesión
            </button>
          </form>
          <p className="mt-4">No estas registrado?, registrate <a
              className="text-blue-800 font-bold underline hover:decoration-4" href="/userForm/"> aqui</a></p>
        </div>
      </div>
  );
};
