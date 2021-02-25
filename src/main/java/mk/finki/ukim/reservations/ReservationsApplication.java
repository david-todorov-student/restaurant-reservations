package mk.finki.ukim.reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
=======
>>>>>>> c60bb3d18004ba3d6bc5dffb09ad4866cb6999dd
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ServletComponentScan
public class ReservationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationsApplication.class, args);
    }

    @Bean
<<<<<<< HEAD
    public PasswordEncoder passwordEncoder(){
=======
    PasswordEncoder passwordEncoder(){
>>>>>>> c60bb3d18004ba3d6bc5dffb09ad4866cb6999dd
        return new BCryptPasswordEncoder(10);
    }
}
