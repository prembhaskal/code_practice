package leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class P973KClosestPoints {

    public static void main(String[] args) {
        P973KClosestPoints clazz = new P973KClosestPoints();
        int[][] points = new int[][]{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}};
        int k = 6;
        int[][] ans = clazz.kClosest1(points, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (a, b) -> {
            int dista = (a[0] * a[0]) + (a[1] * a[1]);
            int distb = (b[0] * b[0]) + (b[1] * b[1]);
            return Integer.compare(dista, distb);
        });

        for (int[] point : points) {
            queue.add(point);
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }

        return ans;
    }

    public int[][] kClosest1(int[][] points, int k) {
        // do k select
//        kSelect(points, 0, points.length - 1, k);
        kSelect1(points, k);
        // after kselect, all elements lower than k, will be at index less than k.
        // Below operation can be done faster using system array copy.
         Arrays.copyOf(points, k);
        int[][] ans = new int[k][2];
        int j = 0;
        for (int i = 0; i < points.length; i++) {
            ans[j] = points[i];
            j++;
        }
        return ans;
    }


    public void kSelect(int[][] points, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        // random pivot
        int pivot = new Random().nextInt(end - start + 1) + start;
        swap(points, pivot, end);

        int i = start - 1;
        int j = start;
        while (j < end) {
            if (dist(points[j]) < dist(points[end])) {
                i++;
                swap(points, i, j);
            }
            j++;
        }

        int part = i + 1;
        swap(points, part, end);

        if (part == k - 1) {
            return;
        }
        if (part < k - 1) {
            kSelect(points, part + 1, end, k);
        } else {
            kSelect(points, start, part - 1, k);
        }

    }

    void kSelect1(int[][] points, int k) {
        int start = 0;
        int end = points.length - 1;
        while (start < end) {
            int pivot = new Random().nextInt(end - start + 1) + start;
            swap(points, pivot, end);
            int part = partition(points, start, end);
            if (part == k - 1) {
                return;
            }
            if (part < k - 1) {
                start = part + 1;
            } else {
                end = part - 1;
            }
        }
    }

    int partition(int[][] points, int start, int end) {
        // pivot at end
        int i = start - 1;
        int j = start;
        while (j < end) {
            if (dist(points[j]) <= dist(points[end])) {
                i++;
                swap(points, i, j);
            }
            j++;
        }
        swap(points, i + 1, end);
        return i + 1;
    }

    int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
}
