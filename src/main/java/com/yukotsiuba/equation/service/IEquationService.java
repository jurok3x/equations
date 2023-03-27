package com.yukotsiuba.equation.service;

import com.yukotsiuba.equation.dto.EquationDto;

import java.util.List;

public interface IEquationService {
    
    EquationDto save(EquationDto equationDto);
    List<EquationDto> findByRootValues(List<String> values);
    List<EquationDto> findByRootsCount(Integer count);
    EquationDto addRoots(Integer equationId, List<String> values);

}
