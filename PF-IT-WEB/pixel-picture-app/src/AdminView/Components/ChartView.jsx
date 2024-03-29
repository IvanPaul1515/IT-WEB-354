import React, { useState, useEffect } from 'react';
import { Chart as ChartJS } from 'chart.js/auto';
import { Bar, Doughnut} from 'react-chartjs-2';


const ChartView = ({datos}) => {



//   Filtrar por fecha actual
  const currentDate = "27/12/2023"; //formatDate(new Date())
  const filteredData = datos.filter(item => item.fecha == currentDate);

//   Calcular la cantidad de compras realizadas, solicitudes pendientes y realizadas
  const comprasRealizadas = filteredData.filter(item => item.estado === 'Completado').length;
  const solicitudesPendientes = filteredData.filter(item => item.estado === 'Pendiente').length;
  const solicitudesRealizadas = filteredData.filter(item => item.estado === 'En proceso').length;

  const data = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        label: 'My First Dataset',
        data: [65, 59, 80, 81, 56, 55, 40],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }
    ]
  };

  const chartData = {
    labels: ['Compras Realizadas', 'Solicitudes Pendientes', 'Solicitudes Realizadas'],
    datasets: [
      {
        label: 'Cantidad',
        data: [comprasRealizadas, solicitudesPendientes, solicitudesRealizadas],
        backgroundColor: ['#3498db', '#e74c3c', '#2ecc71'],
      },
    ],
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4 text-center">Estadísticas del día</h2>
      <div className='w-2/4 h-1/2 m-auto'>
        <Doughnut data={chartData} />
      </div>
    </div>
  );
};

function formatDate(date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
  
    return `${day}/${month}/${year}`;
  }

export default ChartView;


