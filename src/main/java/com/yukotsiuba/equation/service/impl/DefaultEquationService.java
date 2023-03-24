package com.yukotsiuba.equation.service.impl;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.exception.IncorrectEquationException;
import com.yukotsiuba.equation.mapper.EquationMapper;
import com.yukotsiuba.equation.mapper.RootMapper;
import com.yukotsiuba.equation.repository.IEquationRepository;
import com.yukotsiuba.equation.service.IEquationService;

import com.yukotsiuba.equation.utils.EquationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
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
    public List<EquationDto> findByRoots(List<RootDto> rootDtos) {
        if(rootDtos == null) {
            throw new NullPointerException("Root list can not be null.");
        }
        List<Root> roots = rootDtos.stream()
                .map(RootMapper::toEntity)
                .collect(Collectors.toList());
        List<Equation> equations = equationRepository.findByRoots(roots);
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

}
