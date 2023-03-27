package com.yukotsiuba.equation.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EquationUtilsTest {

    @Test
    void testRootValidation() {
        assertTrue(EquationUtils.validateRoot("x-2=2", 4.0));
        assertTrue(EquationUtils.validateRoot("x-2/4=2", 2.5));
        assertTrue(EquationUtils.validateRoot("x*x=4", -2.0));
        assertTrue(EquationUtils.validateRoot("2*x+6=174", 84.0));
        assertTrue(EquationUtils.validateRoot("2*x+(6+2)=174", 83.0));
        assertTrue(EquationUtils.validateRoot("x-1=1/3", 1.33));
    }
    
    @Test
    void testEquationValidation() {
        assertTrue(EquationUtils.validateEquation("x + 3 = 5"));
        assertTrue(EquationUtils.validateEquation("x + - 3 = 5"));
        assertTrue(EquationUtils.validateEquation("x - - 3 = 5"));
        assertTrue(EquationUtils.validateEquation("x * - 3 = 5"));
        assertTrue(EquationUtils.validateEquation("x / - 3 = 5"));
        assertTrue(EquationUtils.validateEquation("x + (3 + 7) = 5"));
        assertTrue(EquationUtils.validateEquation("((x * -2) + 3) = 5"));
    }
    
    @Test
    void testIncorrectEquationValidation() {
        assertFalse(EquationUtils.validateEquation("+ 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x5"));
        assertFalse(EquationUtils.validateEquation("x+ 3 = 5 = 8"));
        assertFalse(EquationUtils.validateEquation("y + 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x + 3 =  5.3.3"));
        assertFalse(EquationUtils.validateEquation("x - + 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x - - - 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x + * 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x * / 3 = 5"));
        assertFalse(EquationUtils.validateEquation("x + (3 + 7 = 5"));
        assertFalse(EquationUtils.validateEquation("(x * -2) + 3) = 5"));
    }



}
