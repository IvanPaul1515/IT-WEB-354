package com.example.practica2.entidades;

import jakarta.persistence.*;

@Entity
public class Endpoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ruta;
    private String metodo;
    private String headers;
    private int codigoRespuesta;
    private String contentType;
    private static String cuerpo;
    private String nombre;
    private String codigolenguaje;
    private String descripcion;
    private String tiempoExpiracion;
    private static int demoraRespuesta;
    private boolean validarJwt;
    private String fechaCreacion;


    public Endpoint() {

    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public static String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempoExpiracion() {
        return tiempoExpiracion;
    }

    public void setTiempoExpiracion(String tiempoExpiracion) {
        this.tiempoExpiracion = tiempoExpiracion;
    }

    public static int getDemoraRespuesta() {
        return demoraRespuesta;
    }

    public void setDemoraRespuesta(int demoraRespuesta) {
        this.demoraRespuesta = demoraRespuesta;
    }

    public boolean isValidarJwt() {
        return validarJwt;
    }

    public void setValidarJwt(boolean validarJwt) {
        this.validarJwt = validarJwt;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigolenguaje() {
        return codigolenguaje;
    }

    public void setCodigolenguaje(String codigolenguaje) {
        this.codigolenguaje = codigolenguaje;
    }

    public String getStatus() {
        return null;
    }
}
