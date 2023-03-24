package com.yukotsiuba.equation.utils;

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
        return false;
    }

}
