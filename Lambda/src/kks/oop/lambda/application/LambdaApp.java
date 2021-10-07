package kks.oop.lambda.application;

import kks.oop.lambda.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * При помощи лямбда-функций:
 * А) получить список уникальных имен
 * Б) вывести список уникальных имен в формате:
 * Имена: Иван, Сергей, Петр.
 * В) получить список людей младше 18, посчитать для них средний
 * возраст
 * Г) при помощи группировки получить Map, в котором ключи –
 * имена, а значения – средний возраст
 * Д) получить людей, возраст которых от 20 до 45, вывести в консоль
 * их имена в порядке убывания возраста
 */

public class LambdaApp {
    public static void main(String[] args) {
        List<Person> personsList = getPersonsList();

        System.out.println("Persons list: ");

        printList(personsList);

        List<String> uniqueNamesList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        String uniqueNamesString = uniqueNamesList.stream()
                .collect(Collectors.joining("; ", "Unique names: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> personsListFilteredByAge = personsList.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());

        double avgAge = personsListFilteredByAge.stream()
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println("Persons younger 18 age: " + personsListFilteredByAge + ", average age = " + avgAge);

        Map<String, Double> nameAverageAgeMap = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Map<Name, averageAge>: " + nameAverageAgeMap);

        List<Person> personsListFilteredByAgeRange = personsList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .collect(Collectors.toList());

        String names = personsListFilteredByAgeRange.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Peoples who has age less then 45 and old then 20: " + names);
    }

    private static List<Person> getPersonsList() {
        return Arrays.asList(
                new Person("Варвара", 27),
                new Person("Борис", 45),
                new Person("Мартин", 17),
                new Person("Сурген", 24),
                new Person("Никифор", 11),
                new Person("Борис", 52),
                new Person("Екатерина", 67),
                new Person("Екатерина", 5),
                new Person("Никифор", 76)
        );
    }

    private static void printList(List<Person> list) {
        for (Person p : list) {
            System.out.println(p);
        }
    }
}
