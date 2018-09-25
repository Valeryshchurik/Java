package com.epam.shchuryk.infohandling.reader;

import com.epam.shchuryk.infohandling.exception.InputDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private static Logger LOGGER = LogManager.getLogger(DataReader.class);
    private static String TEXT_DELIMITER = "\\Z";

    public String readText(String filename) throws InputDataException {
        try (Scanner input = new Scanner(new File(filename))) {
            String text = input.useDelimiter(TEXT_DELIMITER).next();
            LOGGER.debug("File opened successfully.");
            return text;
        } catch (FileNotFoundException e) {
            throw new InputDataException("Can't find file: " + filename);
        }
    }

    public List<Integer> readNumbers(String filename) throws InputDataException {
        try (Scanner input = new Scanner(new File(filename))) {
            LOGGER.debug("File opened successfully.");
            List<Integer> numbers = new ArrayList<>();
            while (input.hasNextInt()) {
                numbers.add(input.nextInt());
            }
            return numbers;
        } catch (FileNotFoundException e) {
            throw new InputDataException(" Can't find file: " + filename);
        }
    }
}
