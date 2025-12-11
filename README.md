# 🐇📡 Consumer RabbitMQ – Projeto SIMBIOSYS

<p align="center">
  <img src="https://imgur.com/6s2lH3n.png" width="240" alt="Simbiosys Logo">
</p>

<p align="center">
  <b>Serviço responsável por consumir mensagens do RabbitMQ e integrar dados com o Back-End do projeto Simbiosys.</b>
</p>

---

## 📌 Visão Geral

Este repositório contém o **consumer RabbitMQ** do ecossistema SIMBIOSYS.  
Ele é responsável por:

- 📥 Consumir mensagens enviadas pela aplicação principal  
- 🔄 Processar dados recebidos  
- 📨 Enviar resultados/processamentos para o Back-End  
- 🧩 Integrar dados entre microserviços  

---

## 🛠️ Tecnologias Utilizadas

- **Node.js**
- **RabbitMQ**
- **amqplib**
- **Dotenv**
- **Axios**

---

## ⚙️ Pré-requisitos

Antes de rodar este serviço, garanta que possui:

- Node.js instalado (versão 18+ recomendada)
- RabbitMQ rodando localmente ou em servidor
- Credenciais corretas no arquivo `.env`
- Backend ativo 

---

## 🔧 Configuração

1. Clone o repositório:

```sh
git clone https://github.com/projeto-simbiosys/Consumer-RabbitMQ
cd Consumer-RabbitMQ
```
## 🐇 Como Funciona

O serviço se conecta ao RabbitMQ

Ele escuta a fila definida em QUEUE_NAME

Cada mensagem recebida é processada

Caso necessário, o consumer envia requisições ao Back-End

Logs são exibidos no terminal durante o consumo

## 🐳 Rodando com Docker

Se quiser rodar o consumer via Docker:
```
docker build -t simbiosys-consumer .
docker run --env-file .env simbiosys-consumer
```

## 📚 Logs

Os logs do consumer exibem:

Conexão com RabbitMQ

Mensagens recebidas

Falhas ou reconexões

Processamentos realizados
