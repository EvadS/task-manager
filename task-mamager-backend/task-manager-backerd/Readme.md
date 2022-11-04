# Spring GQL demo

Used annotations
  * @SchemaMapping
  * @Argument
  * @QueryMapping

## uses instructions

Navigate to http://localhost:8080/graphiql or your custom URL.

query 
```json
query {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```