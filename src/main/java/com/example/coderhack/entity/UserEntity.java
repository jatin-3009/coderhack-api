package com.example.coderhack.entity;

import java.util.Set;

import com.example.coderhack.dto.Badge;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    @NotNull
    private String username;
    @NotNull
    private Integer score;
    @NotNull
    private Set<Badge> badges;
}
