package com.crusaders.jobKey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JobKeyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JobKeyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Aplicación JobKey iniciada correctamente, Abriendo puertas..");

    }

}
