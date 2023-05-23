package basics;

import java.util.*;

public class ScratchPad {

    public static void main(String[] s) {
        System.out.printf("hello world\n");

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(-1);
        list.add(2);

        Collections.sort(list);

        printf("sorted list: %s\n", list);

        Collections.sort(list, (o1, o2) -> o2 - o1);

        printf("reverse sorted list: %s\n", list);
    }

    public static void printf(String msg, Object ...s) {
        System.out.printf(msg, s);
    }
}

