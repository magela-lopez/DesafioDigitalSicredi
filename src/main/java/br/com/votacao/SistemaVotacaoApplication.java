package br.com.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
public class SistemaVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaVotacaoApplication.class, args);
    }

}
