import React from 'react'
import logo from '../img/pixel-logo.png'
import { closeSession } from '../API/auth'


export const MenuCliente = () => {
  return (
    <div className='bg-blue-800 flex justify-between'>
      <img id='imgLogo' src={logo} className='my-3 mx-10'/>
      <nav className='flex justify-end px-10 items-center'>
      <ul className='h-16 w-96 text-white flex justify-between items-center text-xl'>
        <li className='cursor-pointer underline-animation' > <a href="/homeClient">Home</a> </li>
        <li className='cursor-pointer underline-animation' > <a href="/AllSalles">Mis compras</a></li>
          <li className='cursor-pointer underline-animation' onClick={() => closeSession()}>Cerrar sesion</li>
      </ul>
      </nav>
    </div>
  )
}
