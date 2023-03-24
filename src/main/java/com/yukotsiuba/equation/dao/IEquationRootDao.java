package com.yukotsiuba.equation.dao;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

public interface IEquationRootDao {

    void save (Equation equation, Root root);
}
