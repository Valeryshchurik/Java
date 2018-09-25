package com.shchuryk.multithreading.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientFileReader implements ClientReader {
    private static final Logger LOGGER = LogManager.getLogger(ClientFileReader.class);

    private String filepath;

    public ClientFileReader(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public List<String> read() {
        List<String> inputLines;
        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
            inputLines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.catching(Level.FATAL, e);
            throw new RuntimeException(e);
        }
        LOGGER.info("file " + filepath + "was read successfully");
        return inputLines;
    }
}
