package br.com.wendelsegadilha.auth.controllers;

import br.com.wendelsegadilha.auth.dtos.LoginResponseDto;
import br.com.wendelsegadilha.auth.dtos.LoginUserDto;
import br.com.wendelsegadilha.auth.dtos.RegisterUserDto;
import br.com.wendelsegadilha.auth.entities.User;
import br.com.wendelsegadilha.auth.security.JwtService;
import br.com.wendelsegadilha.auth.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AutheticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        RegisterUserDto userDto = RegisterUserDto.builder()
                .email(registeredUser.getEmail())
                .fullName(registerUserDto.getFullName())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .userId(authenticatedUser.getId())
                .username(authenticatedUser.getEmail())
                .token(jwtToken)
                .expireIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

}
