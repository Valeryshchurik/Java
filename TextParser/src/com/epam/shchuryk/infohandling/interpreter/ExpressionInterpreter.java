package com.epam.shchuryk.infohandling.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.BiFunction;

public class ExpressionInterpreter {
    private static Logger LOGGER = LogManager.getLogger(ExpressionInterpreter.class);

    private void interpret(ArrayDeque<Double> numbers, BiFunction<Double, Double, Double> operation) {
        LOGGER.debug("Adding data to array.");
        numbers.push(operation.apply(numbers.pop(), numbers.pop()));
    }

    public Double calculate(String input) {
        LOGGER.debug("Calculating expression: " + input);
        ArrayDeque<Double> numbers = new ArrayDeque<>();
        ExpressionConverter expressionConverter = new ExpressionConverter();
        String convertedPolandNotation = expressionConverter.convertToReversePolandNotation(input);
        Arrays.stream(convertedPolandNotation.split(" ")).forEach(number -> {
            switch (number) {
                case "+":
                    interpret(numbers, (n1, n2) -> n2 + n1);
                    break;
                case "-":
                    interpret(numbers, (n1, n2) -> n2 - n1);
                    break;
                case "*":
                    interpret(numbers, (n1, n2) -> n2 * n1);
                    break;
                case "/":
                    interpret(numbers, (n1, n2) -> n2 / n1);
                    break;
                default:
                    numbers.push(Double.parseDouble(number));
            }
        });

        Double result = numbers.pop();

        LOGGER.debug("Expression calculating result: " + result);
        return result;
    }
}