package com.yukotsiuba.equation.service;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public interface IRootService {

    Root add(Equation equation, Root root);
    List<Root> addAll(Equation equation, List<Root> root);
}
