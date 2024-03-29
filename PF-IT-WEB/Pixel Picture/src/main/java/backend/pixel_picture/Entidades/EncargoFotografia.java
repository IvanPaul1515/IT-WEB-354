package backend.pixel_picture.Entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class EncargoFotografia implements Serializable {
    @Id
    private long id;
    private String servicio;
    private String encargado;
    private String fecha;
    private String estado;

    public EncargoFotografia(int id, String servicio, String encargado, String fecha, String estado) {
        this.id = id;
        this.servicio = servicio;
        this.encargado = encargado;
        this.fecha = fecha;
        this.estado = estado;
    }

    public EncargoFotografia() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
