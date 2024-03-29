package backend.pixel_picture.Entidades;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    @Id
    private long id;
    private String nombre;
    private String userName;
    private String password;
    private String correo;

    private String rol;

    public Usuario(long id, String nombre, String userName, String password, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
        this.correo = correo;
        this.rol = rol;
    }

    public Usuario() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
