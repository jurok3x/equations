package com.yukotsiuba.equation.mapper;

import com.yukotsiuba.equation.dto.RootDto;
import com.yukotsiuba.equation.entity.Root;

public class RootMapper {

    public static RootDto toDto(Root root) {
        return RootDto.builder()
                .id(root.getId())
                .root(root.getRoot())
                .build();
    }

    public static Root toEntity(RootDto rootDto) {
        return Root.builder()
                .id(rootDto.getId())
                .root(rootDto.getRoot())
                .build();
    }
}
