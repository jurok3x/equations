package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;
import java.util.Optional;

public interface IEquationDao {

    Equation save(Equation equation);
    Optional<Equation> findById(Integer id);
    Optional<Equation> findByEquationString(String eqString);
    List<Equation> findByRoots(List<Root> roots);
    List<Equation> findByRootCount(Integer count);
}
