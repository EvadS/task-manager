package com.se.manager.taskmanager.intro;

import com.se.manager.taskmanager.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonDao {

    private final List<Person> persons;

    public PersonDao(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons(int count, int offset) {
        return persons.stream()
                .skip(offset)
                .limit(count)
                .collect(Collectors.toList());
    }


    public List<Person> getPersonByName(String name) {
        return persons.stream()
                .filter(p -> name.equals(p.getName()))
                .collect(Collectors.toList());
    }

    public List<Person> getGendersPerson(String gender) {
        return persons.stream()
                .filter(p -> gender.equals(p.getGender()))
                .collect(Collectors.toList());
    }

    public List<Person> getByAge(Integer from, Integer to) {

        if ((Optional.ofNullable(from).orElse(0) != 0) &&
                (Optional.ofNullable(to).orElse(0) != 0)) {

            return persons.stream()
                    .filter(p -> p.getAge() >= from && p.getAge() <= to)
                    .collect(Collectors.toList());
        } else if ((Optional.ofNullable(from).orElse(0) != 0)) {
            return persons.stream()
                    .filter(p -> p.getAge() >= from)
                    .collect(Collectors.toList());

        } else if ((Optional.ofNullable(to).orElse(0) != 0)) {
            return persons.stream()
                    .filter(p -> p.getAge() <= to)
                    .collect(Collectors.toList());
        } else {
            return persons.stream()
                    .collect(Collectors.toList());
        }
    }

    public void savePerson(Person person){
        persons.add(person);
    }
}