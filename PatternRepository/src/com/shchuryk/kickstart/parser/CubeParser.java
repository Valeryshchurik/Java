package com.shchuryk.kickstart.parser;

import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.util.CubeProjectConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeParser {
    private static final Logger LOGGER = LogManager.getLogger(CubeParser.class);

    private static CubeParser ourInstance = new CubeParser();

    public static CubeParser getInstance() {
        return ourInstance;
    }

    private CubeParser() {
    }

    public static double[] parseCubeLine(String line) throws CubeDataException {
        Pattern pattern = Pattern.compile(CubeProjectConstants.CUBE_PATTERN);
        Matcher m = pattern.matcher(line);
        double[] values = new double[CubeProjectConstants.NUMBER_OF_CUBE_PARAMS];
        if (!m.find())
            throw new CubeDataException();
        LOGGER.debug("Cube parser result for the following line: " + line);
        for (int i = 0; i < CubeProjectConstants.NUMBER_OF_CUBE_PARAMS; i++) {
            values[i] = Double.parseDouble(m.group(i + 1));
            LOGGER.debug(values[i]);
        }
        return values;
    }
}
