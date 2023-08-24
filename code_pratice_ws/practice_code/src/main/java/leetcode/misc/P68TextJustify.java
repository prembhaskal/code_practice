package leetcode.misc;

import java.util.ArrayList;
import java.util.List;

public class P68TextJustify {
    public static void main(String[] args) {
        var sol = new P68TextJustify();

        // q1
        var words = new String[]{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        int width = 16;
        var ans = sol.fullJustify(words, width);
        System.out.printf("answer: %s\n", ans);
        for (var line : ans) {
            System.out.printf("-->" + line + "<--\n");
        }
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        var ans = new ArrayList<String>();

        int wordsCount = 0;
        int wordSize = 0;
        int lastWord = -1;
        for (int i = 0; i < words.length; i++) {
            wordSize += words[i].length();
            wordsCount++;

            int minWidthNeeded = wordSize + (wordsCount - 1); // -1 because first word does not need space
            if (minWidthNeeded >= maxWidth) {
                if (minWidthNeeded > maxWidth) { // we cannot take current word
                    i--;
                    wordsCount--;
                }
                if (i == words.length - 1) {
                    ans.add(getLineLeftJustified(words, i - wordsCount+1, i, maxWidth));
                } else {
                    ans.add(getLine(words, i - wordsCount + 1, i, maxWidth));
                }
                wordsCount = 0;
                wordSize = 0;
                lastWord = i;
            }
        }
        if (lastWord != words.length - 1) {
            ans.add(getLineLeftJustified(words, words.length - 1 - wordsCount + 1, words.length - 1, maxWidth));
        }

        return ans;
    }

    String getLineLeftJustified(String[] words, int start, int end, int maxwidth) {
        if (start > end) {
            throw new RuntimeException("incorrect start end");
        }

        int wordCount = end - start + 1;
        // find spaces to put.
        int wordSize = getWordLength(words, start, end);
        wordSize += (wordCount - 1); // add space
        int remspace = maxwidth - wordSize;

        var sb = new StringBuilder();
        sb.append(words[start]);
        for (int i = start+1; i <= end; i++) {
            sb.append(" ").append(words[i]);
        }
        for (int i = 0 ; i < remspace; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    int getWordLength(String[] words, int start, int end) {
        int wordSize = 0;
        for (int i = start; i <= end; i++) {
            wordSize += words[i].length();
        }
        return wordSize;
    }

    String getLine(String[] words, int start, int end, int maxwidth) {
        if (start > end) {
            throw new RuntimeException("incorrect start end");
        }
        if (start == end) {
            return getLineLeftJustified(words, start, end, maxwidth);
        }

        int wordCount = end - start + 1;
        int totalSpace = maxwidth - getWordLength(words, start, end);
        int totalSlot = wordCount - 1;

        int spaceperSlot = totalSpace / totalSlot;
        if (spaceperSlot <= 0) {
            throw new RuntimeException(String.format("error in space calc start: %d, end: %d, maxwidth: %d", start, end, maxwidth));
        }

        int extraspace = totalSpace % totalSlot;
        var sb = new StringBuilder();
        sb.append(words[start]);
        for (int i = start + 1; i <= end; i++) {
            int space = spaceperSlot;
            if (extraspace > 0) { // spread extra space equally from first to last;
                space += 1;
                extraspace--;
            }
            for (int j = 0; j < space; j++) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }

        return sb.toString();
    }
}
