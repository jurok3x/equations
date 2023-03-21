package com.yukotsiuba.equation.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.service.IEquationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DefaultEquationServiceTest {
    
    private IEquationService equationService;

    @BeforeEach
    void setUp() throws Exception {
        equationService = new DefaultEquationService();
    }

    @Test
    void test_correctEquations() {
        List<Equation> correctEquations = prepareCorrectEquations();
        correctEquations.forEach(equation -> assertNotNull((equationService.save(equation))));
    }
    
    @Test
    void test_incorrectEquations() {
        List<Equation> incorrectEquations = prepareIncorrectEquations();
        incorrectEquations.forEach(equation -> assertNull(equationService.save(equation)));
    }
    
    private static List<Equation> prepareCorrectEquations() {
        return Arrays.asList(
                Equation.builder().equation("x + 3 = 5").build(),
                Equation.builder().equation("x + - 3 = 5").build(),
                Equation.builder().equation("x - - 3 = 5").build(),
                Equation.builder().equation("x * - 3 = 5").build(),
                Equation.builder().equation("x / - 3 = 5").build(),
                Equation.builder().equation("x + (3 + 7) = 5").build(),
                Equation.builder().equation("((x * -2) + 3) = 5").build());
    }
    
    private static List<Equation> prepareIncorrectEquations() {
        return Arrays.asList(
             //  Equation.builder().equation("+ 3 = 5").build(),
             //   Equation.builder().equation("y + 3 = 5").build(),
             //   Equation.builder().equation("x + 3  5").build(),
             //   Equation.builder().equation("x - + 3 = 5").build(),
             //   Equation.builder().equation("x - - - 3 = 5").build(),
             //   Equation.builder().equation("x + * 3 = 5").build(),
                Equation.builder().equation("x * / 3 = 5").build(),
                Equation.builder().equation("x + (3 + 7 = 5").build(),
                Equation.builder().equation("(x * -2) + 3) = 5").build());
    }

}
