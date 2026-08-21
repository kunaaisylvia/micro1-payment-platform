package com.tinah.payment.controller;

import com.tinah.payment.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtService jwtService;
    public AuthController(JwtService jwtService) { this.jwtService = jwtService; }
    public record TokenRequest(@NotBlank String username) {}
    public record TokenResponse(String accessToken) {}
    @PostMapping("/token") public TokenResponse token(@RequestBody TokenRequest request) {
        return new TokenResponse(jwtService.issue(request.username()));
    }
}
