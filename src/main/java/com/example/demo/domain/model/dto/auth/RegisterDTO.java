package com.example.demo.domain.model.dto.auth;

import com.example.demo.domain.model.usuario.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record RegisterDTO( String email, String password, UserRole role) {
}
