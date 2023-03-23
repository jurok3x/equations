package com.yukotsiuba.equation.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.yukotsiuba.equation.configuration.TestDBConfig;
import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.entity.Equation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = { TestDBConfig.class, EquationDaoImpl.class })
class EquationDaoImplTest {

    @Autowired
    IEquationDao equationDao;

    @Test
    void  whenEquationIdProvided_thenReturnCorrectEquation() {
        Optional<Equation> equation = equationDao.findById(1);
        assertEquals(prepareEquation(), equation.get());
    }
    
    @Test
    void  whenEquationIdWrong_thenReturnNull() {
        assertEquals(Optional.empty(), equationDao.findById(11));
    }
    
    @Test
    void  whenSaveEquation_thenEquationExists() {
        Equation newEquation = prepareEquation();
        //assume that same equations can exist in db with different id
        newEquation.setId(5);
        assertEquals(newEquation, equationDao.save(newEquation));
    }
    
    private static Equation prepareEquation() {
        return Equation.builder()
                .id(1)
                .equation("-8.7*4/x=4.3")
                .build();
    }

}
