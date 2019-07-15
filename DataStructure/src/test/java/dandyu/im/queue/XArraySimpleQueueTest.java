package dandyu.im.queue;

import dandyu.im.stack.XArraySimpleStack;
import org.junit.Assert;
import org.junit.Test;

public class XArraySimpleQueueTest {
    @Test
    public void test() {
        XArraySimpleQueue<String> queue = new XArraySimpleQueue(String[].class, 5);
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue("Hello");
        queue.enqueue("World");
        queue.enqueue("Awesome");
        queue.enqueue("World");
        queue.enqueue("!");
        Assert.assertTrue(queue.isFull());
        Assert.assertEquals("Hello", queue.dequeue());
        Assert.assertEquals("World", queue.peek());
        Assert.assertEquals("World", queue.dequeue());
    }
}