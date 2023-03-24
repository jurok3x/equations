package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;
import java.util.Optional;

public interface IRootDao {

    Root save(Root root);
    Optional<Root> findByValue(Double value);
    List<Root> findByEquation(Equation equation) ;
}
