package com.yukotsiuba.equation.utils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class EquationUtils {
    private static Pattern simpleLang = Pattern.compile("^\\(*-?\\(*(\\d+([.]\\d+)?|x)([-+*/]\\(*-?\\(*(\\d+([.]\\d+)?|x)\\)*)*$");


    public static boolean validateEquation(String equation) {
        if(!equation.contains("x")){
            return false;
        } 
        if(!containsOnlyOneEqual(equation)){
            return false;
        }
        if(!validateParentheses(equation)){
            return false;
        }
        String trimmedEq = equation.replaceAll("\\s", "");
        String[] expressions = trimmedEq.split("=");
        for(String expression:expressions){
            if(!simpleLang.matcher(expression).matches()){
                return false;
            }
        }
        return true;
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

    public static boolean validateRoot(String expression, Double value) {
        expression = expression.replaceAll("x", value.toString());
        String[] expressionParts = expression.split("=");
        DecimalFormat df = getResultFormat(getPrecision(value)); 
        String leftPart = getResult(expressionParts[0], df);
        String rightPart = getResult(expressionParts[1], df);
        return leftPart.equals(rightPart);
    }
    
    private static DecimalFormat getResultFormat(int precission) {
        StringBuilder format = new StringBuilder("#.");
        for(int i=0;i<precission;i++) {
            format.append("#");
        }
        return new DecimalFormat(format.toString());
    }
    
    private static int getPrecision(Double value) { // to obtain number of digits after dot
        String doubleString = value.toString();
        int decimalIndex = doubleString.indexOf(".");
        return doubleString.length() - decimalIndex - 1;
    }
    
    private static String getResult(String expression, DecimalFormat df) {
        double value = eval(expression);
        return df.format(value);
    }
    
    private static double eval(final String str) {
        return new Object() {
            int pos = -1;
            int ch;
            
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            
            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {// if current character contains the sign then return true and looking forward
                    nextChar();
                    return true;
                }
                return false;
            }
            
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char)ch); // if we parsed but expression still remains throw exception
                }
                return x;
            }
            
            double parseExpression() {
                double x = parseTerm();
                for (;;) { // addition or subtraction but first find multiplication or division
                    if(eat('+')) {
                        x += parseTerm(); 
                    }
                    else if(eat('-')) {
                        x -= parseTerm();
                    }
                    else return x;
                }
            }
            
            double parseTerm() {
                double x = parseFactor();//finds multiplication or division terms
                for (;;) {
                    if(eat('*')) { //now looking for signs
                        x *= parseFactor(); // multiplication
                    }
                    else if (eat('/')) {
                        x /= parseFactor(); // division
                    }
                    else return x;
                }
            }
            
            double parseFactor() {
                if (eat('-')) {
                    return -parseFactor(); // if negative sign
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) {
                        throw new RuntimeException("Missing ')'");
                    }
                } else if (isDigitChar(ch)) { // numbers
                    while (isDigitChar(ch)) {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                }  else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
            
            boolean isDigitChar(int c) {
                return (c >= '0' && c <= '9') || c == '.';
            }
            
        }.parse();
    }

}
