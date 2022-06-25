package server;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration

@SpringBootApplication
@Configuration
//startet die App und konfiguriert OpenAPI sonst nichts.
public class ServerApp {

	public static void main(String[] args) {
		SpringApplication.run(ServerApp.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("MyHolidayFamilyPlanner API").version(appVersion)
				);
	}
}
