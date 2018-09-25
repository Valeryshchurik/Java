package test.com.shchuryk.kickstart.validator;

import com.shchuryk.kickstart.validator.CubeValidator;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CubeValidatorTest {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CubeValidatorTest.class);
    private List<String> testStrings = new java.util.ArrayList<>(java.util.Arrays.asList(
            "1.0 2.0 3.0 edge 6.1",
            "1.0 2.0 3.0c edge 13.0",
            "-1.0 2.4+ 3.2 edge 1.3",
            "1.0 2.0 3.0 edge 6.2"));

    @Test
    public void CubeValidationTest() {
        CubeValidator cubeValidator = CubeValidator.getInstance();
        testStrings.removeIf(line -> (!cubeValidator.validateCubeLine(line)));
        List<String> expected = Arrays.asList("1.0 2.0 3.0 edge 6.1", "1.0 2.0 3.0 edge 6.2");
        List<String> actual = testStrings;
        Assert.assertEquals(expected, actual);
    }
}
