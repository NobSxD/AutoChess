package com.example.autochess.DTO.hero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParameterDto {
    @JsonIgnore
    private Long id;

    private String name_parameters;
    private Double count;
}
