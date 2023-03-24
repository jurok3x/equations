package com.yukotsiuba.equation.repository;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public interface IEquationRepository {

    Equation save(Equation equation);
    List<Equation> findByRoots(List<Root> roots);
    List<Equation> findByRootsCount(Integer count);
}
