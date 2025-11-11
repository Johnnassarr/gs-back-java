# Exemplos de JSON para Testar os Endpoints

## 🔐 Autenticação (Não precisa de token)

### 1. Registrar Usuário
**POST** `http://localhost:8080/auth/register`

**Body (ADMIN):**
```json
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

**Body (USER):**
```json
{
  "username": "usuario",
  "email": "usuario@example.com",
  "password": "user123",
  "role": "USER"
}
```

**Resposta esperada:** `200 OK` (sem body)

---

### 2. Login
**POST** `http://localhost:8080/auth/login`

**Body:**
```json
{
  "email": "admin@example.com",
  "password": "admin123"
}
```

**Resposta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**⚠️ IMPORTANTE:** Copie o token retornado para usar nos próximos endpoints!

---

## 📋 Categorias de Sustentabilidade

**⚠️ ATENÇÃO:** 
- GET: Precisa de autenticação (token no header)
- POST/PUT/DELETE: Precisa de autenticação E ser ADMIN
- Adicione o header: `Authorization: Bearer {seu_token}`

### 3. Listar Todas as Categorias
**GET** `http://localhost:8080/categorias`

**Headers:**
```
Authorization: Bearer {seu_token}
```

**Body:** Não precisa

**Resposta esperada:**
```json
[
  {
    "id": 1,
    "nome": "Reciclagem",
    "descricao": "Categoria relacionada a práticas de reciclagem",
    "nivelImpacto": "ALTO"
  }
]
```

---

### 4. Criar Categoria (ADMIN apenas)
**POST** `http://localhost:8080/categorias`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Reciclagem",
  "descricao": "Categoria relacionada a práticas de reciclagem de materiais",
  "nivelImpacto": "ALTO"
}
```

**Outros exemplos de nivelImpacto:** `"MEDIO"` ou `"BAIXO"`

**Resposta esperada:**
```json
{
  "id": 1,
  "nome": "Reciclagem",
  "descricao": "Categoria relacionada a práticas de reciclagem de materiais",
  "nivelImpacto": "ALTO"
}
```

---

### 5. Atualizar Categoria (ADMIN apenas)
**PUT** `http://localhost:8080/categorias/1`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Reciclagem de Plástico",
  "descricao": "Categoria atualizada sobre reciclagem específica de plástico",
  "nivelImpacto": "MEDIO"
}
```

**Resposta esperada:**
```json
{
  "id": 1,
  "nome": "Reciclagem de Plástico",
  "descricao": "Categoria atualizada sobre reciclagem específica de plástico",
  "nivelImpacto": "MEDIO"
}
```

---

### 6. Deletar Categoria (ADMIN apenas)
**DELETE** `http://localhost:8080/categorias/1`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
```

**Body:** Não precisa

**Resposta esperada:** `204 No Content`

---

## ✅ Tarefas

**⚠️ ATENÇÃO:** 
- GET: Precisa de autenticação (token no header)
- POST/PUT/DELETE: Precisa de autenticação E ser ADMIN
- Adicione o header: `Authorization: Bearer {seu_token}`

### 7. Listar Todas as Tarefas
**GET** `http://localhost:8080/tarefas`

**Headers:**
```
Authorization: Bearer {seu_token}
```

**Body:** Não precisa

**Resposta esperada:**
```json
[
  {
    "id": 1,
    "titulo": "Separar lixo reciclável",
    "descricao": "Separar plástico, papel e metal para reciclagem",
    "completado": false,
    "dataCriacao": "2024-01-15",
    "points": 10,
    "categoria": {
      "id": 1,
      "nome": "Reciclagem",
      "descricao": "Categoria relacionada a práticas de reciclagem",
      "nivelImpacto": "ALTO"
    },
    "usuario": {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com",
      "role": "ADMIN"
    }
  }
]
```

---

### 8. Buscar Tarefa por ID
**GET** `http://localhost:8080/tarefas/1`

**Headers:**
```
Authorization: Bearer {seu_token}
```

**Body:** Não precisa

**Resposta esperada:**
```json
{
  "id": 1,
  "titulo": "Separar lixo reciclável",
  "descricao": "Separar plástico, papel e metal para reciclagem",
  "completado": false,
  "dataCriacao": "2024-01-15",
  "points": 10,
  "categoria": {
    "id": 1,
    "nome": "Reciclagem",
    "descricao": "Categoria relacionada a práticas de reciclagem",
    "nivelImpacto": "ALTO"
  },
  "usuario": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "ADMIN"
  }
}
```

---

