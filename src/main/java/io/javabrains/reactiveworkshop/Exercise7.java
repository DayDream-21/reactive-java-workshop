package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise7 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Print all values from intNumbersFlux that's greater than 5
        /*ReactiveSources.intNumbersFlux()
                .filter(integer -> integer > 5)
                .subscribe(integer -> System.out.print(integer + " "));*/

        // Print each value multiplied by 10 from intNumbersFlux that's greater than 5
        /*ReactiveSources.intNumbersFlux()
                .filter(integer -> integer > 5)
                .map(integer -> integer * 10)
                .subscribe(integer -> System.out.print(integer + " "));*/

        // Print 10 times each value from intNumbersFlux that's greater than 5
        /*ReactiveSources.intNumbersFlux()
                .filter(integer -> integer > 5)
                .flatMap(integer -> Flux.range(1, 10).map(j -> integer))
                .subscribe(integer -> System.out.print(integer + " "));*/

        // Print each value multiplied by 10 from intNumbersFlux for the first 3
        // numbers emitted that's greater than 5
        /*ReactiveSources.intNumbersFlux()
                .filter(integer -> integer > 5)
                .map(integer -> integer * 10)
                .take(3)
                .subscribe(integer -> System.out.print(integer + " "));*/

        // Print each value from intNumbersFlux that's greater than 20. Print -1
        // if no elements are found
        /*ReactiveSources.intNumbersFlux()
                .filter(integer -> integer > 20)
                .defaultIfEmpty(-1)
                .subscribe(integer -> System.out.print(integer + " "));*/

        // Switch ints from intNumbersFlux to the right user from userFlux
        /*ReactiveSources.intNumbersFlux()
                .flatMap(id -> ReactiveSources.userFlux().filter(user -> user.getId() == id))
                .subscribe(user -> System.out.println(user));*/

        // Print only distinct numbers from intNumbersFluxWithRepeat
        /*ReactiveSources.intNumbersFluxWithRepeat()
                .distinct()
                .log()
                .subscribe();*/

        // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
        ReactiveSources.intNumbersFluxWithRepeat()
                .distinctUntilChanged()
                .log()
                .subscribe();

        System.out.println("Press a key to end");
        System.in.read();
    }
}
