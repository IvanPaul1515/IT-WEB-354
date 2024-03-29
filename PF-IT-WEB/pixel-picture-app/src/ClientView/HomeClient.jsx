import React, { useState } from 'react';
import { PayPalScriptProvider, PayPalButtons } from '@paypal/react-paypal-js';
import { useNavigate } from 'react-router-dom';
import Menu from '../Components/Menu';
import { PayService } from '../API/serviciosAPI';

export const HomeClient = () => {

    // useEffect(() => {
    //     const script = document.createElement('script');
    //     script.src = 'https://www.paypal.com/sdk/js?client-id=' + process.env.REACT_APP_ID_NEGOCIO;
    //     script.async = true;
    //     script.onload = () => {
    //       // El SDK de PayPal se ha cargado, puedes inicializar y usar las funciones de PayPal aquí
    //     };
    //     document.body.appendChild(script);

    //     return () => {
    //       // Limpiar el script al desmontar el componente
    //       document.body.removeChild(script);
    //     };
    //   }, []);


  const navigate = useNavigate();
  const [selectedService, setSelectedService] = useState(null);

  const handleOnApprove = (data, actions) => {
    console.log('Pago aprobado:', data);
    // Puedes realizar acciones adicionales después de que el pago ha sido aprobado
    navigate('/homeClient');
  };

  const handleOnCancel = () => {
    console.log('Pago cancelado');
    // Redirige a /homeAdmin después de cancelar la transacción
    navigate('/homeClient');
  };

  const handleService = (e) => {
    const titulo = e.currentTarget.querySelector('#titulo').textContent;
    const precio = parseInt(e.currentTarget.querySelector('#precio').textContent.replace('$', '').replace(' RD', ''));
    const facturaId = Math.random().toString(36).substring(7); // ID único para la factura

    setSelectedService({ titulo, precio, facturaId });
  };

  const createOrder = (data, actions) => {
    return actions.order.create({
      purchase_units: [
        {
          amount: {
            value: selectedService?.precio || '0.00',
          },
          invoice_id: selectedService?.facturaId || '',
        },
      ],
    });
  };

  return (
    <div>
      <Menu />
      <h1 className='text-center text-4xl font-bold mt-5'>Servicios</h1>
      <div className='flex flex-wrap container m-auto justify-between mt-12'>
        {/* Mapea tus servicios aquí */}
        {[1, 2, 3, 4].map((index) => (
          <div
            key={index}
            className='bg-gradient-to-r from-blue-700 to-blue-900 flex flex-col w-2/5 p-6 mb-10 border rounded-lg cursor-pointer shadow-md transform transition-transform hover:scale-105'
            onClick={(e) => handleService(e)}
          >
            <h3 className='font-bold text-2xl text-center mb-4 text-white' id='titulo'>Sesión Pre-Boda</h3>
            <p className='text-justify mb-4 text-white'>
              Celebra el amor en sus primeras etapas con nuestro servicio de Pre-Boda. Capturamos los momentos más especiales y emotivos antes de tu gran día. Nuestro equipo de fotógrafos y videógrafos se esmera en inmortalizar la esencia de vuestro compromiso, creando recuerdos que durarán toda la vida. Garantizamos una experiencia visual única que reflejará la belleza de este capítulo inicial de tu historia de amor.
            </p>
            <p className='bg-blue-900 text-white px-4 py-2 rounded-full inline-block mb-2'>Precio: <span id='precio' className='font-bold'>$1000 RD</span></p>
          </div>
        ))}
        {selectedService && (
          <PayPalScriptProvider options={{ 'client-id': process.env.REACT_APP_ID_NEGOCIO }}>
            <PayPalButtons
              style={{ layout: 'horizontal' }}
              createOrder={(data, actions) => createOrder(data, actions)}
              onApprove={(data, actions) => handleOnApprove(data, actions)}
              onCancel={() => handleOnCancel()}
            />
          </PayPalScriptProvider>
        )}
      </div>
    </div>
  );
};

export default HomeClient;
