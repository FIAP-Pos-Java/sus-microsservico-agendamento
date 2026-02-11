# SUS - Microserviço de Agendamento

## Configuração

### Variáveis de Ambiente

Este projeto utiliza variáveis de ambiente para configuração. Crie um arquivo `.env` na raiz do projeto baseado no arquivo `.env.example`:

### Variáveis Necessárias

#### Database
- `DB_USERNAME`: Usuário do banco de dados PostgreSQL
- `DB_PASSWORD`: Senha do banco de dados PostgreSQL

#### RabbitMQ
- `RABBITMQ_HOST`: Host do RabbitMQ (localhost para desenvolvimento)
- `RABBITMQ_PORT`: Porta do RabbitMQ (padrão: 5672)
- `RABBITMQ_USERNAME`: Usuário do RabbitMQ
- `RABBITMQ_PASSWORD`: Senha do RabbitMQ

#### Executando o Projeto
Clone os 3 projetos no mesmo diretório
sustech
  -> sus-microsservico-core
  -> sus-microsservico-agendamento
  -> sus-microservico-notificacoes

Configure o .env de cada projeto (todos tem um .env de exemplo)

Abra o bash no sus-microsservico-core e execute o comando `docker-compose build --no-cache`
