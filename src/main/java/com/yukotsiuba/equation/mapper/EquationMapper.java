package com.yukotsiuba.equation.mapper;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.entity.Equation;

public class EquationMapper {

    public static EquationDto toDto(Equation equation) {
        return EquationDto.builder()
                .id(equation.getId())
                .equation(equation.getEquation())
                .build();
    }

    public static Equation toEntity(EquationDto equationDto) {
        return Equation.builder()
                .id(equationDto.getId())
                .equation(equationDto.getEquation())
                .build();
    }
}
