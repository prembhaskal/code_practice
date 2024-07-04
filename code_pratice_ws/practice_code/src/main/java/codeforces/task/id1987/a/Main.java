package codeforces.task.id1987.a;

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

        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            int ans = 1 + (n - 1) * k;
            out.println(ans);
        }
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
