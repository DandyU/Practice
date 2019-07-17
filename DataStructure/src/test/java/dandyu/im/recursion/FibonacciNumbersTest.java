package dandyu.im.recursion;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciNumbersTest {

    @Test
    public void test() {
        long startTime, lastTime;
        startTime = System.currentTimeMillis();
        long result1 = FibonacciNumbers.recursiveCall(50);
        lastTime = System.currentTimeMillis();
        System.out.println("recursiveCall millis: " + (lastTime - startTime));
        System.out.println("result1: " + result1);

        startTime = System.currentTimeMillis();
        long result2 = FibonacciNumbers.loopCall(50);
        lastTime = System.currentTimeMillis();
        System.out.println("recursiveCall millis: " + (lastTime - startTime));
        System.out.println("result2: " + result2);
        Assert.assertTrue(result1 == result1);
    }
}