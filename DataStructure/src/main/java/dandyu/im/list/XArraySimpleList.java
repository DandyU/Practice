package dandyu.im.list;

import java.lang.reflect.Array;

public class XArraySimpleList<E> implements XSimpleList<E> {

    final private E[] array;
    final private int arraySize;
    private int cursor;

    public XArraySimpleList(Class<E> type, int size) {
        array = (E[]) Array.newInstance(type.getComponentType(), size);
        arraySize = size;
        cursor = 0;
    }

    public boolean isFull() {
        if (size() == arraySize)
            return true;

        return false;
    }

    @Override
    public boolean add(E element) {
        if (size() >= arraySize)
            throw new IndexOutOfBoundsException();

        array[cursor] = element;
        cursor++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        System.arraycopy(array, (index + 1), array, index, (size() - (size() - (index))));
        cursor--;
        return true;
    }

    @Override
    public void clearList() {
        cursor = 0;
    }

    @Override
    public int size() {
        return cursor;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        return array[index];
    }
}
