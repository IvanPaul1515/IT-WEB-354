// Clase principal de la aplicación
package org.example.sender01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClienteSensorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteSensorApplication.class, args);
    }
}
