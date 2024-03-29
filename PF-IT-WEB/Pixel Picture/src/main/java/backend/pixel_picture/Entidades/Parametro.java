package backend.pixel_picture.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parametro implements Serializable {
    @Id
    private Integer codigo;
    private String nombre;
    private String valor;
}
