package com.yukotsiuba.equation.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.yukotsiuba.equation.configuration.TestDBConfig;
import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.entity.Equation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = { TestDBConfig.class, EquationDaoImpl.class })
class EquationDaoImplTest {

    @Autowired
    IEquationDao equationDao;

    @Test
    void  whenEquationIdProvided_thenReturnCorrectEquation() {
        Equation preparedEq = prepareEquation();
        Optional<Equation> equation = equationDao.findById(preparedEq.getId());
        assertEquals(prepareEquation(), equation.get());
    }
    
    @Test
    void  whenEquationStringProvided_thenReturnCorrectEquation() {
        Equation preparedEq = prepareEquation();
        Optional<Equation> equation = equationDao.findByEquationString(preparedEq.getEqString());
        assertEquals(preparedEq, equation.get());
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
    
    @Test
    void  whenFindEquationsByRootsCount_thenFindEquations() {
        assertEquals(Arrays.asList(prepareEquation()), equationDao.findByRootCount(1));
    }
    
    @Test
    void  whenFindEquationsByRoots_thenFindEquations() {
        assertEquals(prepareEquationList(), equationDao.findByRootValues(prepareRootList()));
    }
    
    private static Equation prepareEquation() {
        return Equation.builder()
                .id(2)
                .eqString("2*x+6=174")
                .build();
    }
    
    private static List<Equation> prepareEquationList() {
        return Arrays.asList(
                Equation.builder().id(2).eqString("2*x+6=174").build(),
                Equation.builder().id(3).eqString("x*x=4").build());
    }
    
    private static List<String> prepareRootList() {
        return Arrays.asList("2.0", "84.0");
    }

}
