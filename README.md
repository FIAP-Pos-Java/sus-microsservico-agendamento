# SUS - Microserviço de Agendamento

## Configuração

### Variáveis de Ambiente

Este projeto utiliza variáveis de ambiente para configuração. Crie um arquivo `.env` na raiz do projeto baseado no arquivo `.env.example`:

```bash
cp .env.example .env
```

### Variáveis Necessárias

#### Database
- `DB_USERNAME`: Usuário do banco de dados PostgreSQL
- `DB_PASSWORD`: Senha do banco de dados PostgreSQL

#### RabbitMQ
- `RABBITMQ_HOST`: Host do RabbitMQ (localhost para desenvolvimento)
- `RABBITMQ_PORT`: Porta do RabbitMQ (padrão: 5672)
- `RABBITMQ_USERNAME`: Usuário do RabbitMQ
- `RABBITMQ_PASSWORD`: Senha do RabbitMQ

### Executando o Projeto

```bash
./mvnw spring-boot:run
```

### Executando com Docker

```bash
cd ../sustech
docker-compose up sus-agendamento
```

## Notas de Segurança

⚠️ **IMPORTANTE**: Nunca faça commit do arquivo `.env` no repositório! O arquivo `.gitignore` já está configurado para ignorá-lo.
