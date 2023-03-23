package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import java.util.List;

public interface IRootDao {

    Root save(Root root);
    Root findByValue(Double value);
    List<Root> findByEquation(Equation equation) ;
}
