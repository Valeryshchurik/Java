package com.epam.shchuryk.infohandling.action;

import com.epam.shchuryk.infohandling.exception.InputDataException;
import com.epam.shchuryk.infohandling.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExpressionReplacer {
    private static Logger LOGGER = LogManager.getLogger(MathExpressionReplacer.class);

    private static final String INCREMENT_PATTERN = "\\s*(\\+\\+)[x]|\\s*[x](\\+\\+)";
    private static final String DECREMENT_PATTERN = "\\s*(\\-\\-)[x]|\\s*[x](\\-\\-)";
    private static final String NUMBERS_FILEPATH = "test_data/numbers.txt";

    public String cleanUpText(String rawText) {
        DataReader dataReader = new DataReader();
        try {
            List<Integer> numbers = dataReader.readNumbers(NUMBERS_FILEPATH);
            rawText = replaceIncrementExpression(rawText, numbers);
            rawText = replaceDecrementExpression(rawText, numbers);

        } catch (InputDataException e) {
            LOGGER.error("Invalid data in file.");
        }
        LOGGER.debug("Text was successfully cleaned.");
        return rawText;
    }

    private String replaceIncrementExpression(String rawText, List<Integer> numbers) {
        Matcher matcher = Pattern.compile(INCREMENT_PATTERN.replace('x', 'i')).matcher(rawText);
        rawText = matcher.replaceAll(String.valueOf(numbers.get(0) + 1));
        matcher = Pattern.compile(INCREMENT_PATTERN.replace('x', 'j')).matcher(rawText);
        rawText = matcher.replaceAll(String.valueOf(numbers.get(1) + 1));
        LOGGER.debug("Increment values were counted.");
        return rawText;
    }

    private String replaceDecrementExpression(String rawText, List<Integer> numbers) {
        Matcher matcher = Pattern.compile(DECREMENT_PATTERN.replace('x', 'i')).matcher(rawText);
        rawText = matcher.replaceAll(String.valueOf(numbers.get(0) - 1));
        matcher = Pattern.compile(DECREMENT_PATTERN.replace('x', 'j')).matcher(rawText);
        rawText = matcher.replaceAll(String.valueOf(numbers.get(1) - 1));
        LOGGER.debug("Decrement values were counted.");
        return rawText;
    }
}
