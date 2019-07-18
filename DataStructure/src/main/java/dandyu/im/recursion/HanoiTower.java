package dandyu.im.recursion;

import java.util.LinkedList;

public class HanoiTower {

    private static void move(LinkedList<String> from, LinkedList<String> to) {
        to.push(from.pop());
        //System.out.println(from + " → " + to);
    }

    public static void hanoi(int n, LinkedList<String> from, LinkedList<String> to, LinkedList<String> temp) {
        if (n == 1) {
            move(from, to);
        } else {
            hanoi(n - 1, from, temp, to);
            move(from, to);
            hanoi(n - 1, temp, to, from);
        }
    }
}
