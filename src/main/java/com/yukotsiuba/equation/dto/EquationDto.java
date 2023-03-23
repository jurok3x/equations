package com.yukotsiuba.equation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquationDto {

    private Integer id;
    private String equation;
}
