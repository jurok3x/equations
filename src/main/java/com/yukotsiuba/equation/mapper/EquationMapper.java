package com.yukotsiuba.equation.mapper;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public class EquationMapper {

    public static EquationDto toDto(Equation equation) {
        EquationDto equationDto = EquationDto.builder()
                .id(equation.getId())
                .eqString(equation.getEqString())
                .build();
        if(equation.getRoots() != null) {
            List<RootDto> rootDtos = equation.getRoots().stream()
                    .map(RootMapper::toDto)
                    .toList();
            equationDto.setRoots(rootDtos);
        }
        return equationDto;
    }

    public static Equation toEntity(EquationDto equationDto) {
        Equation equation = Equation.builder()
                .id(equationDto.getId())
                .eqString(equationDto.getEqString())
                .build();
        if(equationDto.getRoots() != null) {
            List<Root> roots = equationDto.getRoots().stream()
                    .map(RootMapper::toEntity)
                    .toList();
            equation.setRoots(roots);
        }
        return equation;
    }
}
