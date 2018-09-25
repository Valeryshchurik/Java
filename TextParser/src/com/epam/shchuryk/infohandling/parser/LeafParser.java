package com.epam.shchuryk.infohandling.parser;

import com.epam.shchuryk.infohandling.entity.Component;
import com.epam.shchuryk.infohandling.entity.Leaf;
import com.epam.shchuryk.infohandling.entity.LeafType;
import com.epam.shchuryk.infohandling.interpreter.ExpressionInterpreter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;
public class LeafParser implements ComponentParser {
    private static Logger LOGGER = LogManager.getLogger(LeafParser.class);
    private final static String PUNCTUATION_MARK_PATTERN = "[\\p{P}]+";
    private final static String MATH_EXPRESSION_PATTERN = "^(\\(|[0-9])[^a-zA-Z]+(\\)|[0-9])$";

    @Override
    public Component parse(String leaf) {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        if (isPunctuationMark(leaf)) {//switchcase
            LOGGER.debug("Punctuation mark parsed.");
            return new Leaf(leaf.trim(), LeafType.PUNCTUATION_MARK);
        } else if (isMathExpression(leaf)) {
            LOGGER.debug("Math expression parsed.");
            return new Leaf(expressionInterpreter.calculate(leaf).toString(), LeafType.WORD);//////////////
        } else {
            LOGGER.debug("Word parsed.");
            return new Leaf(leaf.trim(), LeafType.WORD);
        }
    }

    @Override
    public Component chain(String data) {
        return null;
    }

    private boolean isPunctuationMark(String leaf) {
        return Pattern.compile(PUNCTUATION_MARK_PATTERN).matcher(leaf).matches();
    }

    private boolean isMathExpression(String leaf) {
        return Pattern.compile(MATH_EXPRESSION_PATTERN).matcher(leaf).matches();
    }
}
