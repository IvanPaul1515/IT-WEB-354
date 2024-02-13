//package com.example.practica2.controlador;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import com.example.practica2.entidades.Endpoint;
//import com.example.practica2.servicios.EndpointService;
//
//
//@RestController
//@RequestMapping("/api")
//public class ApiMockController {
//
//    @Autowired
//    EndpointService endpointService;
//
//    @GetMapping("/{name}/{codigo}")
//    public ResponseEntity<String> consulta(@PathVariable String name, @PathVariable String codigo) {
//        Endpoint endpoint = endpointService.buscarPorCodigoYNombre(name, codigo);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        String fechaActualFormateada = LocalDateTime.now().format(formatter);
//        String fechaExpiracionFormateada = String.format(String.valueOf(formatter));
//
//        System.out.println(fechaActualFormateada + " otra: " + fechaExpiracionFormateada);
//
//
//        LocalDateTime fechaActual = LocalDateTime.parse(fechaActualFormateada, formatter);
//        LocalDateTime fechaExpiracion = LocalDateTime.parse(fechaExpiracionFormateada, formatter);
//
//        if (fechaActual.isBefore(fechaExpiracion)) {
//            MediaType contentType = null;
//
//            if (endpoint.getContentType().equals("text/plain")) {
//                contentType = MediaType.TEXT_PLAIN;
//            } else if (endpoint.getContentType().equals("text/html")) {
//                contentType = MediaType.TEXT_HTML;
//            } else if (endpoint.getContentType().equals("application/json")) {
//                contentType = MediaType.APPLICATION_JSON;
//            } else if (endpoint.getContentType().equals("application/xml")) {
//                contentType = MediaType.APPLICATION_XML;
//            }
//
//            HttpHeaders headers = new HttpHeaders();
//            if (!endpoint.getHeaders().isEmpty()) {
//                JSONObject headersJson = new JSONObject(endpoint.getHeaders());
//                for (String key : headersJson.keySet()) {
//                    headers.add(key, headersJson.getString(key));
//                }
//            }
//
//            try {
//                Thread.sleep(Endpoint.getDemoraRespuesta() * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(new JSONObject(Endpoint.getCuerpo()));
//            return ResponseEntity
//                    .status(Integer.parseInt(endpoint.getStatus()))
//                    .contentType(contentType)
//                    .headers(headers)
//                    .body(Endpoint.getCuerpo());
//        } else {
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Location", "/");
//            return new ResponseEntity<>(headers, HttpStatus.FOUND);
//        }
//    }
//}
