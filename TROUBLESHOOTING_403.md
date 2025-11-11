# 🔧 Troubleshooting - Erro 403 (Forbidden)

## Problema
Você está recebendo erro **403 Forbidden** ao tentar acessar endpoints mesmo com o token JWT válido.

## ✅ Correções Aplicadas

As seguintes correções foram aplicadas no código:

1. **SecurityFilter melhorado**: Agora busca o usuário completo do banco garantindo que o campo `role` seja carregado corretamente
2. **Tratamento de token melhorado**: Suporte para "Bearer " e "bearer " (case insensitive)
3. **Validação de role**: Verifica se o usuário tem role antes de autenticar

## 🔍 Como Diagnosticar o Problema

### 1. Verificar se o Token está sendo enviado corretamente

**Header correto:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**⚠️ IMPORTANTE:**
- Deve ter um espaço entre "Bearer" e o token
- Não pode ter aspas no token
- O header deve ser exatamente "Authorization" (case sensitive)

### 2. Verificar se o usuário tem a role ADMIN

**Para operações de escrita (POST, PUT, DELETE):**
- O usuário **DEVE** ter a role `ADMIN`
- Usuários com role `USER` não podem criar/editar/deletar

**Para operações de leitura (GET):**
- Qualquer usuário autenticado (ADMIN ou USER) pode ler

### 3. Verificar se o usuário foi criado corretamente

Execute no pgAdmin4 para verificar o usuário:

```sql
SELECT id, username, email, role FROM usuario;
```

**Verifique:**
- O campo `role` não está NULL
- O valor de `role` está correto: `ADMIN` ou `USER` (não `admin` ou `user` em minúsculas)

### 4. Verificar se o Token está expirado

Os tokens expiram em **2 horas**. Se o token estiver expirado:
1. Faça login novamente
2. Copie o novo token
3. Use o novo token nas requisições

### 5. Testar o Fluxo Completo

#### Passo 1: Registrar um usuário ADMIN
```bash
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "username": "admin",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

#### Passo 2: Fazer Login
```bash
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "admin123"
}
```

**Copie o token da resposta!**

#### Passo 3: Testar GET /usuarios (deve funcionar)
```bash
GET http://localhost:8080/usuarios
Authorization: Bearer {cole_o_token_aqui}
```

#### Passo 4: Testar PUT /usuarios/{id} (precisa ser ADMIN)
```bash
PUT http://localhost:8080/usuarios/1
Authorization: Bearer {cole_o_token_aqui}
Content-Type: application/json

{
  "username": "admin_updated",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}
```

## 🐛 Problemas Comuns e Soluções

### Problema 1: "403 Forbidden" em GET /usuarios

**Causa:** Token inválido ou não está sendo enviado

**Solução:**
1. Verifique se o header `Authorization` está sendo enviado
2. Verifique se o token não expirou (faça login novamente)
3. Verifique se há um espaço entre "Bearer" e o token

### Problema 2: "403 Forbidden" em POST/PUT/DELETE

**Causa:** Usuário não tem role ADMIN

**Solução:**
1. Verifique no banco se o usuário tem `role = 'ADMIN'`
2. Se não tiver, crie um novo usuário com role ADMIN
3. Faça login com esse usuário ADMIN

### Problema 3: Token parece válido mas ainda dá 403

**Causa:** O campo `role` no banco pode estar NULL

**Solução:**
1. Execute no pgAdmin4:
```sql
UPDATE usuario SET role = 'ADMIN' WHERE email = 'seu_email@example.com';
```
2. Faça login novamente para gerar um novo token
3. Teste novamente

### Problema 4: Erro ao fazer login

**Causa:** Usuário não existe ou senha incorreta

**Solução:**
1. Verifique se o usuário foi criado:
```sql
SELECT * FROM usuario WHERE email = 'seu_email@example.com';
```
2. Se não existir, crie o usuário primeiro
3. Verifique se a senha está correta

## 📝 Exemplo de Requisição Completa (cURL)

```bash
# 1. Registrar ADMIN
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "email": "admin@example.com",
    "password": "admin123",
    "role": "ADMIN"
  }'

# 2. Login (copie o token)
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "admin123"
  }'

# 3. Listar usuários (use o token recebido)
curl -X GET http://localhost:8080/usuarios \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

## 🔑 Verificações Importantes

1. ✅ O token está no formato correto? (`Bearer {token}`)
2. ✅ O token não expirou? (válido por 2 horas)
3. ✅ O usuário tem a role correta no banco? (`ADMIN` para escrita)
4. ✅ O campo `role` no banco não está NULL?
5. ✅ O header `Authorization` está sendo enviado?
6. ✅ A aplicação foi reiniciada após as alterações?

## 🚀 Próximos Passos

Se ainda estiver com problemas após verificar todos os itens acima:

1. **Verifique os logs da aplicação** para ver mensagens de erro mais detalhadas
2. **Teste com Postman/Insomnia** para garantir que os headers estão corretos
3. **Verifique no banco de dados** se o usuário existe e tem a role correta
4. **Reinicie a aplicação** para garantir que as alterações foram aplicadas

## 📌 Nota Final

Após aplicar as correções:
1. **Reinicie a aplicação Spring Boot**
2. **Crie um novo usuário ADMIN** (ou atualize o existente)
3. **Faça login novamente** para obter um novo token
4. **Teste os endpoints** com o novo token

Se o problema persistir, verifique os logs da aplicação para mais detalhes sobre o erro específico.

