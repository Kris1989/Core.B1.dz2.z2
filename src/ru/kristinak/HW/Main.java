package ru.kristinak.HW;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minors = persons.stream()
                .filter(person -> person.getAge() > 18)
                .count();

        System.out.println("1. Количество несовершеннолетних: " + minors + ".");

        List<String> conscripts = persons.stream()
                .filter(man -> man.getSex() == Sex.MAN)
                .filter(man -> man.getAge() > 17)
                .filter(man -> man.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("2. Список призывников: " + conscripts);

        List<Person> potentialEmployees = persons.stream()
                .filter(worker -> worker.getEducation() == Education.HIGHER)
                .filter(worker -> worker.getAge() > 17)
                .filter(worker -> worker.getSex() == Sex.MAN ? worker.getAge() <= 65 : worker.getAge() <= 60)
                .sorted(Comparator.comparing(worker -> worker.getFamily()))
                .collect(Collectors.toList());

        System.out.println("3. Список работоспособных людей: " + potentialEmployees);
    }
}
