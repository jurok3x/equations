package com.yukotsiuba.equation.service.impl;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.service.IEquationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class DefaultEquationService implements IEquationService {

    static final Pattern DIGIT_OR_X = Pattern.compile("[\\dx]");
    static final Pattern OPERATION = Pattern.compile("\\([\\dx][+*/-][\\dx]\\)");

    @Override
    public Equation save(Equation equation) {
        if(validateEquation(equation.getEqString())) {
            return equation;
        }
        return null;
    }
    
    private boolean validateParentheses(String equation) {
        log.debug("Checking parentheses");
        int count = 0;
        for(int i = 0; i < equation.length(); i++) {
          if(equation.charAt(i) == '(') {
              count++;
          }
            else if(equation.charAt(i) == ')') {
                count--;
            }
          if(count < 0) {
              return false;
          }
        }
        if(count == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateEquation(String equation) {
        String trimmedEq = equation.replaceAll("\\s+", "").toLowerCase();
        if(!containsOnlyOneEqual(trimmedEq)){
            return false;
        }
        String[] formulas = trimmedEq.split("=");
        for(String formula:formulas){
            if(!isFormula(formula)){
                return false;
            }
        }
        return validateParentheses(trimmedEq);
    }

    private boolean  containsOnlyOneEqual(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '=') {
                count++;
            }
        }
        return count == 1;
    }

    private boolean isFormula(String s) {
        while (true) {
            if (DIGIT_OR_X.matcher(s).matches())
                return true;
            String rep = OPERATION.matcher(s).replaceAll("x");
            if (rep.equals(s))
                return false;
            s = rep;
        }

    }
    }
