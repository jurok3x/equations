package com.yukotsiuba.equation.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EquationUtils {
    static final Pattern DIGIT_OR_X = Pattern.compile("[\\dx]");
    static final Pattern OPERATION = Pattern.compile("\\([\\dx][+*/-][\\dx]\\)");


    public static boolean validateEquation(String equation) {
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

    private static boolean validateParentheses(String equation) {
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

    private static boolean  containsOnlyOneEqual(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '=') {
                count++;
            }
        }
        return count == 1;
    }

    private static boolean isFormula(String s) {
        while (true) {
            if (DIGIT_OR_X.matcher(s).matches())
                return true;
            String rep = OPERATION.matcher(s).replaceAll("x");
            if (rep.equals(s))
                return false;
            s = rep;
        }
    }

    public static boolean validateRoot(String expression, Double value) {
        String[] expressionParts = expression.split("=");
        int precission = getPrecision(value);
        List<Double> results = new ArrayList<>();
        for(String expressionPart:expressionParts) {
            if(expressionPart.contains("x")) {
                String expressionFormat = getExpressionFormat(expressionPart, precission);
                expressionPart = String.format(expressionFormat, value);
            }
            results.add(evaluate(expressionPart)); 
        }
        return results.get(0) == results.get(1);
    }
    
    private static String getExpressionFormat(String expression, int precission) {
        String floatFormat = String.format("%%.%df", precission);      
        return expression.replaceAll("x", floatFormat);
    }
    
    private static int getPrecision(Double value) {
        String doubleString = value.toString();
        int decimalIndex = doubleString.indexOf(".");
        return doubleString.length() - decimalIndex - 1;
    }
    
    private static Double evaluate(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Double result = null;
        try {
            result = (double) engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

}
