package br.com.wendelsegadilha.auth.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private Integer userId;
    private String username;
    private String token;
    private long expireIn;
}
