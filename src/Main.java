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

        long stream1 = persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + stream1);

        List<String> stream2 = persons.stream()
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 27)
                .filter(Person -> Person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывники: " + stream2);

        List<Person> stream3 = persons.stream()
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 60 && Person.getSex() == Sex.WOMAN && Person.getEducation() == Education.HIGHER)
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 65 && Person.getSex() == Sex.MAN && Person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person -> Person.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Трудоспособные: " + stream3);

    }
}
