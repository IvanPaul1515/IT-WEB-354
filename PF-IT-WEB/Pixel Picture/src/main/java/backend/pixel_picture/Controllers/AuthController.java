package backend.pixel_picture.Controllers;

import backend.pixel_picture.Entidades.AuthResponse;
import backend.pixel_picture.Entidades.LoginDTO;
import backend.pixel_picture.Entidades.Usuario;
import backend.pixel_picture.Repositorios.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/")
    public ResponseEntity<AuthResponse> auth(@RequestBody LoginDTO user){

        System.out.println("\n USUARIO"+ user.getUserName() +"\n");


        Usuario usuario = userRepository.findByUserName(user.getUserName());


        if(usuario==null || !usuario.getPassword().equals(user.getPassword())){
            System.out.println("\n "+usuario.getPassword() + "======" +user.getPassword() +"\n");
            System.out.println("\nNO AUTORIZADO\n");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Usuario aux = userRepository.findByUserName(user.getUserName());
        AuthResponse token = jwtService.getToken(aux);
        System.out.println("\n\n INICIO DE SEISON \n\n");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
