package backend.pixel_picture.Controllers;

import backend.pixel_picture.Repositorios.ParametroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/")
public class PagoController {

    private ParametroRepository parametroRepository;
//    private CompraRepository compraRepository;
    private Logger logger = Logger.getLogger(PagoController.class.getName());

    private static final String CUENTA_NEGOCIO = "sb-kpzwa28971958@business.example.com";


    public PagoController(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
//        this.compraRepository = compraRepository;
    }

    // FALTAN LOS DETALLES DIAGASE(MONTO,DIRECCION, ETC...)
}
