package com.yukotsiuba.equation.mapper;

import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Root;

public class RootMapper {

    public static RootDto toDto(Root root) {
        return RootDto.builder()
                .id(root.getId())
                .value(root.getValue())
                .build();
    }

    public static Root toEntity(RootDto rootDto) {
        return Root.builder()
                .id(rootDto.getId())
                .value(rootDto.getValue())
                .build();
    }
}
