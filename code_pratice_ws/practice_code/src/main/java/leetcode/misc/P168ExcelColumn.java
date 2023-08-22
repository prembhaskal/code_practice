package leetcode.misc;

public class P168ExcelColumn {
    public String convertToTitle(int columnNumber) {
        var sb = new StringBuilder();
        // take example of Z or ZZ to get the intuition behind -1.
        // mainly this is very similar to decimal to HEX , but there is no way to represent 0 in this system.
        while (columnNumber > 0) {
            int col = (columnNumber - 1) % 26;

            char ch = (char) ('A' + (col));
            // System.out.printf("columnnumber: %d, col is %d, char is %c\n", columnNumber, col , ch);
            sb.append(ch);
            columnNumber = (columnNumber - 1) / 26;
        }

        return sb.reverse().toString();

    }
}
