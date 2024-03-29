import React from 'react'
import Menu from '../Components/Menu'
import Table from '../AdminView/Components/Table'

export const AllSales = () => {
  return (
    <div>
        <Menu />
        <div>
            <h1 className='text-4xl'>Lista de todas las compras realizadas</h1>
            <Table columns={["ID", "Servicio", "Fecha"]} data={[{ID: 1, Servicio: "prueba", Fecha: "10/11/2024" }]} pageSize={5} />
        </div>
    </div>
  )
}
