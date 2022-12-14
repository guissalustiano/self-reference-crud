# Clousure tables with Hibernates
Implemenes clousure tables to handle self-referencing relationships

## Ideia
- Use functional as mutch as possible
- Service as verbs
- A lot of types

## Endpoints

### `GET /v1/categories`
List categories
#### Response schema
```
[
      {
            parentId: number?, # null if is root
            id: number,
            code: string,
            name: string,
            description: string?,
            depth: string,
            slug: string,
            createdAt: string, # ISO 8601
            updatedAt: string, # ISO 8601
      }
]
```

### `GET /v1/categories/:id`

```
{
    parentId: number?, # null if is root
    id: number,
    code: string,
    name: string,
    description: string?,
    depth: string,
    slug: string,
    child: [
        {
            parentId: number?, # null if is root
            id: number,
            code: string,
            name: string,
            description: string?,
            depth: string,
            slug: string,
            createdAt: string, # ISO 8601
            updatedAt: string, # ISO 8601
        }
    ],
    createdAt: string, # ISO 8601
    updatedAt: string, # ISO 8601
}
```

### `POST /v1/categories`

```
{
    parentId: number?, # null if is root
    id: number,
    code: string,
    name: string,
    description: string?,
    depth: number,
    slug: string,
    createdAt: string, # ISO 8601
    updatedAt: string, # ISO 8601
}
```


### `DELETE /v1/categories/:id`


### `GET /v1/products`
```
[
      {
            categoryId: number,
            id: number,
            code: string,
            name: string,
            description: string?,
            slug: string,
            createdAt: string, # ISO 8601
            updatedAt: string, # ISO 8601
      }
]
```


### `GET /v1/products`
```
{
    categoryId: number,
    id: number,
    code: string,
    name: string,
    description: string?,
    slug: string,
    createdAt: string, # ISO 8601
    updatedAt: string, # ISO 8601
}
```

## TODO
- [ ] Error handling
- [ ] Update endpoints (PATCH?)
- [ ] Product complex filters (with Criteria API maybe)
