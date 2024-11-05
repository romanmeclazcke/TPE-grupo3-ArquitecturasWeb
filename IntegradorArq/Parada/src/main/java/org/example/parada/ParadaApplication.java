package org.example.parada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class ParadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParadaApplication.class, args);
	}

}
