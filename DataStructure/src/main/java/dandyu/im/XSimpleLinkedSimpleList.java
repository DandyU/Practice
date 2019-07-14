package dandyu.im;

public class XSimpleLinkedSimpleList<E> implements XSimpleList<E> {

    private Node head;
    private int size;

    @Override
    public boolean add(E element) {
        if (head == null) {
            head = new Node(element, null);
        } else {
            Node current = head;
            final int listSize = size();
            for (int i = 0; i < (listSize - 1); i++) {
                current = current.next;
            }
            current.next = new Node(element, null);
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
            head = null;
            size--;

            return true;
        }

        Node previous = null;
        Node current = head;
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
        head = null;
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

        Node current = head;
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
