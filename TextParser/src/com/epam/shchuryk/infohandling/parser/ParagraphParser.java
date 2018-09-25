package com.epam.shchuryk.infohandling.parser;

import com.epam.shchuryk.infohandling.entity.Component;
import com.epam.shchuryk.infohandling.entity.ComponentType;
import com.epam.shchuryk.infohandling.entity.CompositePart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParagraphParser implements ComponentParser {
    private static Logger LOGGER = LogManager.getLogger(ParagraphParser.class);
    private SentenceParser sentenceParser = new SentenceParser();
    private final static String SENTENCE_PATTERN = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";


    public CompositePart parse(String paragraph) {
        LOGGER.debug("Parsing to sentences.");
        CompositePart sentences = new CompositePart(ComponentType.PARAGRAPH);
        Matcher matcher = Pattern.compile(SENTENCE_PATTERN).matcher(paragraph);
        while (matcher.find()) {
            sentences.add(chain(matcher.group()));
        }

        LOGGER.debug("Sentences were parsed.");
        return sentences;
    }

    @Override
    public Component chain(String data) {
        return sentenceParser.parse(data);
    }

}
