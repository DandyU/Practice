package dandyu.im.recursion;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class HanoiTowerTest {

    @Test
    public void move() {
        int n = 3;
        HanoiTower.move(n, "A", "B",  "C");
        Assert.assertTrue(true);
    }
}