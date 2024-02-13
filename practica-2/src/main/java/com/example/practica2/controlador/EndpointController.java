package com.example.practica2.controlador;

import com.example.practica2.entidades.Endpoint;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.practica2.servicios.EndpointService;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointController {

    private final EndpointService endpointService;

    @Autowired
    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @PostMapping
    public ResponseEntity<Endpoint> crearEndpoint(@RequestBody Endpoint endpoint) {
        // Llama al servicio para crear un nuevo endpoint
        Endpoint nuevoEndpoint = endpointService.crearEndpoint(endpoint);
        MediaType contentType = null;

        if (nuevoEndpoint.getContentType().equals("text/plain")) {
            contentType = MediaType.TEXT_PLAIN;
        } else if (nuevoEndpoint.getContentType().equals("text/html")) {
            contentType = MediaType.TEXT_HTML;
        } else if (nuevoEndpoint.getContentType().equals("application/json")) {
            contentType = MediaType.APPLICATION_JSON;
        } else if (nuevoEndpoint.getContentType().equals("application/xml")) {
            contentType = MediaType.APPLICATION_XML;
        }

        HttpHeaders headers = new HttpHeaders();
        if (!nuevoEndpoint.getHeaders().isEmpty()) {
            JSONObject headersJson = new JSONObject(endpoint.getHeaders());
            for (String key : headersJson.keySet()) {
                headers.add(key, headersJson.getString(key));
            }
        }

        try {
            Thread.sleep(nuevoEndpoint.getDemoraRespuesta() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return ResponseEntity
                .status(Integer.parseInt(nuevoEndpoint.getStatus()))
                .contentType(contentType)
                .headers(headers)
                .body(nuevoEndpoint);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endpoint> obtenerEndpointPorId(@PathVariable String id) {
        // Llama al servicio para obtener un endpoint por su ID
        Endpoint endpoint = endpointService.obtenerEndpointPorId(id);
        return ResponseEntity.ok(endpoint);
    }

    @GetMapping
    public ResponseEntity<List<Endpoint>> obtenerTodosLosEndpoints() {
        // Llama al servicio para obtener todos los endpoints
        List<Endpoint> endpoints = endpointService.obtenerTodosLosEndpoints();
        return ResponseEntity.ok(endpoints);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endpoint> actualizarEndpoint(@PathVariable String id, @RequestBody Endpoint endpoint) {
        // Llama al servicio para actualizar un endpoint por su ID
        Endpoint endpointActualizado = endpointService.actualizarEndpoint(id, endpoint);
        return ResponseEntity.ok(endpointActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEndpoint(@PathVariable String id) {
        // Llama al servicio para eliminar un endpoint por su ID
        endpointService.eliminarEndpoint(id);
        return ResponseEntity.noContent().build();
    }
}




