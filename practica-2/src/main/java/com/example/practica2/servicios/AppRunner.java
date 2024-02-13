package com.example.practica2.servicios;

import com.example.practica2.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.practica2.repo.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AppRunner implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppRunner(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Verificar si ya existe un usuario administrador (por ejemplo, con nombre "admin")
        if (!usuarioRepository.findByUsuario("admin").isPresent()) {
            // Si no existe, crea un usuario administrador por defecto
            Usuario admin = new Usuario(1L, "Admin", "admin","admin","admin", true);
            // Hashear la contraseña antes de almacenarla
            String contrasenaHasheada = passwordEncoder.encode(admin.getContrasena());
            admin.setContrasena(contrasenaHasheada);
            // Guardar el usuario administrador
            usuarioRepository.save(admin);
        }
    }
}
