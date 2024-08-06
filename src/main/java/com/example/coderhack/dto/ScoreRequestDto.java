package com.example.coderhack.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScoreRequestDto {
    @NotNull
    @Min(1)
    @Max(100)
    private Integer score;
}
