package com.epam.shchuryk.infohandling.parser;

import com.epam.shchuryk.infohandling.entity.Component;
import com.epam.shchuryk.infohandling.entity.ComponentType;
import com.epam.shchuryk.infohandling.entity.CompositePart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements ComponentParser {
    private static Logger LOGGER = LogManager.getLogger(TextParser.class);
    private final static String PARAGRAPH_PATTERN = "\\t|\\s{4}(.+)(\\n?)";

    private ParagraphParser paragraphParser = new ParagraphParser();

    public CompositePart parse(String rawData) {
        LOGGER.debug("Parsing to paragraphs.");
        CompositePart paragraphs = new CompositePart(ComponentType.TEXT);
        Matcher matcher = Pattern.compile(PARAGRAPH_PATTERN).matcher(rawData);
        while (matcher.find()) {
            paragraphs.add(chain(matcher.group(1)));
        }

        LOGGER.debug("Paragraphs were parsed.");
        return paragraphs;
    }

    @Override
    public Component chain(String data) {
        return paragraphParser.parse(data);
    }

}
