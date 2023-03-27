package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;

import java.util.List;
import java.util.Optional;

public interface IEquationDao {

    Equation save(Equation equation);
    Optional<Equation> findById(Integer id);
    Optional<Equation> findByEquationString(String eqString);
    List<Equation> findByRootValues(List<String> roots);
    List<Equation> findByRootCount(Integer count);
}
