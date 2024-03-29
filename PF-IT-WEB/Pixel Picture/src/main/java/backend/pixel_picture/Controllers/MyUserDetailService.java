package backend.pixel_picture.Controllers;

import backend.pixel_picture.Entidades.Usuario;
import backend.pixel_picture.Repositorios.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private Logger logger =  LoggerFactory.getLogger(UserController.class);

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario findByUsuario(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        String role = user.getRol();
        logger.info("Role: "+role);
        roles.add(new SimpleGrantedAuthority(role));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
