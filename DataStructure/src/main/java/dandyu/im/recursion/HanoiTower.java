package dandyu.im.recursion;

import java.util.LinkedList;

public class HanoiTower {

    public static void move(int n, String from, String temp, String to) {
        if (n == 1) {
            System.out.println("1 원판을 " + from + "에서 " + to + "로 옮겼습니다.");
            return;
        } else {
            move(n - 1, from, to, temp);
            System.out.println(n + " 원판을 " + from + "에서 " + to + "로 옮겼습니다.");
            move(n - 1, temp, from, to);
        }
    }
}
