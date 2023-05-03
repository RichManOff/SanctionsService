package com.example.sanction_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
//@EnableJpaRepositories("com.example.sanction_service.repository")
public class SanctionServiceApplication{
//    @Autowired implements CommandLineRunner
//    private SanctionJob sanctionXmlToBD;
    public static void main(String[] args){
        SpringApplication.run(SanctionServiceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        sanctionXmlToBD.saxmethod();
//    }
}
