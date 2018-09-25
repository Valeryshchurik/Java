package com.shchuryk.kickstart.reader;

import com.shchuryk.kickstart.validator.CubeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CubeFileReader implements CubeReader {
    private static final Logger LOGGER = LogManager.getLogger(CubeFileReader.class);
    private String filepath;

    public CubeFileReader(String filepath) {
        this.filepath = filepath;
    }

    public List<String> readCubes() {
        List<String> inputLines;
        CubeValidator cubeValidator = CubeValidator.getInstance();
        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
            inputLines = stream.filter(cubeValidator::validateCubeLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.catching(Level.FATAL, e);
            throw new RuntimeException(e);
        }
        LOGGER.info("file " + filepath + "was read successfully");
        return inputLines;
    }
}
