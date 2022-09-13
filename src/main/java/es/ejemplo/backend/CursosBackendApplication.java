package es.ejemplo.backend;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class CursosBackendApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(CursosBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info ("Iniciando aplicación ...");


    }


}
