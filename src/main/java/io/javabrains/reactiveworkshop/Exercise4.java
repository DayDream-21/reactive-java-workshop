package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.Optional;

public class Exercise4 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(System.out::println);

        // Get the value from the Mono into an integer variable
        Optional<Integer> integer = ReactiveSources
                .intNumberMono()
                .blockOptional();
        System.out.println("From block " + integer.orElse(-1));

        System.out.println("Press a key to end");
        System.in.read();
    }
}
