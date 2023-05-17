package misc.secure;

public class Test {

    public static void main(String[] args) {
        boolean isTrue = Integer.MIN_VALUE == -Integer.MIN_VALUE;

        if (isTrue) {
            System.out.println("integer min value equals negative of integer min value");
        }

        int absIntMinValue = Math.abs(Integer.MIN_VALUE);
        System.out.println("abs of integer min value is " + absIntMinValue);
    }
}
