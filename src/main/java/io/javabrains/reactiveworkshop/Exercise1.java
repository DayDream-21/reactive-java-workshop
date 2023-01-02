package io.javabrains.reactiveworkshop;

public class Exercise1 {
    public static void main(String[] args) {
        // Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream().forEach(num -> System.out.print(num + " "));
        System.out.println();

        // Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream().filter(num -> num < 5).forEach(num -> System.out.print(num + " "));
        System.out.println();

        // Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources.intNumbersStream()
                .filter(num -> num > 5)
                .limit(3)
                .skip(1)
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        StreamSources.intNumbersStream()
                .filter(num -> num > 5)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("-1"));

        // Print first names of all users in userStream
        StreamSources.userStream()
                .map(User::getFirstName)
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // Print first names in userStream for users that have IDs from number stream
        // 1 solution
        StreamSources.intNumbersStream()
                .forEach(id -> StreamSources.userStream()
                        .filter(user -> user.getId() == id)
                        .map(User::getFirstName)
                        .forEach(name -> System.out.print(name + " ")));
        System.out.println();
        // 2 solution
        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(id -> id == user.getId()))
                .forEach(user -> System.out.print(user.getFirstName() + " "));
        System.out.println();
        // 3 solution
        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(user -> System.out.print(user + " "));
    }
}