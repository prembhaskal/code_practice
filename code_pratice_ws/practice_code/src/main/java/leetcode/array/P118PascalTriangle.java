package array;

import java.util.List;
import java.util.ArrayList;

public class P118PascalTriangle {
	 public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pt = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        pt.add(first);

        for (int i = 1; i < numRows; i++) {
            int rowlen = i + 1;
            List<Integer> prev = pt.get(i-1);
            
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int r = 1; r < rowlen-1; r++) {
                row.add(prev.get(r-1) + prev.get(r));
            }

            row.add(1);

            pt.add(row);
        }

        return pt;
    }
}