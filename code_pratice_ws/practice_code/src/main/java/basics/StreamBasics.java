package basics;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StreamBasics {
    void runStreamExamples() {
        Sys.printf("\nrunning stream examples\n");
        convertStreamToMap();
        Sys.printf("\ncompleted stream examples\n");
    }

    void convertStreamToMap() {
        Sys.println("************ stream to map **************");
        // collect data to map, does not handle duplicates
        Sys.println("\n normal stream to map");
        Arrays.asList("Prem", "kumar", "ish", "punnda")
                .stream()
                .collect(Collectors.toMap(str -> str.length(), str -> str))
                .forEach((k, v) -> Sys.printf("key: %s, value: %s\n", k, v));

        Sys.println("\nhandling duplicates with merge function");
        // handle duplicates using merge function
        Arrays.asList("prem", "kumar", "ishu", "panda")
                .stream()
                .collect(Collectors.toMap(str -> str.length(), str -> str, (k1, k2) -> k2))
                .forEach((k, v) -> Sys.printf("key: %s, value: %s\n", k, v));

    }
}
