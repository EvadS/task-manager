package com.se.manager.taskmanager.into;


import com.se.manager.taskmanager.intro.GraphqlConfiguration;
import com.se.manager.taskmanager.intro.PersonController;
import com.se.manager.taskmanager.intro.PostController;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@GraphQlTest(PersonController.class)
@Import(GraphqlConfiguration.class)
public class PersonControllerIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @Order(1)
    void givenPerson_whenExecuteQueryForRecentPerson_thenReturnResponse() {
        String documentName = "recent_person";

        graphQlTester.documentName(documentName)
                .variable("from", 31)
                .variable("to", 34)
                .execute()
                .path("$")
                .matchesJson(expected(documentName));
    }

    // only from
    @Test
    @Order(2)
    void givenPerson_whenExecuteQueryForRecentPersonOnlyFromParam_thenReturnResponse() {
        String documentName = "recent_person";

        graphQlTester.documentName(documentName)
                .variable("from", 38)
                .execute()
                .path("$")
                .matchesJson(expected_from(documentName));
    }

    // only too
    @Test
    @Order(3)
    void givenPerson_whenExecuteQueryForRecentPersonOnlyToParam_thenReturnResponse() {
        String documentName = "recent_person";

        graphQlTester.documentName(documentName)
                .variable("to", 32)
                .execute()
                .path("$")
                .matchesJson(expected_to(documentName));
    }


    // mutation test
//    @Test
//    @Order(4)
//    void givenNewPostData_whenExecuteMutation_thenNewPostCreated() {
//        String documentName = "create_person";
//
//        graphQlTester.documentName(documentName)
//                .variable("name", "New person")
//                .variable("age", "41")
//                .variable("language", "German")
//                .variable("city", "City")
//                .variable("gender", "Male")
//                .execute()
//                //  .path("createPerson.id").hasValue()
//                .path("createPerson.name").entity(String.class).isEqualTo("New person")
//                .path("createPerson.age").entity(Integer.class).isEqualTo(41)
//                .path("createPerson.gender").entity(String.class).isEqualTo("Male")
//        ;
//    }

    @SneakyThrows
    public static String expected(String fileName) {
        Path path = Paths.get("src/test/resources/graphql-test/" + fileName + "_expected_response.json");
        return new String(Files.readAllBytes(path));
    }

    @SneakyThrows
    public static String expected_to(String fileName) {
        Path path = Paths.get("src/test/resources/graphql-test/" + fileName + "_to_expected_response.json");
        return new String(Files.readAllBytes(path));
    }

    @SneakyThrows
    public static String expected_from(String fileName) {
        Path path = Paths.get("src/test/resources/graphql-test/" + fileName + "_from_expected_response.json");
        return new String(Files.readAllBytes(path));
    }


}
