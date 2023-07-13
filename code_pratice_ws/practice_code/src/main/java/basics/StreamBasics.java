package basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static basics.Sys.println;
import static basics.Sys.printf;

public class StreamBasics {
    void runStreamExamples() {
        printf("\nrunning stream examples\n");
        convertStreamToMap();
        MapAndFilter1();
        printf("\ncompleted stream examples\n");
    }

    void convertStreamToMap() {
        println("************ stream to map **************");
        // collect data to map, does not handle duplicates
        println("\n normal stream to map");
        Arrays.asList("Prem", "kumar", "ish", "punnda")
                .stream()
                .collect(Collectors.toMap(str -> str.length(), str -> str))
                .forEach((k, v) -> printf("key: %s, value: %s\n", k, v));

        println("\nhandling duplicates with merge function");
        // handle duplicates using merge function
        Arrays.asList("prem", "kumar", "ishu", "panda")
                .stream()
                .collect(Collectors.toMap(str -> str.length(), str -> str, (k1, k2) -> k2))
                .forEach((k, v) -> printf("key: %s, value: %s\n", k, v));

    }

    void MapAndFilter1() {
        println("\n************ Map And Filter 1 ************** ");
        library().stream()
                .map(book -> book.getAuthor())
                .filter(author -> author.getAge() > 25)
                .distinct()
                .limit(10)
                .map(Author::getName) // method reference used instead of writing out the lambda.
                .map(String::toUpperCase)
                .collect(Collectors.toList())
                .forEach(name -> printf("author: %s\n", name));
    }

    List<Book> library() {
        return Arrays.asList(
                new BookBuilder().withDetails("harry potter", "Young Adults", "J.K. Rowling", 50, Gender.FEMALE).get(),
                new BookBuilder().withDetails("algorithms I", "comp. science", "Donald Knuth", 50, Gender.MALE).get(),
                new BookBuilder().withDetails("war of world", "fiction", "H.G. Wells", 24, Gender.MALE).get(),
                new BookBuilder().withDetails("algorithms II", "comp. science", "Donald Knuth", 50, Gender.MALE).get()
        );
    }
}
