package com.yukotsiuba.equation.service;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public interface IRootService {

    RootDto add(EquationDto equationDto, RootDto rootDto);
    List<RootDto> addAll(EquationDto equation, List<RootDto> rootDtos);
}
