const url = process.env.REACT_APP_API_URL;

export const getServices = async () => {
    try {  
      const response = await fetch(url + '/servicio/');
  
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      console.error(error);
      return [];
    }
  };

export const getServiceById = async (id) => {
    try {
        const response = await fetch(`${url}/servicio/${id}`)
        const data = await response.json();
        return data

    } catch (error) {
        console.log(error);
    }
}

export const PayService = async (precio, servicio) => {

    var formData = new FormData();
    formData.append("cmd", "_xclick");
    formData.append("business", process.env.REACT_APP_ID_NEGOCIO);
    formData.append("amount", precio)
    formData.append("currency_code", "USD");
    formData.append("rm", "2");
    formData.append("invoice", Date.now().toString())
    formData.append('item_name', servicio)
    formData.append("return", "http://127.0.0.1:3000/detalle");
    formData.append("cancel_return", "http://127.0.0.1:3000/HomeClient");
    // Agrega más campos ocultos según sea necesario

    try {
        const response = await fetch("https://www.sandbox.paypal.com/cgi-bin/webscr", {
            method: "POST",
            body: formData,
            // mode: "no-cors"
        })

        console.log(response);
    } catch (error) {
        console.error(error);
    }
}


export const createService = async (service) => {

    service.id = Date.now();

    try {
        const response = await fetch(`${url}/servicio/new`, {
            method: "POST",
            body: JSON.stringify(service),
            headers: {
                'Content-type': 'application/json'
            }
        });

        if (response.ok) {
            const data = await response.json();
            console.log(data);
            return data;
        } else {
            throw new Error('Error en la solicitud');
        }


    } catch (error) {
        console.log(error);
    }
}

export const deleteServiceById = async (code) => {
    try {
        const response = await fetch(`${url}/servicio/${code}`, {
            method: "DELETE",
            headers: {
                'Content-type': 'application/json'
            }
        });

        if (response.ok) {
            alert("usuario eliminado correctamente.");
        } else {
            console.log("HTTP request unsuccessful");
        }

        const data = await response.json();
        console.log(data);
    } catch (error) {
        console.log(error);
    }

}

export const updateServiceById = async (obj) => {

    try {
        const response = await fetch(`${url}/servicio/`, {
            method: "PUT",
            body: JSON.stringify(obj),
            headers: {
                'Content-type': 'application/json'
            }
        });

        if (response.ok) {
            alert("Articulo editado correctamente.");
        } else {
            console.log("HTTP request unsuccessful");
        }

        const data = await response.json();
        return data
    } catch (error) {
        console.log(error);
    }
}