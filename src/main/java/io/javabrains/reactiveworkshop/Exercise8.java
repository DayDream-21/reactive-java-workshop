package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise8 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        /*ReactiveSources.intNumbersFluxWithException()
                .subscribe(
                        num -> System.out.print(num + " "),
                        throwable -> System.out.print("\nError 1")
                );

        ReactiveSources.intNumbersFluxWithException()
                .doOnError(throwable -> System.out.print("\nError 2"))
                .subscribe(num -> System.out.print(num + " "));*/

        // Print values from intNumbersFluxWithException and continue on errors
        /*ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((e, item) -> System.out.println("Error!!! " + e.getMessage()))
                .subscribe(num -> System.out.print(num + " "));*/

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(err -> Flux.just(-1, -2))
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_COMPLETE) {
                        System.out.println("Done");
                    } else {
                        System.out.println("Done with error");
                    }
                })
                .subscribe(num -> System.out.print(num + " "));

        System.out.println("Press a key to end");
        System.in.read();
    }
}
