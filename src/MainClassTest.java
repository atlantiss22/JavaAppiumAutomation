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
}
