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

```
  {
    _id: ObjectId("64165606d4a9053a6d311f7e"),
    title: '1984',
    rating: 6,
    genre: ["fantasy", "horror", "comedy"]
  }, 
  {...},
  {...}
```

Podemos ter três requerimentos para buscas nesse array:

- Buscar os objetos cuja lista contém o parâmetro passado:
  ```bash
  db.books.find({"genre": fantasy}) # Buscará pelos objetos cuja lista "genre" contêm o gênero fantasy (sim, é isso mesmo!)
  ```
  
- Buscar os objetos cuja lista seja **exatamente** igual ao parâmetro:
  ```bash
  db.books.find({"genre": ["fantasy"]}) # Buscará pelos objetos cuja lista "genre" seja EXATAMENTE igual à lista informada dentro dos 
  ```
  
- Buscar objetos cuja lista contenha os parâmetros passados (mais de um parâmetro!):
  ```bash
  db.books.find({"genre": {$all: ["fantasy", "sci-fi"]}}) # Buscará pelos objetos cuja lista "genre" possua PELO MENOS fantasy e sci-fi (ambos!)
  ```

# Referenciando objetos em sub listas

Suponha o seguinte objeto:

```
  {
    _id: ObjectId("64165606d4a9053a6d311f7e"),
    title: '1984',
    rating: 6,
    genre: ["fantasy", "horror", "comedy"],
    reviews: [
      {
        "name": "Alex",
        "rating": 10
      }
    ]
  }
```

Podemos buscar por todos os livros que o Alex avaliou:

```bash
db.books.find({"reviews.name": "Alex"})
```

# Deletando objetos

Use `deleteOne` e `deleteMany()`

# Atualizando objetos

Para atualizar **UM** objeto, usar o `updateOne({}, {})`, que recebe dois argumentos: 
- O primeiro sendo as informações dos objetos a serem atualizados
- O segundo sendo os campos a serem atualizados, com o **$set**

```bash
db.books.update({"_id": ObjectId("64165606d4a9053a6d311f7e")}, {$set: {"rating": 10, "title": "1984 V2"}}) # Atualizará o objeto com o ID informado, setando o rating para 10 e o title para "1984 V2"
```

Para atualizar **MÚLTIPLOS** objetos, usar o `updateMany({}, {})`:

```bash
db.books.update({"author": "Alex"}, {$set: {"author": "Alex Rodrigues"}}) # Atualizará o campo author para "Alex Rodrigues" em todos os objetos cujo campo author == Alex

# Set e Inc

Ao atualizar o objeto, podemos usar o `$set` para setar valores e o `$inc` para incrementar valores

```bash
db.books.updateOne({"_id": ObjectId("64165606d4a9053a6d311f7e")}, {$set: {"rating": 10}}) # Setará o rating do objeto para 10
db.books.updateOne({"_id": ObjectId("64164f4fd4a9053a6d311f7c")}, {$inc: {"rating": +3}}) # Incrementará o rating do objeto em 3
```
