package example.cashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The application entry point
 *
 * @author Felipe Gutierrez
 * @author Josh Cummings
 */
@SpringBootApplication
public class CashCardApplication {

	@Bean
	SecurityFilterChain appSecurity(HttpSecurity http, AuthenticationEntryPoint entryPoint)
			throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			)
			.oauth2ResourceServer((oauth2) -> oauth2
				.authenticationEntryPoint(entryPoint)
				.jwt(Customizer.withDefaults())
			);
		return http.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(CashCardApplication.class, args);
	}

}
