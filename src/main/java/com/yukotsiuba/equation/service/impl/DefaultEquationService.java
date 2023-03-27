package com.yukotsiuba.equation.service.impl;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.exception.BadRootException;
import com.yukotsiuba.equation.exception.IncorrectEquationException;
import com.yukotsiuba.equation.exception.ResourceNotFoundException;
import com.yukotsiuba.equation.mapper.EquationMapper;
import com.yukotsiuba.equation.repository.IEquationRepository;
import com.yukotsiuba.equation.service.IEquationService;

import com.yukotsiuba.equation.utils.EquationUtils;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultEquationService implements IEquationService {

    private IEquationRepository equationRepository;

    @Override
    public EquationDto save(EquationDto equationDto) {
        if(!EquationUtils.validateEquation(equationDto.getEqString())) {
            throw new IncorrectEquationException("Wrong equation.");
        }
        Equation newEquation = equationRepository.save(EquationMapper.toEntity(equationDto));
        return EquationMapper.toDto(newEquation);
    }

    @Override
    public List<EquationDto> findByRootValues(List<Double> values) {
        if(values == null) {
            throw new NullPointerException("Root list can not be null.");
        }
        List<Equation> equations = equationRepository.findByRootValues(values);
        List<EquationDto> equationDtos = mapEquationList(equations);
        return equationDtos;
    }

    @Override
    public List<EquationDto> findByRootsCount(Integer count) {
        List<Equation> equations = equationRepository.findByRootsCount(count);
        List<EquationDto> equationDtos = mapEquationList(equations);
        return equationDtos;
    }

    private List<EquationDto> mapEquationList(List<Equation> equations) {
        return equations.stream()
                .map(EquationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EquationDto addRoots(Integer equationId, List<Double> values) {
        List<Root> roots = values.stream().map(value -> mapToRoot(value)).toList();
        Equation equation = equationRepository.findById(equationId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the equation"));
        for(Root root:roots) {
            if(!EquationUtils.validateRoot(equation.getEqString(), root.getValue())) {
                throw new BadRootException(String.format("Value %.2f is not root of the expression %s.", root.getValue(), equation.getEqString()));
            }
        }
        equation.getRoots().addAll(roots);
        return EquationMapper.toDto(equationRepository.save(equation));
    }
    
    private Root mapToRoot(Double value) {
        return Root.builder().value(value).build();
    }

}
