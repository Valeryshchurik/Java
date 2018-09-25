package com.epam.shchuryk.infohandling.parser;

import com.epam.shchuryk.infohandling.entity.Component;
import com.epam.shchuryk.infohandling.entity.ComponentType;
import com.epam.shchuryk.infohandling.entity.CompositePart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SentenceParser implements ComponentParser {
    private static Logger LOGGER = LogManager.getLogger(SentenceParser.class);

    private LeafParser leafParser = new LeafParser();

    public CompositePart parse(String line) {
        LOGGER.debug("Parsing to lexemes.");
        CompositePart leafs = new CompositePart(ComponentType.SENTENCE);
        String[] words = line.trim().split(" ");

        for (String word : words) {
            leafs.add(chain(word));
        }

        LOGGER.debug("Lexemes were parsed..");
        return leafs;
    }

    @Override
    public Component chain(String data) {
        return leafParser.parse(data);
    }
}