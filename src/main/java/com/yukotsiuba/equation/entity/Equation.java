package com.yukotsiuba.equation.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Equation {

    private Integer id;
    private String equation;

}
