package com.tsystems.javaschool.tasks.calculator;

import java.util.StringTokenizer;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    private final String operations = "+-*/";
    private CalcStack<String> stackOfOperators = new CalcStack<>();
    private CalcStack<String> RPNstack = new CalcStack<>();
    private final String openedBracket = "(";
    private final String closedBracket = ")";

    private boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String element) {
        if (operations.contains(element))
            return true;
        else
            return false;
    }

    private int getPriority(String element) {
        if (element.equals("+") || element.equals("-"))
            return 0;
        else
            return 1;
    }

    private boolean hasDuplicateOperations(String str) {
        if(str.contains("++") || str.contains("--") || str.contains("**")
                || str.contains("//") || str.contains("..") || str.contains(","))
            return true;
        else
            return false;
    }

    private boolean isOpenedBracket(String element) {
        if(element.equals(openedBracket))
            return true;
        else
            return false;
    }

    private String convertToRPN(String statement) {
       // char [] splittedStatement = statement.toCharArray();
        if (hasDuplicateOperations(statement)) {
            return null;
        }
        StringTokenizer myTokenizer = new StringTokenizer(statement,operations + "()", true);
        while (myTokenizer.hasMoreTokens()) {
            String token = myTokenizer.nextToken();
            if (token.equals(openedBracket)) {
                stackOfOperators.push(token);
            } else if (token.equals(closedBracket)) {
                while (!stackOfOperators.isEmpty() && !isOpenedBracket(stackOfOperators.getLastElement())) {
                    RPNstack.push(stackOfOperators.pop());
                    if (stackOfOperators.isEmpty()) {
                        return null;
                    }
                }
                stackOfOperators.pop();
            if (stackOfOperators != null){
                RPNstack.push(stackOfOperators.pop());
            }

            } else if (isNumber(token)) {
                RPNstack.push(token);

            } else if (isOperator(token)) {
                while (!stackOfOperators.isEmpty() && isOperator(stackOfOperators.getLastElement())
                        && getPriority(token) <= getPriority(stackOfOperators.getLastElement())) {
                    RPNstack.push(stackOfOperators.pop());
                }
                stackOfOperators.push(token);
            }
        }
        while (!stackOfOperators.isEmpty()) {
            RPNstack.push(stackOfOperators.pop());
        }
        StringBuilder resultantRPN = new StringBuilder();
        if (!RPNstack.isEmpty()) {
            resultantRPN = resultantRPN.append(RPNstack.myList);
            RPNstack.clear();
        }
        return resultantRPN.toString();
    }

      public String evaluate(String statement) {
          try {
              String rpn = convertToRPN(statement);

              if (rpn.isEmpty() && rpn.equals("")) {
                  return null;
              }
              String regRpn, regRpn2;
              regRpn = rpn.replaceAll("\\[","");
              regRpn2 = regRpn.replaceAll("\\]","");
              String[] elementsOfRPN = regRpn2.split(", ");
              CalcStack<Double> resultStack = new CalcStack<>();
              for (int i = 0; i <= elementsOfRPN.length-1; i++){
                  if (!operations.contains(elementsOfRPN[i])) {
                      resultStack.push(new Double(elementsOfRPN[i]));
                  } else {
                      Double operand1;
                      Double operand2 = resultStack.pop();
                      if (resultStack.isEmpty())
                           operand1 = 0.0;
                      else operand1 = resultStack.pop();
                      switch (elementsOfRPN[i]) {
                          case "*":
                              resultStack.push(operand1 * operand2);
                              break;
                          case "/":
                              if (!(operand2 == 0)) {
                                  resultStack.push(operand1 / operand2);
                                  break;
                              } else {
                                  throw new ArithmeticException();
                              }
                          case "+":
                              resultStack.push(operand1 + operand2);
                              break;
                          case "-":
                              resultStack.push(operand1 - operand2);
                              break;
                          default:
                              System.out.println("Wrong operator");
                      }
                  }
              }
              Double resultOfCalculation = resultStack.pop();
              if (resultOfCalculation % 1.0 == 0){
              return String.format("%.0f",resultOfCalculation);}
                  else
              return String.valueOf((resultOfCalculation));
          } catch (Exception ex) {
              return null;
          }
     }
}






