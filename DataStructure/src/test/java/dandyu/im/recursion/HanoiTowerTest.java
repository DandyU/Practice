package dandyu.im.recursion;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class HanoiTowerTest {

    @Test
    public void move() {
        int n = 3;
        LinkedList<String> a = new LinkedList<>();
        LinkedList<String> b = new LinkedList<>();
        LinkedList<String> c = new LinkedList<>();
        a.push("3");
        a.push("2");
        a.push("1");

        System.out.println("A");
        Arrays.stream(a.toArray()).forEach(e -> System.out.println(e));

        HanoiTower.hanoi(n, a, b,  c);

        System.out.println("B");
        Arrays.stream(b.toArray()).forEach(e -> System.out.println(e));
        Assert.assertTrue(true);
    }
}