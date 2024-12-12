package com.binaries.clutch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.binaries.clutch.config", "com.binaries.clutch.controllers"})
public class App {

    public static void main(String[] args) {

		SpringApplication.run(App.class, args);
    }

}
