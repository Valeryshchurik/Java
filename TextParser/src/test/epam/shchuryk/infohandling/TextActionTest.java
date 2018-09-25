package test.epam.shchuryk.infohandling;

import com.epam.shchuryk.infohandling.action.MathExpressionReplacer;
import com.epam.shchuryk.infohandling.action.TextAction;
import com.epam.shchuryk.infohandling.entity.CompositePart;
import com.epam.shchuryk.infohandling.exception.InputDataException;
import com.epam.shchuryk.infohandling.parser.TextParser;
import com.epam.shchuryk.infohandling.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextActionTest {
    private TextAction textAction = new TextAction();
    private Service service = new Service();
    private List<Object[]> parameters = new ArrayList<>();

    @BeforeClass
    public void initializeTools() {
        String text_filepath = "test_data/input.txt";
        String text = "    It has survived - not only (five) centuries, but also the leap into 13+i-- electronic typesetting, remaining 3+5 essentially 6+9*(3-4) unchanged." +
                " It was popularised in the 5*(1*2*(3*(4*(5---j+4)-3)-2)-1) with the release of Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." +
                " The point of using (71-(2*i--*(3*(2-1/2*2)-2)-10/2))*++i Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here)," +
                " content here', making it look like readable English.\n" +
                "    It is a (1/2*(2+5*2---j)-5)*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.\n";
        service.setUp(text_filepath, text);

        String values_filepath = "test_data/values.txt";
        String values = "4 125";
        service.setUp(values_filepath, values);

        try {
            DataReader dataReader = new DataReader();
            TextParser textParser = new TextParser();

            String input = dataReader.readText(text_filepath);
            MathExpressionReplacer replacer = new MathExpressionReplacer();
            input = replacer.cleanUpText(input);
            CompositePart compositePart = textParser.parse(input);

            List<Integer> expectedValues = dataReader.readNumbers(values_filepath);
            System.out.print(compositePart);
            Object[] data = new Object[]{compositePart, expectedValues.get(0), expectedValues.get(1)};
            parameters.add(data);
        } catch (InputDataException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "data")
    public Iterator<Object[]> retrieveData() {
        return parameters.iterator();
    }

    @Test(dataProvider = "data")
    public void repeatableLexemesTest(CompositePart part, int repeatableLexemes, int wordsCounted) {
        Assert.assertEquals(textAction.countRepeatableLexemes(part), repeatableLexemes);
    }
    
    @Test(dataProvider = "data")
    public void countWordsInTextTest(CompositePart part, int repeatableLexemes, int wordsCounted){
        Assert.assertEquals(textAction.countWordsInText(part), wordsCounted);
    }

    @AfterClass
    public void closeFiles() {
        service.dropFile();
    }
}
