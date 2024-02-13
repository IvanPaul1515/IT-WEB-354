package com.example.practica2.servicios;

import com.example.practica2.entidades.Usuario;
import com.example.practica2.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
            this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarUsuario(Usuario usuario) {
        // Verificar si el usuario ya existe por su nombre de usuario
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }
        // Hashear la contraseña antes de almacenarla
        String contrasenaHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaHasheada);

        // Puedes realizar otras configuraciones o validaciones antes de guardar el usuario
        usuarioRepository.save(usuario);
    }

    public void modificarUsuario(Usuario usuario) {
        // Verificar si el usuario existe por su ID
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        // Hashear la nueva contraseña antes de almacenarla
        String contrasenaHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaHasheada);

        // Puedes realizar otras configuraciones o validaciones antes de guardar los cambios
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        // Verificar si el usuario existe por su ID
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        // Si existe, eliminar al usuario por su ID
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElse(null); 
    }

    public void actualizarUsuario(Long id, Usuario usuario) {
        // Verificar si el usuario existe por su ID
        Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findById(id);
        if (usuarioExistenteOptional.isPresent()) {
            // Actualizar los campos del usuario existente
            Usuario usuarioExistente = usuarioExistenteOptional.get();
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setContrasena(usuario.getContrasena());

            // Guardar los cambios en el usuario existente
            usuarioRepository.save(usuarioExistente);
        } else {
            throw new IllegalArgumentException("El usuario no existe.");
        }
    }

    public boolean validarCredenciales(String usuario, String contrasena) {
        Usuario usuarioEncontrado = usuarioRepository.findByUsuario(usuario)
                .orElse(null);

        if (usuarioEncontrado != null && passwordEncoder.matches(contrasena, usuarioEncontrado.getContrasena())) {
            return true;
        }

        return false;
    }
}

