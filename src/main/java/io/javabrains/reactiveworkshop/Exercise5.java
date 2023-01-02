package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {
    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        /*ReactiveSources.intNumbersFlux().subscribe(
                value -> System.out.println(value),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Complete nums"));*/

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.userFlux().subscribe(new BaseSubscriber<>() {
            int count = 0;

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("User observer subscribed");
                request(1);
            }

            @Override
            protected void hookOnNext(User user) {
                count++;
                System.out.println(user.getFirstName());
                if (count < 3) {
                    request(1);
                } else {
                    hookOnComplete();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Complete users");
            }
        });

        System.out.println("Press a key to end");
        System.in.read();
    }
}