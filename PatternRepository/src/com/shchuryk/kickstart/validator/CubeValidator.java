package com.shchuryk.kickstart.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

import static com.shchuryk.kickstart.util.CubeProjectConstants.*;

public class CubeValidator {
    private final static Logger LOGGER = LogManager.getLogger(CubeValidator.class);

    private static CubeValidator ourInstance = new CubeValidator();

    public static CubeValidator getInstance() {
        return ourInstance;
    }

    private CubeValidator() {
    }

    public boolean validateCubeLine(String line) {
        Pattern pattern = Pattern.compile(CUBE_PATTERN);
        boolean isValid = pattern.matcher(line).matches();
        LOGGER.debug("Cube validation result for the following line: " + line + " - " + isValid);
        return isValid;
    }

}
