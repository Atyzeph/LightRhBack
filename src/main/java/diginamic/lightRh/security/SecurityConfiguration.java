package diginamic.lightRh.security;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import diginamic.lightRh.enums.Profile;
import diginamic.lightRh.enums.Role;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

   private final JwtAuthenticationFilter jwtAuthenticationFilter;
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http
	.csrf(csrf -> csrf.disable())
	.cors(cors -> cors.configure(http))
	.sessionManagement(sess -> 
		sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	.cors(cors -> cors.configure(http))
	.authorizeHttpRequests(
		auth -> auth
		.requestMatchers(
				"/user/login/**", 
				"/user/changepassword/**", 
				"/user/setnewpassword/**", 
				"/user/createAbsence/**",
				"/absences/**",
				"/swagger-ui/**", 
				"/v3/api-docs/**"
				).permitAll()
		.requestMatchers("/employee/**").hasAnyAuthority(Profile.MANAGER.name(), Profile.EMPLOYEE.name())
		.requestMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
		.requestMatchers("/management/**").hasAuthority(Profile.MANAGER.name())
		.anyRequest().authenticated()		
	)
	.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    	configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE","OPTIONS"));
   	  	configuration.setAllowedHeaders(Arrays.asList("content-type", "Authorization"));
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }   
    
    @Bean
    public OpenAPI openAPI() {
	return new OpenAPI()
		.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
	        .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
	        .info(new Info().title("ligthRh API"));
    }
    
    private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
		        .bearerFormat("JWT")
		        .scheme("bearer");
    }
    
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername("diginiamiclightrh@gmail.com");
        mailSender.setPassword("olgztkfpjnccdsif");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        return mailSender;
    }
}