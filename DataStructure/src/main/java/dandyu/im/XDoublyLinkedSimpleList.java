package dandyu.im;

public class XDoublyLinkedSimpleList<E> implements XSimpleList<E> {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public boolean add(E element) {
        if (size == 0) {
            Node newNode = new Node(element, null, null);
            head = newNode;
            tail = newNode;
        } else {
            Node newNode = new Node(element, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;

        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        if (size == 1) {
            head = null;
            tail = null;
            size = 0;

            return true;
        }

        Node current;
        if (index < (size() / 2)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = 0; i < index; i++) {
                current = current.previous;
            }
        }

        if (head == current) {
            head = current.next;
        } else if (tail == current) {
            tail = current.previous;
        } else {
            Node previos = current.previous;
            Node next = current.next;
            previos.next = next;
            next.previous = previos;
        }
        size--;

        return true;
    }

    @Override
    public void clearList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return (E) current.data;
    }

    class Node<E> {
        E data;
        Node previous;
        Node next;

        Node(E data, Node previous, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }

}
