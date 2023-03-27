package com.yukotsiuba.equation.dao.impl;

import com.yukotsiuba.equation.configuration.TestDBConfig;
import com.yukotsiuba.equation.dao.IEquationRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { TestDBConfig.class, EquationRootDaoImpl.class })
class EquationRootDaoImplTest {
    
    @Autowired
    private IEquationRootDao equationRootDao;

    @Test
    void saveRuns() {
        equationRootDao.save(prepareEquation(), prepareRoot());;
    }
    
    private static Equation prepareEquation() {
        return Equation.builder()
                .id(4)
                .eqString("x-6=0")
                .build();
    }
    
    private Root prepareRoot() {
        return Root.builder()
                .id(4)
                .value("6.0")
                .build();
    }

}
