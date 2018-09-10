package hackerearth;

public class RescueOps {

    public static String solve(int N, String s) {
        char[] arr = s.toCharArray();

        // find final position.
        int x = 0;
        int y = 0;

        int dir = 0;

        for (char ch : arr) {
            switch (ch) {
                case 'M':
                    if (dir == 0) {
                        y++;
                    } else if (dir == 1) {
                        x++;
                    } else if (dir == 2) {
                        y--;
                    } else if (dir == 3) {
                        x--;
                    } else {
                        throw new RuntimeException("should not happen dir value is " + dir);
                    }
                    break;
                case 'L':
                    dir = (dir + 3) % 4;
                    break;
                case 'R':
                    dir = (dir + 1) % 4;
                    break;
            }
        }

//        System.out.println("final destination is x,y: " +  x + "," + y);A

        char ch1;
        char ch2;
        if (x > 0 && y >= 0) { // 1st quad and y axis
            ch1 = '1';
            ch2 = x > y ? '6' : '5';
        }
        else if (x >=0 && y < 0) { // 2nd quad and x axis
            ch1 = '2';
            ch2 = x > -y ? '6' : '7';
        }
        else if (x < 0 && y <= 0) { // 3rd quad and y axis
            ch1 = '3';
            ch2 = -x > -y ? '8' : '7';
        }
        else { // 4th quad and x axis
            ch1 = '4';
            ch2 = -x > y ? '8' : '5';
        }

        // abs
        x = (x < 0) ? -x : x;
        y = (y < 0) ? -y : y;

        // diagonal moves
        int diag = (x <= y) ? x : y;
        int stt = x - y;
        stt = (stt < 0) ? -stt : stt;

        char[] direct = new char[diag + stt + 1];
        for (int i = 0; i < diag; i++) {
            direct[i] = ch1;
        }
        for (int i = diag; i < diag + stt; i++) {
            direct[i] = ch2;
        }
        direct[diag + stt] = '0';
        return new String(direct);
    }


}
