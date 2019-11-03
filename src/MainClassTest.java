import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Local number is not equal to", 14, MainClass.getLocalNumber());
    }
}
