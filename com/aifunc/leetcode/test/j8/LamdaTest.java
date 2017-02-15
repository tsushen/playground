package com.aifunc.leetcode.test.j8;

/**
 * Created by alex on 12/17/16.
 */
public class LamdaTest {

  interface MathOperation {
    int operation(int a , int b);
  }

  interface GreetingService {
    void sayMessage(String message);
  }

  interface OperationService {
    void sayOperation(String symbol, MathOperation operation);
  }

  private int operate(int a, int b, MathOperation mathOperation) {
    return mathOperation.operation(a, b);
  }

  public static void main(String args[]) {
    LamdaTest tester = new LamdaTest();

    MathOperation addition = (int a, int b) -> a + b;

    MathOperation substraction = (a, b) -> a - b;

    MathOperation multiplication = (int a, int b) -> {return a * b; };

    MathOperation division = (int a, int b) -> a / b;

    GreetingService greetingService1 = message -> System.out.println("hello " + message);

    OperationService operationService = (symbol, operation) -> System.out.println(
      "10 "+ symbol +" 5 = " + tester.operate(10, 5, operation)
    );

    operationService.sayOperation("+", addition);
    operationService.sayOperation("-", substraction);
    operationService.sayOperation("*", multiplication);
    operationService.sayOperation("/", division);
  }
}
