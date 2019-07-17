package dandyu.im.recursion;

import org.junit.Assert;
import org.junit.Test;

public class FactorialTest {
    @Test
    public void testFactorial() {
        Assert.assertEquals(120, Factorial.factorial(5));
    }
}