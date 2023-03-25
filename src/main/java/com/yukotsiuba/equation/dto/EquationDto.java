package com.yukotsiuba.equation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquationDto {

    private Integer id;
    private String eqString;
    private List<RootDto> roots;
}
