
import { useState } from "react";
import { FaCheck } from 'react-icons/fa';
import { deleteUserById, updateUserById } from "../../API/usersAPI";
import { deleteServiceById, updateServiceById } from "../../API/serviciosAPI";

const Table = ({ columns, data, pageSize }) => {


  const [currentPage, setCurrentPage] = useState(1);

  const totalPages = Math.ceil(data.length / pageSize);

  const startIndex = (currentPage - 1) * pageSize;
  const endIndex = startIndex + pageSize;

const convertirObjetosAMinusculas = (arreglo) => {
  return arreglo.map((objeto) => {
    let objetoEnMinusculas = {};
    for (const key in objeto) {
      if (Object.hasOwnProperty.call(objeto, key)) {
        objetoEnMinusculas[key.toLowerCase()] = objeto[key];
      }
    }
    return objetoEnMinusculas;
  });
}

const dataMinusculas = convertirObjetosAMinusculas(data)

  const currentData = dataMinusculas.slice(startIndex, endIndex);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const handleVer = () => {
    if (columns.includes("Servicio")) {
      //Aqui va la llamada a la api del servicio
      alert("Esta es una tabla de servicios")
    } else if (columns.includes('Nombre')) {
      alert("Esta es una tabla de Usuarios")

    }
  }

  const handleEditar = async (e) => {
    const id = parseInt(e.target.parentElement.parentElement.parentElement.firstChild.textContent);

    if (columns.includes("Servicio")) {
      //Aqui va la llamada a la api del servicio
      await updateServiceById(id);

    } else if (columns.includes('Nombre')) {


      window.location.href = "/userForm/" + id;
    }
  }

  const handleVerEliminar = async (e) => {
    const id = parseInt(e.target.parentElement.parentElement.parentElement.firstChild.textContent);
    if (columns.includes("Servicio")) {
      //Aqui va la llamada a la api del servicio
      await deleteServiceById(id);
    } else if (columns.includes('Nombre')) {
      await deleteUserById(id);
    }
  }



  return (
    <div className="overflow-x-auto w-10/12 m-auto" style={{ height: "auto" }}>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr>
            {columns.map((column) => (
              <th key={column} className="py-2 px-4 text-center font-bold">
                {column}
              </th>
            ))}
            {columns.includes("actions") && <th className="py-2 px-4 text-center font-bold">Actions</th>}
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-300">
          {currentData.map((row, index) => (
            <tr key={index}>
              {columns.map((column) => (
                <td key={column} className="py-2 px-4 text-center ">
                  {row.hasOwnProperty(column.toLowerCase()) ? row[column.toLowerCase()] : (
                    column.toLowerCase() === "seleccionar" ? (
                      <button className="bg-blue-800 text-white rounded w-12 h-10 flex justify-center items-center text-xl ml-28" onClick={() => console.log(`Seleccionado: ${row.id}`)}>
                        <FaCheck />
                      </button>
                    ) : (
                      <div className="py-2 px-4 flex justify-center">
                        <button onClick={() => handleVer()} className="mr-2 text-blue-500 cursor-pointer underline-animation-blue">Ver</button>
                        <button onClick={(e) => handleEditar(e)}  className="mr-2 text-green-500 cursor-pointer underline-animation-green">Editar</button>
                        <button onClick={(e) => handleVerEliminar(e)} className="text-red-500 cursor-pointer underline-animation-red">Eliminar</button>
                      </div>
                    )
                  )}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>

      <div className="pagination mt-4">
        {Array.from({ length: totalPages }, (_, index) => index + 1).map((page) => (
          <button
            key={page}
            onClick={() => handlePageChange(page)}
            className={`mx-1 px-3 py-2 border ${currentPage === page ? 'bg-blue-700 text-white' : 'hover:bg-gray-200'}`}
          >
            {page}
          </button>
        ))}
      </div>
    </div>
  );
};

export default Table;

