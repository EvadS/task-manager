package com.se.manager.taskmanager.bookDetails;

import com.se.manager.taskmanager.model.Author;
import com.se.manager.taskmanager.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    /**
     * bind this method to a gql query, a field under the Query type.
     * @param  @Argument annotation to have an argument bound to a target
     *            object and injected into the handler method.
     * @return
     */
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    /**
     * @SchemaMapping annotation maps a handler method to a field in the GraphQL schema and declares it to be the DataFetcher
     * @param book
     * @return
     */
    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}