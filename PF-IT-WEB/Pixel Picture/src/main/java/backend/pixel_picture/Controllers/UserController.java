package backend.pixel_picture.Controllers;

import java.util.List;
import java.util.Optional;

import backend.pixel_picture.Entidades.LoginDTO;
import backend.pixel_picture.Entidades.Usuario;
import backend.pixel_picture.Repositorios.UserRepository;
import backend.pixel_picture.servicios.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private EmailService emailService;

    // Crear un Usuario
    @PostMapping("/new")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        System.out.println("NEW USER" + usuario.getId());
        System.out.println("NEW USER" + usuario.getNombre());
        System.out.println("NEW USER" + usuario.getCorreo());
//        emailService.enviarCorreoBienvenida(usuario);
        return userRepository.save(usuario);
    }

    // Leer todos los estudiantes
    @GetMapping("/")
    public List<Usuario> obtenerUsuarios() {
        System.out.println("ENTRE LISTAR TODOS");
        return (List<Usuario>) userRepository.findAll();
    }

    // Leer un estudiante por matrícula
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/hola/{usuario}")
    public Usuario saludo(@PathVariable String usuario) {
        System.out.println("\n\n HOLA " + usuario +"\n\n");

        return  userRepository.findByUserName(usuario);

    }

    // Actualizar un estudiante
    @PutMapping("/{code}")
    public Usuario actualizarEstudiante(@PathVariable int code, @RequestBody Usuario usuarioActualizado) {
        usuarioActualizado.setId(code);
        return userRepository.save(usuarioActualizado);
    }

    // Eliminar un estudiante
    @DeleteMapping("/{code}")
    public void eliminarEstudiante(@PathVariable long code) {
        userRepository.deleteById(code);
    }

}