package com.yukotsiuba.equation.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.yukotsiuba.equation.configuration.TestDBConfig;
import com.yukotsiuba.equation.dao.IRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = { TestDBConfig.class, RootDaoImpl.class })
class RootDaoImplTest {
    
    @Autowired
    private IRootDao rootDao;
    
    @Test
    void whenSave_thenReturnCorrectRoot() {
        Root root = Root.builder().value("12.0").build();
        root.setId(4);
        assertEquals(root, rootDao.save(root));
    }

    @Test
    void whenValueProvided_thenReturnCorrectRoot() {
        Root root = prepareRoot();
        assertEquals(root, rootDao.findByValue(root.getValue()).get());
    }
    
    @Test
    void whenIdProvided_thenReturnCorrectRoot() {
        Root root = prepareRoot();
        assertEquals(root, rootDao.findById(root.getId()).get());
    }
    
    @Test
    void whenEquationId_thenReturnCorrectRoots() {
        assertEquals(prepareRootList(), rootDao.findByEquation(prepareEquation()));
    }
    
    private Root prepareRoot() {
        return Root.builder()
                .id(1)
                .value("84.0")
                .build();
    }
    
    private List<Root> prepareRootList() {
        return Arrays.asList(
                Root.builder().id(2).value("-2.0").build(),
                Root.builder().id(3).value("2.0").build());
    }
    
    private static Equation prepareEquation() {
        return Equation.builder()
                .id(3)
                .eqString("x*x=4")
                .build();
    }

}
