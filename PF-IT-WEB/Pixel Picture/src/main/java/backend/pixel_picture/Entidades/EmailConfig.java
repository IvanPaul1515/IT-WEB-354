package backend.pixel_picture.Entidades;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public Mailer mailer() {
        return MailerBuilder
                .withSMTPServer("in-v3.mailjet.com", 587, "3d7d02de4d0e973ea23d510d2564a1e6", "00367340db1a58c0072eac6d8445f9ef")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria()
                .withDebugLogging(true)
                .buildMailer();
    }
}
