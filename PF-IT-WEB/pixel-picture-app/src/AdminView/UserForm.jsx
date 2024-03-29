import React, { useState, useEffect } from 'react';
import Menu from '../Components/Menu';
import { useParams } from 'react-router-dom';
import { createUser, getUserByUsername, getUserById } from '../API/usersAPI';

const UserForm = () => {
  const { id } = useParams();
  const [userRole, setUserRole] = useState(null);
  const [userData, setUserData] = useState({ id: 0, nombre: '', userName: '', password: '', correo: '', rol: '' });
  const [confirmPassword, setConfirmPassword] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userName = localStorage.getItem('userName');

        if (userName == null) {
          setUserRole('Cliente');
        } else {
          const usuario = await getUserByUsername(userName);
          setUserRole(usuario.rol);
        }

        if (id) {
          // Si hay un parámetro en la URL (modo edición), carga los datos del usuario por ID
          const userById = await getUserById(id);
          setUserData(userById);
        }
      } catch (error) {
        console.error(error);
        // Maneja el error según tus necesidades
      }
    };

    fetchData();
  }, [id]); // Agrega id como dependencia para que se ejecute cuando cambia

  const handleInputChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleConfirmPasswordChange = (e) => {
    const inputValue = e.target.value;
    setConfirmPassword(inputValue);

    const input = document.getElementById('confirmPassword');

    if (inputValue !== userData.password) {
      input.classList.add('border-red-500');
      input.classList.add('focus:border-red-500');
      console.log('diferentes');
    } else {
      input.classList.remove('border-red-500');
      input.classList.remove('focus:border-red-500');
      console.log('iguales');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (confirmPassword !== userData.password) {
      alert('Las contraseñas no coinciden');
      return;
    }

    await createUser(userData);

    console.log(userData);
    window.location.reload();

  };

  return (
    <div className="">
      <Menu />
      <div className="max-w-md mx-auto bg-white p-8 rounded-md shadow-md mt-14">
        <h2 className="text-2xl font-bold mb-6 text-center">Crear Nuevo Usuario</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
              Nombre Completo:
            </label>
            <input
              type="text"
              id="nombre"
              name="nombre"
              placeholder="Ej: Bryant Reynoso"
              value={userData.nombre}
              onChange={handleInputChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-700"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
              Nombre de usuario:
            </label>
            <input
              type="text"
              id="userName"
              name="userName"
              value={userData.userName}
              onChange={handleInputChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-700"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
              Correo Electrónico:
            </label>
            <input
              type="email"
              id="correo"
              name="correo"
              value={userData.correo}
              onChange={handleInputChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-700"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
              Contraseña:
            </label>
            <input
              type="password"
              id="password"
              name="password"
              value={userData.password}
              onChange={handleInputChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-700"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
              Confirmar Contraseña:
            </label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={confirmPassword}
              onChange={handleConfirmPasswordChange}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-500"
            />
          </div>
          {
            userRole === 'ADMIN' && (
              <div className="mb-4">
                <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                  Rol:
                </label>
                <select
                  id="rol"
                  name="rol"
                  value={userData.rol}
                  onChange={handleInputChange}
                  className="w-full px-4 py-2 border rounded-md focus:outline-none focus:border-blue-500"
                >
                  <option value="Seleccionar">Seleccionar</option>
                  <option value="ADMIN">Administrador</option>
                  <option value="CLIENTE">Cliente</option>
                  <option value="EMPLOYEER">Empleado</option>
                </select>
              </div>
            )
          }
          <div className="text-center">
            <button
              type="submit"
              className="w-full bg-blue-700 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600"
            >
              Crear Usuario
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserForm;





