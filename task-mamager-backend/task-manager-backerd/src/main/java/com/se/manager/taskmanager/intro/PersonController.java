package com.se.manager.taskmanager.intro;


import com.se.manager.taskmanager.model.Person;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PersonController {


    private final PersonDao personDao;


    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @QueryMapping()
    public List<Person> recentPerson(@Argument Integer from, @Argument Integer to) {
        return personDao.getByAge(from, to);
    }


    @MutationMapping
    public Person createPerson(@Argument String name, @Argument Integer age,
                               @Argument String language, @Argument String city,
                               @Argument String gender) {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName(name);
        person.setAge(age);
        person.setLanguage(language);
        person.setCity(city);
        person.setGender(gender);
        personDao.savePerson(person);

        return person;
    }
}
