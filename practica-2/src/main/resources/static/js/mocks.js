import { getEndPoints } from "./api/endPoint.controller";

const tbody = document.getElementById("tBody");



const datos = getEndPoints();

datos.forEach(item => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${item.id}</td>
        <td>
            <div class="detalles">
                <p class="texto-detalle">${item.metodo}</p>
                <p class="texto-detalle">${item.codigoRespuesta}</p>
                <p class="texto-detalle">${item.contentType}</p>
                <p class="texto-detalle">UTF-8</p>
            </div>
            <p class="body-response">{${item.cuerpo}}</p>
        </td>
        <td>${item.fechaCreacion}</td>
        <td class="-action">
            <a href="https://api.example.com/data" target="_blank" class="action-link">
                <i class="fas fa-external-link-alt"></i>
            </a>
            <span class="action-icon" onclick="eliminarFila(this)">
                <i class="fas fa-trash"></i>
            </span>
    </td>
    
    `;

    tbody.appendChild(tr);

});

