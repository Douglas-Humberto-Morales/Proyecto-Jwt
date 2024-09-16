package org.douglasalvarado.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

// Es una versión especializada del controlador que implementa Controller y ResponseBody
@RestController
// Asigna una solicitud HTTP a un método
@RequestMapping("/auth")
// Genera constructores para todos los campos finales y no nulos
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; 

    //Valor del mapeo de tipo post
    @PostMapping(value = "login")
    // ResponseEntity = respresenta toda la respuesta Http (Body Header etc)
    // RequestBody = Parametros en el cuerpo de la solicitud
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        //Estaturs correcti en la respuesta de la entidad
        return ResponseEntity.ok(authService.login(request));
    }
    
    //Valor del mapeo de tipo post
    @PostMapping(value = "register")
    // ResponseEntity = respresenta toda la respuesta Http (Body Header etc)
    // RequestBody = Parametros en el cuerpo de la solicitud
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        //Estaturs correcti en la respuesta de la entidad
        return ResponseEntity.ok(authService.register(request));
    }
    
}