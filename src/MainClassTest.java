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
<<<<<<< HEAD
        String testString = new MainClass().getClassString();
        Assert.assertTrue("The string \""+ testString + "\" does not contain the expected substring",
                testString.contains("hello") || testString.contains("Hello"));
=======
        MainClass mainClass = new MainClass();
        Assert.assertTrue("The string \""+ mainClass.getClassString() + "\" does not contain the expected substring", mainClass.getClassString().contains("hello") || mainClass.getClassString().contains("Hello"));
>>>>>>> 41af7424360a96a4ff8ea9ae6be222e28c919059
    }
}
