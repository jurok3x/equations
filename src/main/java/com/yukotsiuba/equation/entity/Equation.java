package com.yukotsiuba.equation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equation {

    private Integer id;
    private String eqString;
    private List<Root> roots;

}
