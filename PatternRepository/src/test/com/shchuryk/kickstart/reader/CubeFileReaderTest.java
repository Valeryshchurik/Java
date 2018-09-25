package test.com.shchuryk.kickstart.reader;

import com.shchuryk.kickstart.reader.CubeFileReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.fail;

public class CubeFileReaderTest {
    private String wrongFilePath = "data/cube_TTTTTTTTTTTTT.txt";
    private String testFilePath = "data/cube_test.txt";
    private Path testFile;

    List<String> testData = Arrays.asList(
            "1.0 2.0 3.0 edge 6.1",
            "1.0 2.0 3.0 edge 13.0",
            "-1.0 2.4 3.2 edge 1.3",
            "1.0 2.0 3.0 edge 6.2",
            "1.0 -2.2.2 23 edge 4",
            "2 2 2 edge 2");

    @BeforeClass
    public void setUp() {
        testFile = Paths.get(testFilePath);
        try {
            Files.write(testFile, testData, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void dropFile() {
        try {
            Files.delete(testFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readCubesPositiveTest() {
        List<String> actualLines;
        CubeFileReader cubeFileReader = new CubeFileReader(testFilePath);
        actualLines = cubeFileReader.readCubes();
        List<String> expectedLines = Arrays.asList(
                "1.0 2.0 3.0 edge 6.1",
                "1.0 2.0 3.0 edge 13.0",
                "-1.0 2.4 3.2 edge 1.3",
                "1.0 2.0 3.0 edge 6.2",
                "2 2 2 edge 2");
        Assert.assertEquals(expectedLines, actualLines);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void readCubesNegativeTest() {
        CubeFileReader cubeFileReader = new CubeFileReader(wrongFilePath);
        cubeFileReader.readCubes();
        fail();
    }
}

