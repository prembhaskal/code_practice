package codeforces.task.m167div2.taskb;

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

        int tests = in.nextInt();

        for (int i = 0; i < tests; i++) {
            String str = in.next();
            String seq = in.next();
            out.println(minLength(str, seq));
        }
    }

    // approach - break 'seq' into 3 parts, front, middle, back. Each of which could be empty too.
    // fix the length of front and then determine, length of the remaining front + middle by matching
//    characters from 'str'.
    int minLength(String str, String seq) {
        int minLength = str.length() + seq.length();
        // append <seq[0:i]> <str> <seq[i:n]>
        for (int i = -1; i < seq.length(); i++) {
            int strp = 0;
            int seqp = i + 1;
            for (; strp < str.length(); strp++) {
                if (seqp >= seq.length()) {
                    break;
                }
                if (seq.charAt(seqp) == str.charAt(strp)) {
                    seqp++;
                }
            }

            int currLength = (i + 1); // char on left of str
            currLength += (str.length()); // substring itself
            int remaining = seq.length() - 1 - seqp + 1;
            if (remaining > 0) {
                currLength += remaining;
            }
            minLength = Math.min(minLength, currLength);
        }

        return minLength;
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
