package dandyu.im;

import java.lang.reflect.Array;

public class XArrayList<T> {

    final private T[] array;
    final private int arraySize;
    private int cursor;


    public XArrayList(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type.getComponentType(), size);
        arraySize = size;
        cursor = 0;
    }

    public boolean isFull() {
        if (size() == arraySize)
            return true;

        return false;
    }

    public boolean add(T element) {
        if (size() >= arraySize)
            throw new IndexOutOfBoundsException();

        array[cursor] = element;
        cursor++;
        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(array, (index + 1), array, index, (size() - (size() - (index))));
        cursor--;
        return true;
    }

    public void clearList() {
        cursor = 0;
    }

    public int size() {
        return cursor;
    }

    public T get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        return array[index];
    }
}
