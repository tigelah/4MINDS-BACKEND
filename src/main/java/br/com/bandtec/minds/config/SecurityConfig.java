
package br.com.bandtec.minds.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.bandtec.minds.security.JWTAuthenticationFilter;
import br.com.bandtec.minds.security.JWTAuthorizationFilter;
import br.com.bandtec.minds.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // libera a autorizacao de endpoins especificos
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
//	variavel de ambiente 
	@Autowired
    private Environment env;

	// libera o acesso ao banco H2
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-consolee/**",
	};

	// libera o acesso a esses endpoints apenas como get
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/pacientes/**",
			"/consultas/**",
	};
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/pacientes/**",
			"/auth/forgot/**",
			"/consultas/**",
			"/psicologos/**", 

			
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		chamada da variavel de ambiente para dar acesso ao perfil setado em contains
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
//			desabilita a protecao contra csrf
		http.cors().and().csrf().disable();

		http.authorizeRequests()
//		autoriza apenas metodos GET nos endpoints permitidos 
//		permitindo apenas os metodos get , para os acessos
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
//			permitindo todos os metodos para esses acessos
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil)); // adicionando filtro na autenticacao
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService)); // adicionando filtro na autorizacao
//		 garante que o backend nao cria sessao de usuario
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
