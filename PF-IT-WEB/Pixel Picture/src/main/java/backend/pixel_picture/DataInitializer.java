package backend.pixel_picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import backend.pixel_picture.Entidades.Usuario;
import backend.pixel_picture.Repositorios.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si el usuario administrador ya existe
        if (!userRepository.existsById((long) 0)) {
            // Crear el usuario administrador si no existe
            Usuario adminUser = new Usuario(0, "admin", "admin", "admin123", null, "ADMIN");
//            adminUser.setId(1);
//            adminUser.setNombre("admin");
//            adminUser.setPassword("admin123"); // Cambia esto por la contraseña que desees
//            adminUser.setRol("ADMIN");
            userRepository.save(adminUser);
            System.out.println("Usuario administrador creado con éxito.");
        } else {
            System.out.println("El usuario administrador ya existe.");
        }
    }
}
