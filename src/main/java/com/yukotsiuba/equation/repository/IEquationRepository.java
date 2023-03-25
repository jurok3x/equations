package com.yukotsiuba.equation.repository;

import com.yukotsiuba.equation.entity.Equation;

import java.util.List;
import java.util.Optional;

public interface IEquationRepository {

    Equation save(Equation equation);
    Optional<Equation> findById(Integer id);
    List<Equation> findByRootValues(List<Double> values);
    List<Equation> findByRootsCount(Integer count);

}
