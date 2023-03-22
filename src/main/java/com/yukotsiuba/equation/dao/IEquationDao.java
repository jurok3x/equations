package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;

import java.util.Optional;

public interface IEquationDao {

    Equation save(Equation equation);
    Optional<Equation> findById(Integer id);
}
