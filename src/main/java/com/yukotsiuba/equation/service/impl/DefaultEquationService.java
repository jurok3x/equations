package com.yukotsiuba.equation.service.impl;

import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.service.IEquationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DefaultEquationService implements IEquationService {

    @Override
    public Equation save(Equation equation) {
        if(validateSigns(equation.getEquation()) && validateParentheses(equation.getEquation())) {
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
    
    private boolean validateSigns(String equation) {
        log.debug("Checking signs");
        String trimmedEq = equation.replaceAll("\\s+", "");
        String regex = "([+-/*][+/*])|([+-/*]-{2})|([^x+-/*=\\d\\(\\)])|(=.*?=)|(=$)|(^[^=]*$)|(^[^x]*$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trimmedEq);
        return !matcher.find();
    }

}
