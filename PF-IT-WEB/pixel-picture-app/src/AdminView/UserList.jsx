import React from 'react'
import Menu from '../Components/Menu'
import Table from './Components/Table'

export const UserList = () => {
    const columns = ['ID', 'userName', 'Correo', 'Actions']
    const userData = [
        { id: 1, username: 'usuario', correo: 'usuario1@example.com' },
        { id: 2, username: 'usuario2', correo: 'usuario2@example.com' },
        { id: 3, username: 'usuario3', correo: 'usuario3@example.com' },
        { id: 4, username: 'usuario4', correo: 'usuario4@example.com' },
        { id: 5, username: 'usuario5', correo: 'usuario5@example.com' },
        { id: 6, username: 'usuario6', correo: 'usuario6@example.com' },
        { id: 7, username: 'usuario7', correo: 'usuario7@example.com' },
        { id: 8, username: 'usuario8', correo: 'usuario8@example.com' },
        { id: 9, username: 'usuario9', correo: 'usuario9@example.com' },
        { id: 10, username: 'usuario10', correo: 'usuario10@example.com' },
      ];
  return (
    <div>
        <Menu />
        <Table columns={columns} data={userData} pageSize={5} />
    </div>
  )
}
