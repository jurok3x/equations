package com.yukotsiuba.equation.repository;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public interface IRootRepository {

    Root add(Equation equation, Root root);
    List<Root> addAll(Equation equation, List<Root> roots);

}
