package dandyu.im.recursion;

public class FibonacciNumbers {

    public static long recursiveCall(long number) {
        if (number == 0)
            return 0;

        if (number == 1)
            return 1;

        return recursiveCall(number - 1) + recursiveCall(number - 2);
    }

    public static long loopCall(long number) {
        if (number < 2)
            return number;

        long first = 0;
        long second = 1;
        long temp;
        for (long i = 2; i <= number; i++) {
            temp = second;
            second += first;
            first = temp;
        }

        return second;
    }

}
