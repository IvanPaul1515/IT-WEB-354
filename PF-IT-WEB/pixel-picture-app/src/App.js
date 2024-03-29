import './App.css';
import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import {Login} from './Login/index'
import { HomeClient } from './ClientView/HomeClient';
import { HomeAdmin } from './AdminView/HomeAdmin';
import UserForm from './AdminView/UserForm';
import { UserList } from './AdminView/UserList';
import EmployeeDashboard from './EmployeerView/EmployeeDashBoard';
import { getUserByUsername } from './API/usersAPI';
import { AllSales } from './ClientView/AllSales';


function App() {


  const [userRole, setUserRole] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userName = localStorage.getItem('userName');
        const usuario = await getUserByUsername(userName)

        setUserRole(usuario.rol);
      } catch (error) {
        console.error(error);
        // Maneja el error según tus necesidades
      }
    };

    fetchData();
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={ <Login /> } />
        {/* <Route path='*' element={<NotFound />} /> */}
        <Route path='/userForm/:id?' element={ <UserForm /> } />

        {userRole === "ADMIN" && (
            <>
              <Route path='/homeAdmin' element={ <HomeAdmin /> } />
              <Route path='/userForm/:id?' element={ <UserForm /> } />
            </>
          )}
        {userRole === "CLIENTE" && (
            <>
              <Route path='/HomeClient' element={ <HomeClient />} />
              <Route path='/AllSalles' element={ <AllSales />} />
            </>
          )}
        {userRole === "EMPLOYEER" && (
            <>
              <Route path='/EmployeeDashboard' element={ <EmployeeDashboard /> } />
            </>
          )}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
