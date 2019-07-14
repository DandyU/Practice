package dandyu.im;

import org.junit.Assert;
import org.junit.Test;

public class XArrayListTest {

    @Test
    public void isFull() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        Assert.assertTrue(arrayList.isFull());
    }

    @Test
    public void add() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
            Assert.assertEquals(i, arrayList.get(i).intValue());
        }
        System.out.println();
    }

    @Test
    public void remove() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        arrayList.remove(2);

        String temp = "";
        for (int i = 0; i < 4; i++)
            temp += arrayList.get(i).intValue();
        Assert.assertEquals("0134", temp);
    }

    @Test
    public void clearList() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        arrayList.clearList();
        Assert.assertEquals(0, arrayList.size());
    }

    @Test
    public void size() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        Assert.assertEquals(5, arrayList.size());
    }

    @Test
    public void get() {
        XArrayList<Integer> arrayList = new XArrayList(Integer[].class, 5);
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Integer(i));
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        Assert.assertEquals(2, arrayList.get(2).intValue());
    }

}