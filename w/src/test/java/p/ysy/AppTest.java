package p.ysy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        t();
    }

    static void t() {
        try {
            System.out.println("start");
            throw new Exception("s");
        } catch (Exception e) {
            System.out.println("e");
            return;
        } finally {
            System.out.println("final");
        }
    }
}
