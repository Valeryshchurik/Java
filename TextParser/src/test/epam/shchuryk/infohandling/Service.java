package test.epam.shchuryk.infohandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Service {
    private Path testFile;

    void setUp(String filepath, String text) {
        testFile = Paths.get(filepath);
        try {
            Files.write(testFile, text.getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dropFile() {
        try {
            Files.delete(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
