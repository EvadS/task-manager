package com.se.manager.taskmanager.intro;

import com.se.manager.taskmanager.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class GraphqlConfiguration {

    @Bean
    public PersonDao personDao() {
        List<Person> persons = new ArrayList<>();
        Random rnd = new Random(10);




        for (int personId =1; personId <= 10; ++personId) {
            Person person = new Person();
            person.setId("Person_" + personId);
            person.setName("Person name" + ":" + personId);
            int randomNum = rnd.nextInt((100 - personId) + personId) + 1;
            person.setAge(30 + personId);
            if (personId % 2 == 0) {
                person.setLanguage("English");
            } else {
                person.setLanguage("French");
            }

            if (personId % 2 == 0) {
                person.setCity("Kiev");
            } else {
                person.setCity("Warshaw");
            }

            if (personId % 2 == 0) {
                person.setGender("Male");
            } else {
                person.setGender("Female");
            }

            persons.add(person);
        }

        return new PersonDao(persons);
    }

    @Bean
    public PostDao postDao() {
        List<Post> posts = new ArrayList<>();
        for (int postId = 0; postId < 10; ++postId) {
            for (int authorId = 0; authorId < 10; ++authorId) {
                Post post = new Post();
                post.setId("Post" + authorId + postId);
                post.setTitle("Post " + authorId + ":" + postId);
                post.setCategory("Post category");
                post.setText("Post " + postId + " + by author " + authorId);
                post.setAuthorId("Author" + authorId);
                posts.add(post);
            }
        }
        return new PostDao(posts);
    }

    @Bean
    public AuthorDao authorDao() {
        List<Author> authors = new ArrayList<>();
        for (int authorId = 0; authorId < 10; ++authorId) {
            Author author = new Author();
            author.setId("Author" + authorId);
            author.setName("Author " + authorId);
            author.setThumbnail("http://example.com/authors/" + authorId);
            authors.add(author);
        }
        return new AuthorDao(authors);
    }
}
