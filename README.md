# 💬 Chat em Java com Grupos e Nicknames

Este é um projeto simples de chat feito em Java, que permite que várias pessoas conversem em tempo real. Você pode escolher um apelido, criar grupos de conversa e bater papo com outras pessoas conectadas no mesmo servidor.

Ideal para quem está aprendendo sobre **Sockets em Java** ou quer entender como funciona a comunicação em rede entre cliente e servidor.

## ✨ O que dá pra fazer

- Definir um **apelido** com o comando `/nick`
- Criar ou entrar em **grupos de conversa** com `/join`
- Enviar mensagens para um grupo com `/msg`
- Conectar vários clientes ao mesmo tempo (multiclientes)

## 🗂️ Arquivos do Projeto

📦ChatJava
├── chatServer.java // Código do servidor (responsável por gerenciar os clientes e grupos)
├── chatCliente.java // Interface em texto para o usuário digitar os comandos
├── ClienteSocket.java // Lógica de conexão do cliente com o servidor
└── README.md // Este arquivo :)

## 🚀 Como rodar

### 1. Compile todos os arquivos
Abra o terminal e digite:
```bash
javac chatServer.java chatCliente.java ClienteSocket.java

java chatServer

java chatCliente

| Comando                   | Para que serve                           |
| ------------------------- | ---------------------------------------- |
| `/nick <nome>`            | Escolhe seu apelido                      |
| `/join <grupo>`           | Entra (ou cria) um grupo com esse nome   |
| `/msg <grupo> <mensagem>` | Envia uma mensagem para o grupo indicado |

/nick Julia
/join devs
/msg devs Olá, galera!


### Requisitos
Java JDK 8 ou superior

Um terminal (ou vários, se quiser testar com múltiplos clientes 😄)
