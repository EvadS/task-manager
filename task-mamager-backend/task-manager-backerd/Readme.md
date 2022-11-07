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

```
    query {
        recentPosts(count: 10, offset: 10) {
        id
        title
        text
        }
    }
```


### Mutation
curl \
--request POST 'localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n    createPost(title: \"New Title\", authorId: \"Author2\", text: \"New Text\") {\n id\n       category\n        author {\n            id\n            name\n        }\n    }\n}"}'

## Remarks
#### Remark1 

query name from resource must be the same as function name mapped by @QueryMapping annotations

For Example: query from resources 
```java
 recentPosts(count: Int, offset: Int): [Post]!
```
method
```java
 @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset){
        ...
    }
```

### Remark 2
method must have parameters annotated with @Argument that correspond to the corresponding parameters in the schema.



