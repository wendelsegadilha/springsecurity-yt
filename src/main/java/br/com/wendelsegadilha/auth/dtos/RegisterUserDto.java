package br.com.wendelsegadilha.auth.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterUserDto {
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String fullName;
}
