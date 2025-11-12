# Execução e Testes com RabbitMQ

## 1. Pré-requisitos
- Docker Desktop instalado e em execução.
- Java 21 instalado (ou ajuste `pom.xml` para a versão disponível).
- Banco PostgreSQL conforme configurado em `application.properties`.

## 2. Subir o RabbitMQ
1. Abra o PowerShell (preferencialmente como administrador).
2. Execute:
   ```powershell
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
   ```
3. Acompanhe:
   - Painel administrativo: `http://localhost:15672` (login `guest/guest`).
   - A fila criada pela aplicação é `tasks.events`.

### Comandos úteis
```powershell
docker ps                           # listar containers em execução
docker stop rabbitmq                # parar o container
docker rm rabbitmq                  # remover o container
```

## 3. Rodar a aplicação Spring Boot
1. Na raiz do projeto:
   ```powershell
   .\mvnw.cmd -q -DskipTests compile
   .\mvnw.cmd spring-boot:run
   ```
2. O aplicativo conecta no RabbitMQ usando as propriedades padrão (`localhost`, porta `5672`, `guest/guest`).

## 4. Publicar e consumir mensagens
1. Crie uma tarefa nova (Postman, Insomnia ou curl). Exemplo:
   ```bash
   curl -X POST http://localhost:8080/tarefas ^
     -H "Content-Type: application/json" ^
     -H "Authorization: Bearer <TOKEN>" ^
     -d "{`"titulo`":`"Coleta seletiva`",`"descricao`":`"Separar lixo`",`"completado`":false,`"dataCriacao`":`"2025-01-01T12:00:00`",`"points`":20,`"categoriaId`":1,`"usuarioId`":1}"
   ```
   (Ajuste IDs e token conforme sua base.)
2. O serviço `TarefaService` publica `TarefaCriadaEvent` na exchange `tasks.exchange`.
3. O listener `TaskEventListener` consome e registra no log:
   ```
   📬 Mensagem recebida do RabbitMQ: nova tarefa Coleta seletiva criada para o usuário 1
   ```
4. Se quiser inspecionar pelo painel:
   - Vá em *Queues → tasks.events → Get messages*.
   - Defina `Ack mode: Auto ack` e `Messages: 1`.

## 5. Diagnóstico rápido
- Se a aplicação não conectar: cheque logs para `Failed to connect to RabbitMQ`.
- Se a tarefa não publicar: verifique se `Categoria` e `Usuário` existem (IDs corretos).
- Se o listener não consumir: confirme que a fila `tasks.events` possui mensagens e que a aplicação está rodando.

---

Assim você verifica o fluxo completo de mensageria RabbitMQ no projeto EcoTask.

