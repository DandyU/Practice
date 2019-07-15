package dandyu.im.list;

public class XCircularLinkedSimpleList<E> implements XSimpleList<E> {

    private Node tail;
    private int size;

    @Override
    public boolean add(E element) {
        if (tail == null) {
            tail = new Node(element, null);
            tail.next = tail;
        } else {
            Node lastNdde = tail;
            Node firstNode = tail.next;

            tail = new Node(element, null);
            lastNdde.next = tail;
            tail.next = firstNode;
        }
        size++;

        return true;
    }

    @Override
    public boolean remove(int index) {
        final int listSize = size();
        if (index < 0 || index >= listSize)
            throw new IndexOutOfBoundsException();

        if (listSize == 1) {
            tail = null;
            size = 0;

            return true;
        }

        Node previous = null;
        Node current = tail.next;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        size--;

        return true;
    }

    @Override
    public void clearList() {
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        final int listSize = size();
        if (index < 0 || index >= listSize)
            throw new IndexOutOfBoundsException();

        Node current = tail.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return (E) current.data;
    }

    class Node<E> {
        E data;
        Node next;

        Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
