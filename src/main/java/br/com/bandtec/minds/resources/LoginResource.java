package br.com.bandtec.minds.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bandtec.minds.domain.Psicologo;



@RestController
public class LoginResource {

//	@PostMapping("/login")
//	public ResponseEntity<String> validateLogin (@RequestBody Psicologo psicologo){
//		
//		if(psicologo.getEmail().equals(psicologo.getPassword())) {
//			return ResponseEntity.ok("Success");
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error 401 Not Autorized");
//	}
}
