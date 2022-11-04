# Spring GQL demo

Used annotations
  * @SchemaMapping -> maps the handler method to a field with the same name in the schema
  * @Argument
  * @QueryMapping


The “!” at the end of some names indicates that it's a non-nullable type


## uses instructions

Navigate to or your GQL URL.

```
http://localhost:8080/graphiql 
```
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