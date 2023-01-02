package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Exercise6 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
        String response = ReactiveSources
                .unresponsiveMono()
                .timeout(Duration.of(5, ChronoUnit.SECONDS))
                .onErrorResume(TimeoutException.class, e -> Mono.just("Error"))
                .block();
        System.out.println(response);

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        List<String> list = new ArrayList<>();
        ReactiveSources.unresponsiveFlux()
                .timeout(Duration.ofSeconds(5))
                .map(s -> list.add(s))
                .subscribe();

        System.out.println("Press a key to end");
        System.in.read();
    }
}
