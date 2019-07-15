package dandyu.im.stack;

import org.junit.Assert;
import org.junit.Test;

public class XArraySimpleStackTest {

    @Test
    public void test() {
        XArraySimpleStack<String> stack = new XArraySimpleStack(String[].class, 5);
        Assert.assertTrue(stack.isEmpty());
        stack.push("Hello");
        stack.push("World");
        stack.push("Awesome");
        stack.push("World");
        stack.push("!");
        Assert.assertTrue(stack.isFull());
        Assert.assertEquals("!", stack.pop());
        Assert.assertEquals("World", stack.peek());
        Assert.assertEquals("World", stack.pop());
        Assert.assertEquals(3, stack.size());
    }

}