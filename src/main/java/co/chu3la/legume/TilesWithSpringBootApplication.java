package co.chu3la.legume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import co.chu3la.legume.entities.User;
import co.chu3la.legume.entities.UserInfo;

@SpringBootApplication
public class TilesWithSpringBootApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TilesWithSpringBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TilesWithSpringBootApplication.class, args);
		
	}

}
