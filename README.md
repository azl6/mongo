# Inserindo objetos

**insertOne:** Insere um objeto em uma collection

```bash
db.books.insertOne({"title": "The Lord of the Rings", "author": "Tolkien", rating: 10})
```

**insertMany:** Insere muitos objetos em uma collection

```bash
db.books.insertMany([{...}, {...}, {...}])
```


# Buscando objetos

**Funções uxiliares:** Os métodos abaixo podem ser usados juntamente ao find:

exemplos:

```
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