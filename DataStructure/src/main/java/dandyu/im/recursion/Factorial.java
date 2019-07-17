package dandyu.im.recursion;

public class Factorial {
    public static int factorial(int value) {
        return (value == 1) ? 1 : (value * factorial(value -1));
    }
}