### 9. Criar Tarefa (ADMIN apenas)
**POST** `http://localhost:8080/tarefas`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
Content-Type: application/json
```

**Body:**
```json
{
  "titulo": "Separar lixo reciclável",
  "descricao": "Separar plástico, papel e metal para reciclagem",
  "completado": false,
  "dataCriacao": "2024-01-15",
  "points": 10,
  "categoriaId": 1,
  "usuarioId": 1
}
```

**Body (tarefa completada):**
```json
{
  "titulo": "Usar sacola reutilizável",
  "descricao": "Evitar usar sacolas plásticas descartáveis",
  "completado": true,
  "dataCriacao": "2024-01-10",
  "points": 15,
  "categoriaId": 1,
  "usuarioId": 1
}
```

**⚠️ IMPORTANTE:** `categoriaId` e `usuarioId` são obrigatórios. Certifique-se de criar uma categoria e um usuário primeiro!

**Resposta esperada:**
```json
{
  "id": 1,
  "titulo": "Separar lixo reciclável",
  "descricao": "Separar plástico, papel e metal para reciclagem",
  "completado": false,
  "dataCriacao": "2024-01-15",
  "points": 10,
  "categoria": {
    "id": 1,
    "nome": "Reciclagem",
    "descricao": "Categoria relacionada a práticas de reciclagem",
    "nivelImpacto": "ALTO"
  },
  "usuario": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "ADMIN"
  }
}
```

---

### 10. Atualizar Tarefa (ADMIN apenas)
**PUT** `http://localhost:8080/tarefas/1`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
Content-Type: application/json
```

**Body:**
```json
{
  "titulo": "Separar lixo reciclável - ATUALIZADO",
  "descricao": "Separar plástico, papel, metal e vidro para reciclagem",
  "completado": true,
  "dataCriacao": "2024-01-15",
  "points": 15,
  "categoriaId": 1,
  "usuarioId": 1
}
```

**Resposta esperada:**
```json
{
  "id": 1,
  "titulo": "Separar lixo reciclável - ATUALIZADO",
  "descricao": "Separar plástico, papel, metal e vidro para reciclagem",
  "completado": true,
  "dataCriacao": "2024-01-15",
  "points": 15,
  "categoria": {
    "id": 1,
    "nome": "Reciclagem",
    "descricao": "Categoria relacionada a práticas de reciclagem",
    "nivelImpacto": "ALTO"
  },
  "usuario": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "ADMIN"
  }
}
```

---

### 11. Deletar Tarefa (ADMIN apenas)
**DELETE** `http://localhost:8080/tarefas/1`

**Headers:**
```
Authorization: Bearer {seu_token_admin}
```

**Body:** Não precisa

**Resposta esperada:** `204 No Content`

---

## 🧪 Ordem Recomendada para Testes

1. **Registrar um usuário ADMIN** (POST /auth/register)
2. **Fazer login** (POST /auth/login) - copiar o token
3. **Criar uma categoria** (POST /categorias) - usar token do ADMIN
4. **Listar categorias** (GET /categorias) - usar token
5. **Criar uma tarefa** (POST /tarefas) - usar token do ADMIN e IDs obtidos
6. **Listar tarefas** (GET /tarefas) - usar token
7. **Buscar tarefa por ID** (GET /tarefas/1) - usar token
8. **Atualizar tarefa** (PUT /tarefas/1) - usar token do ADMIN
9. **Atualizar categoria** (PUT /categorias/1) - usar token do ADMIN
10. **Deletar tarefa** (DELETE /tarefas/1) - usar token do ADMIN
11. **Deletar categoria** (DELETE /categorias/1) - usar token do ADMIN

---

## 📝 Notas Importantes

1. **Token JWT:** Após fazer login, você receberá um token JWT. Use esse token no header `Authorization: Bearer {token}` para acessar endpoints protegidos.

2. **Roles:**
   - `ADMIN`: Pode fazer todas as operações (GET, POST, PUT, DELETE)
   - `USER`: Pode apenas fazer GET (ler dados)

3. **IDs:** Os IDs são gerados automaticamente pelo banco de dados. Use os IDs retornados nas respostas para fazer atualizações e deleções.

4. **Data:** O formato de data é `YYYY-MM-DD` (exemplo: "2024-01-15")

5. **Nível de Impacto:** Valores possíveis são: `"ALTO"`, `"MEDIO"`, `"BAIXO"`

6. **CategoriaId e UsuarioId:** 
   - Na **criação** (POST): São **obrigatórios** - devem ser IDs de categoria e usuário existentes
   - Na **atualização** (PUT): `categoriaId` pode ser `null` (opcional), mas `usuarioId` deve ser fornecido
   - Certifique-se de criar uma categoria e um usuário antes de criar tarefas

---

## 🔧 Ferramentas para Testar

- **Postman**: Importe os exemplos acima
- **Insomnia**: Similar ao Postman
- **curl**: Use no terminal
- **Thunder Client** (VS Code): Extensão para testar APIs
- **httpie**: Ferramenta de linha de comando

### Exemplo com curl:

```bash
# Registrar usuário
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","email":"admin@example.com","password":"admin123","role":"ADMIN"}'

# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"admin123"}'

# Listar tarefas (substitua {token} pelo token recebido)
curl -X GET http://localhost:8080/tarefas \
  -H "Authorization: Bearer {token}"
```

