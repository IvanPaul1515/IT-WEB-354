package backend.pixel_picture.servicios;

import backend.pixel_picture.Entidades.Usuario;

public interface UsuarioService {
    Usuario encontrarPorRol(String rol);
}
