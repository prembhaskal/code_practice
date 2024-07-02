package codeforces.task.m167div2.taska;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] s) {
        try {
            InputStream inputStream = System.in;
            InputReader in = new InputReader(inputStream);
            PrintWriter writer = new PrintWriter(System.out);

            Task solution = new Task();
            solution.solve(in, writer);

            writer.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Task {

    public void solve(InputReader in, PrintWriter out) throws IOException {

        int coins = in.nextInt();

        for (int i = 0; i < coins; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            // we can catch anything in positive y or x-axis
            // anything at 1 distance can be caught, (0,-1)
            // + anything in -ve 'y',
            //  if below 45 degrees, we cannot catch
            // if abs(abs(x)-abs(y)) -> hops to reach 45deg line, abs(x)-1 hops to catch.
            if (y > 0 ) {
                out.println("YES");
            } else {
                int xabs = abs(x);
                int yabs = abs(y);
                if (yabs == 1) {
                    out.println("YES");
                } else if (yabs > xabs) {
                    out.println("NO");
                } else {
                    int hopToReach45Degree = abs(xabs - yabs);
                    int hopToCatch = abs(xabs - 1);
                    if (hopToCatch<= hopToReach45Degree) {
                        out.println("YES");
                    } else {
                        out.println("NO");
                    }
                }
            }
        }

    }

    int abs(int x) {
        if (x < 0) {
            return -x;
        }
        return x;
    }


}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

}
