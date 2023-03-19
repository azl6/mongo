# Inserindo objetos

**insertOne:** Insere um objeto em uma collection

```bash
db.books.insertOne({"title": "The Lord of the Rings", "author": "Tolkien", rating: 10})
```

**insertMany:** Insere muitos objetos em uma collection

```bash
db.books.insertMany([{...}, {...}, {...}])
```


# Buscando objetos básico

**Funções auxiliares:** Os métodos abaixo podem ser usados juntamente ao find:

Exemplos:

```bash
db.books.find().count # Conta o número de retornos
db.books.find().limit(3) # Limita a 3 retornos
db.books.find().sort("author": 1) # 1 ASC, 0 DESC. -1 Inverte a ordem! 
```

**find:** Encontra objetos

```bash
db.books.find() # Retornará todos os objetos
```

Alternativamente, podemos passar argumentos para a busca:

```bash
db.books.find({"author": "Tolkien", "rating": 10})
```

Podemos também especificar quais campos queremos que seja retornado, 1 representando **RETORNE**:

```bash
db.books.find({"rating": 10}, {"title": 1, "author": 1}) # Retornará os campos title e author
```

**findOne:** Busca um objeto

```bash
db.books.findOne({"_id": "ObjectId(2423543r432ct6vvv3)"})
```

# Buscando objetos com operadores

Operador **AND**:

A "," nos exemplos abaixo já é o suficiente para representar um **AND**.

Podemos buscar objetos com um campo:
- Maior que: **gt**
- Maior ou igual a: **gte**
- Menor que: **lt**
- Menor ou igual a: **lte** 

```bash
db.books.find({"rating": {$lt 5}}) # Encontra livros cujo rating é menor que 7
db.books.find({"rating": {$gte 8}, "author": "Tolkien"}) # Encontra livros cujo rating é >= 8 AND do autor Tolkien
```

Operador **OR**:

Para usarmos o operador **OR**, iniciamos a query com **$or: [{...}, {...}]**

```bash
db.books.find({$or: [{"rating": 6}, {"rating": 10}]}) # Encontrará livros cujo rating seja 6 ou 10
db.books.find({$or: [{"rating": {$lte: 4}},{"rating": {$gte: 8}}]}) # Encontrará livros cujo rating seja <= 4 ou >= 8
```

Operador **IN**:

Para buscarmos objetos cujos atributos satisfazem a condição passada

```bash
db.books.find({"rating": {$in: [6,7,8]}}) # Encontrará livros cujo rating seja 7,8 ou 9
```

# Buscando objetos dentro de listas

Suponha o seguinte objeto:

```json
  {
    _id: ObjectId("64165606d4a9053a6d311f7e"),
    title: '1984',
    rating: 6,
    genre: ["fantasy", "horror", "comedy"]
  }, 
  {...},
  {...}
```

Podemos ter dois requerimentos para buscas nesse array:

- Buscar os objetos cuja lista contém o parâmetro passado:
  ```bash
  db.books.find({"genre": fantasy}) # Buscará pelos objetos cuja lista "genre" contêm o gênero fantasy (sim, é isso mesmo!)
  ```
  
- Buscar os objetos cuja lista seja **exatamente** igual ao parâmetro:
  ```bash
  db.books.find({"genre": ["fantasy"]}) # Buscará pelos objetos cuja lista "genre" seja EXATAMENTE igual à lista informada dentro dos 
  ```
