package com.yukotsiuba.equation.service.impl;

import com.yukotsiuba.equation.dto.EquationDto;
import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Root;
import com.yukotsiuba.equation.exception.BadRootException;
import com.yukotsiuba.equation.mapper.EquationMapper;
import com.yukotsiuba.equation.mapper.RootMapper;
import com.yukotsiuba.equation.repository.IRootRepository;
import com.yukotsiuba.equation.service.IRootService;
import com.yukotsiuba.equation.utils.EquationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultRootService implements IRootService {

    private IRootRepository rootRepository;
    @Override
    public RootDto add(EquationDto equationDto, RootDto rootDto) {
        if(!EquationUtils.validateRoot(equationDto.getEqString(), rootDto.getValue())){
            throw new BadRootException(String.format("Value %.2f is not root of the equation %s.", rootDto.getValue(), equationDto.getEqString()));
        }
        Root newRoot = rootRepository.add(EquationMapper.toEntity(equationDto), RootMapper.toEntity(rootDto));
        return RootMapper.toDto(newRoot);
    }

    @Override
    public List<RootDto> addAll(EquationDto equationDto, List<RootDto> rootDtos) {
        List<RootDto> newDtoList = new ArrayList<>();
        rootDtos.forEach(rootDto -> {
            RootDto newRoot = add(equationDto, rootDto);
            newDtoList.add(newRoot);
        });
        return newDtoList;
    }
}
