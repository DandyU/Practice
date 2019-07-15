package dandyu.im.stack;

import java.lang.reflect.Array;

public class XArraySimpleStack<E> {

    private E[] array;
    private int pos;
    private int size;

    public XArraySimpleStack(Class<E> type, int size) {
        this.array = (E[]) Array.newInstance(type.getComponentType(), size);
        this.size = size;
        pos = -1;
    }

    public boolean isFull() {
        if ((pos + 1) == size)
            return true;

        return false;
    }

    public boolean isEmpty() {
        if (pos == -1)
            return true;

        return false;
    }

    public boolean push(E element) {
        if (isFull())
            throw new StackOverflowError();

        array[++pos] = element;
        return true;
    }

    public E pop() {
        if (isEmpty())
            throw new RuntimeException("StackUnderflowError");

        return array[pos--];
    }

    public E peek() {
        if (isEmpty())
            throw new RuntimeException("StackUnderflowError");

        return array[pos];
    }

    public int size() {
        return pos + 1;
    }
}
