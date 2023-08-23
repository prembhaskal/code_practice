package leetcode.heap;

import java.util.HashMap;
import java.util.PriorityQueue;

public class P767OrgString {
    public String reorganizeString(String s) {
        return usingPriorityQueue(s);
    }

    // general algo, pick top two frequent characters, choose the one != prev. repeat
    String usingPriorityQueue(String s) {

        var chars = s.toCharArray();
        var freqmap = new HashMap<Character, Integer>();
        for (char ch : chars) {
            var freq = freqmap.get(ch);
            if (freq == null) {
                freq = 0;
                freqmap.put(ch, 0);
            }
            freqmap.put(ch, freq + 1);
        }

        var heap = new PriorityQueue<Pair>(
                (p1, p2) -> {
                    return Integer.compare(p2.freq, p1.freq);
                }
        );

        for (var entry : freqmap.entrySet()) {
            var pair = new Pair();
            pair.ch = entry.getKey();
            pair.freq = entry.getValue();
            heap.offer(pair);
        }

        var sb = new StringBuilder();
        char prev = '0';
        for (int i = 0; i < chars.length; i++) {
            var top = heap.poll();
            Pair currpair;
            if (heap.size() > 0) {
                var second = heap.poll();
                if (top.ch == prev) {
                    currpair = second;
                    heap.offer(top);
                } else {
                    currpair = top;
                    heap.offer(second);
                }
            } else {
                currpair = top;
            }

            // System.out.printf("idx: %d, pair: %c, pairfreq: %d\n", i, currpair.ch, currpair.freq);

            if (currpair.ch == prev) {
                return "";
            }

            currpair.freq--;
            if (currpair.freq > 0) {
                heap.offer(currpair);
            }

            prev = currpair.ch;

            sb.append(currpair.ch);
        }

        return sb.toString();

    }

    class Pair {
        char ch;
        int freq;
    }

    public String reorganizeStringNaive(String s) {
        var chars = s.toCharArray();

        var freqmap = new HashMap<Character, Integer>();
        for (char ch : chars) {
            var freq = freqmap.get(ch);
            if (freq == null) {
                freq = 0;
                freqmap.put(ch, 0);
            }
            freqmap.put(ch, freq + 1);
        }

        // l l l v v o   v
        // l v o v l v

        var sb = new StringBuilder();
        char prev = '0';

        out:
        for (int i = 0; i < chars.length; i++) {
            char[] toptwo = getTopTwo(freqmap);

            // System.out.printf("idx: %d, toptwo: %s\n", i, Arrays.toString(toptwo));
            char curr;

            if (toptwo.length == 0) {
                return "";
            }
            if (toptwo.length == 1) {
                curr = toptwo[0];
            } else {
                char top = toptwo[0];
                char sec = toptwo[1];
                if (prev == top) {
                    curr = sec;
                } else {
                    curr = top;
                }
            }
            if (prev == curr) {
                return "";
            }

            int topfreq = freqmap.get(curr);
            freqmap.put(curr, topfreq - 1);

            prev = curr;
            sb.append(curr);
        }

        return sb.toString();
    }

    char[] getTopTwo(Map<Character, Integer> freqmap) {
        char top = '0';
        int topfreq = 0;
        char second = '0';
        int secfreq = 0;

        for (var entry : freqmap.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            if (freq > topfreq) {
                secfreq = topfreq;
                second = top;
                topfreq = freq;
                top = ch;
            } else if (freq > secfreq) {
                secfreq = freq;
                second = ch;
            }
        }

        if (topfreq == 0) {
            return new char[]{};
        }
        if (secfreq == 0) {
            return new char[]{top};
        }
        return new char[]{top, second};
    }


}
