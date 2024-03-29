
import React, {useEffect, useState} from 'react';
import Table from './Components/Table';
import Menu from '../Components/Menu';
import ChartView from './Components/ChartView';
import { getUsers } from '../API/usersAPI';

export const HomeAdmin = () => {

  const userColumns = ['ID', 'Nombre', "UserName" ,'Correo', "rol" , 'Acciones'];
  const userData = [
    { id: 1, nombre: 'Usuario1', correo: 'usuario1@example.com' },
    { id: 2, nombre: 'Usuario2', correo: 'usuario2@example.com' },
    { id: 3, nombre: 'Usuario3', correo: 'usuario3@example.com' },
    // Agrega más filas según sea necesario
  ];

  const [userData2, setUserData2] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getUsers();
        setUserData2(data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();

  }, [])
  


  const orderColumns = ['ID', 'Servicio', 'Encargado', 'Fecha', 'Estado', 'Acciones'];
  const orderData = [
    { id: 1, servicio: 'Fotografía de Bodas', encargado: 'John Doe', fecha: '27/12/2023', estado: 'En proceso' },
    { id: 2, servicio: 'Videografía de Bodas', encargado: 'Jane Smith', fecha: '27/12/2023', estado: 'Completado' },
    { id: 3, servicio: 'Sesión Pre-Boda', encargado: 'Alice Johnson', fecha: '27/12/2023', estado: 'Pendiente' },
    { id: 1, servicio: 'Fotografía de Bodas', encargado: 'John Doe', fecha: '27/12/2023', estado: 'En proceso' },
    { id: 2, servicio: 'Videografía de Bodas', encargado: 'Jane Smith', fecha: '27/12/2023', estado: 'Completado' },
    { id: 3, servicio: 'Sesión Pre-Boda', encargado: 'Alice Johnson', fecha: '27/12/2023', estado: 'Pendiente' },
    { id: 1, servicio: 'Fotografía de Bodas', encargado: 'John Doe', fecha: '27/12/2023', estado: 'En proceso' },
    { id: 2, servicio: 'Videografía de Bodas', encargado: 'Jane Smith', fecha: '27/12/2023', estado: 'Completado' },
    { id: 3, servicio: 'Sesión Pre-Boda', encargado: 'Alice Johnson', fecha: '27/12/2023', estado: 'Pendiente' },
    // Agrega más filas según sea necesario
  ];


  return (
    <div className=''>
        <Menu />
        <div className='container m-auto'>
            <h1 className='text-4xl text-center mt-6 font-bold'>Panel de administrador</h1>
            <div className='p-4'>
              <h2 className='text-2xl font-semibold text-center mb-2'>Listado de ordenes</h2>
                <Table columns={orderColumns} data={orderData} pageSize={10} />
            </div>
            <div className='p-4'>
                <h2 className='text-2xl font-semibold text-center mb-2'>Listado de usuarios</h2> 
                <Table columns={userColumns} data={userData2} pageSize={10} />
            </div>
            <ChartView datos={orderData} />
            
        </div>
    </div>
  );
};

