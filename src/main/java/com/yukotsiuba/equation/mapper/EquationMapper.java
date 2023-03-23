package com.yukotsiuba.equation.mapper;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.entity.Equation;

public class EquationMapper {

    public static EquationDto toDto(Equation equation) {
        return EquationDto.builder()
                .id(equation.getId())
                .eqString(equation.getEqString())
                .build();
    }

    public static Equation toEntity(EquationDto equationDto) {
        return Equation.builder()
                .id(equationDto.getId())
                .eqString(equationDto.getEqString())
                .build();
    }
}
