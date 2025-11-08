package com.example.demo.domain.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record AuthenticationDTO(String email, String password) {

}
