package leetcode.greedy;

import java.util.Arrays;

// source - https://www.codingninjas.com/studio/problems/fractional-knapsack_975286
public class FractionalKnapsack {

    class Pair {
        int weight;
        int value;

        Pair(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static double maximumValue(Pair[] items, int n, int w) {
        // sort by [value/weight] ratio
        Arrays.sort(items,
                (a, b) -> {
                    double r1 = (double) a.value / a.weight;
                    double r2 = (double) b.value / b.weight;
                    if (r1 > r2) {
                        return -1;
                    } else {
                        return 1;
                    }
                });

        double totalval = 0.0;
        for (int i = 0; i < n && w > 0; i++) {
            if (items[i].weight <= w) { // we can take full
                w = w - items[i].weight;
                totalval += items[i].value;
            } else { // take partial == w * val/wt. ratio.
                totalval += (w * (double) items[i].value / (double) items[i].weight);
                break;
            }
        }
        return totalval;

    }
}
