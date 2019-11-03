import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Local number is not equal to", 14, MainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        Assert.assertTrue("Class number <= 45", mainClass.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        String testString = new MainClass().getClassString();
        Assert.assertTrue("The string \""+ testString + "\" does not contain the expected substring",
                testString.contains("hello") || testString.contains("Hello"));
    }
}
