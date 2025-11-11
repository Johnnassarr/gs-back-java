# 🚀 Guia Rápido de Teste da API

## Passo 1: Registrar um usuário ADMIN

**POST** `http://localhost:8080/auth/register`

```json
{
  "username": "admin",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

---

## Passo 2: Fazer Login

**POST** `http://localhost:8080/auth/login`

```json
{
  "email": "admin@example.com",
  "password": "admin123"
}
```

**Copie o token da resposta!** Você vai precisar dele nos próximos passos.

Exemplo de resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

## Passo 3: Criar uma Categoria

**POST** `http://localhost:8080/categorias`

**Headers:**
```
Authorization: Bearer {cole_o_token_aqui}
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Reciclagem",
  "descricao": "Categoria relacionada a práticas de reciclagem",
  "nivelImpacto": "ALTO"
}
```

**Copie o ID da categoria da resposta!** (geralmente será 1)

---

## Passo 4: Criar uma Tarefa

**POST** `http://localhost:8080/tarefas`

**Headers:**
```
Authorization: Bearer {cole_o_token_aqui}
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

⚠️ **IMPORTANTE:** 
- Use o `categoriaId` que você recebeu no Passo 3
- Use `usuarioId: 1` (o primeiro usuário criado geralmente tem ID 1)

---

## Passo 5: Listar Tarefas

**GET** `http://localhost:8080/tarefas`

**Headers:**
```
Authorization: Bearer {cole_o_token_aqui}
```

**Body:** Não precisa

---

## 📝 Outros Exemplos Úteis

### Listar Categorias
**GET** `http://localhost:8080/categorias`
- Headers: `Authorization: Bearer {token}`

### Buscar Tarefa por ID
**GET** `http://localhost:8080/tarefas/1`
- Headers: `Authorization: Bearer {token}`

### Atualizar Tarefa
**PUT** `http://localhost:8080/tarefas/1`
- Headers: `Authorization: Bearer {token}`
- Body:
```json
{
  "titulo": "Separar lixo reciclável - ATUALIZADO",
  "descricao": "Separar plástico, papel, metal e vidro",
  "completado": true,
  "dataCriacao": "2024-01-15",
  "points": 15,
  "categoriaId": 1,
  "usuarioId": 1
}
```

### Deletar Tarefa
**DELETE** `http://localhost:8080/tarefas/1`
- Headers: `Authorization: Bearer {token}`

---

## 🔑 Lembretes Importantes

1. **Token JWT:** Sempre adicione o header `Authorization: Bearer {token}` em endpoints protegidos
2. **ADMIN vs USER:** 
   - ADMIN pode fazer tudo (GET, POST, PUT, DELETE)
   - USER pode apenas fazer GET (ler dados)
3. **IDs:** Use os IDs retornados nas respostas para atualizar/deletar
4. **Nível de Impacto:** Valores possíveis: `"ALTO"`, `"MEDIO"`, `"BAIXO"`
5. **Data:** Formato `YYYY-MM-DD` (exemplo: "2024-01-15")

---

## 🛠️ Ferramentas Recomendadas

- **Postman**: Melhor para testes
- **Insomnia**: Similar ao Postman
- **Thunder Client** (VS Code): Extensão simples
- **curl**: Para terminal

Para mais exemplos detalhados, veja o arquivo `EXEMPLOS_JSON_ENDPOINTS.md`

