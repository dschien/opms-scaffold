package ac.uk.bris.spe.opms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.home}/.opms.properties")
public class OpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpmsApplication.class, args);
    }
}
