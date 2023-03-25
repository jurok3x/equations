package com.yukotsiuba.equation.service;

import com.yukotsiuba.equation.dto.EquationDto;

import java.util.List;

public interface IEquationService {
    
    EquationDto save(EquationDto equationDto);
    List<EquationDto> findByRootValues(List<Double> values);
    List<EquationDto> findByRootsCount(Integer count);
    EquationDto addRoots(Integer equationId, List<Double> values);

}
