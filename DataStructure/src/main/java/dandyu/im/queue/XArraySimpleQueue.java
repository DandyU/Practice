package dandyu.im.queue;

import java.lang.reflect.Array;

public class XArraySimpleQueue<E> {

    private E[] array;
    private int size;
    private int front;
    private int rear;

    public XArraySimpleQueue(Class<E> type, int size) {
        array = (E[]) Array.newInstance(type.getComponentType(), size + 1);
        this.size = size + 1;
    }

    public boolean isFull() {
        if ((rear + 1) % this.size == front)
            return true;

        return false;
    }

    public boolean isEmpty() {
        if (front == rear)
            return true;

        return false;
    }

    public boolean enqueue(E element) {
        if (isFull())
            throw new StackOverflowError();

        array[rear] = element;
        rear = (rear + 1) % this.size;
        return true;
    }

    public E dequeue() {
        E element = array[front];
        front = (front + 1) % this.size;
        return element;
    }

    public E peek() {
        return array[front];
    }

}
