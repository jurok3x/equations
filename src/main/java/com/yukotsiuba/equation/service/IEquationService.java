package com.yukotsiuba.equation.service;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.dto.RootDto;

import java.util.List;

public interface IEquationService {
    
    EquationDto save(EquationDto equationDto);
    List<EquationDto> findByRoots(List<RootDto> rootDtos);
    List<EquationDto> findByRootsCount(Integer count);

}
