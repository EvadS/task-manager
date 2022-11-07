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



### Remark 3
```java
mutation
  {
  createPost(title: "New Title", authorId: "Author2", text: "New Text")
    { id
      category
      author {   
        id
        name      
}
}}
```

or 
```java
mutation createPost ($title: String!, $text: String!, $category: String, $authorId: String!) {
    createPost (title: $title, text: $text, category: $category, authorId: $authorId) {
        id
        title
        text
        category
    }
}
```


### Remark 3
generarte docs
```bash
  npm install -g @2fd/graphdoc
```

```bash
  graphdoc -e http://localhost:8080/graphql -o ./doc/schema
```

### Create person mutation

```post
http://localhost:8080/graphql
```

```body
mutation createPerson ($name: String!, $age: Int!, $language: String, $city: String!,$gender: String!) {
    createPerson (name: $name, age:$age, language: $language, city: $city, gender:$gender) {
        name
        age
        language
        city
        gender

    }
}
```

```variables
{
  "name": "new post",
  "age": 30,
  "language": "category",
  "city": "Author0",
  "gender":"Male"
}
```

