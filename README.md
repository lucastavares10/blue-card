# Web BlueCard

Sistema de Controle de Passagens e Cartões — um sistema interno fictício para empresa de bilhetagem eletrônica.

---

## 💡 Ideia do Projeto

Web BlueCard é uma aplicação web para controle de bilhetagem eletrônica com funcionalidades para usuários e administradores, incluindo cadastro, autenticação, gerenciamento de cartões, recargas e histórico de transações.

---

## 📌 Funcionalidades

### Usuário (Cliente)

- Criar conta e autenticar via JWT
- Cadastrar e visualizar seus cartões
- Solicitar recargas
- Visualizar histórico de transações

### Administrador

- Gerenciar usuários (CRUD)
- Validar recargas pendentes
- Acompanhar uso dos cartões

---

## 🖥️ Tecnologias Utilizadas

### Frontend (`bluecard-fed`)

- Vue 3
- Pinia (gerenciamento de estado)
- TypeScript

### Backend (`bluecard-api`)

- Spring Boot
- Spring Security (com JWT)
- Banco de Dados Oracle
- JPA (Hibernate)
- Flyway (migrations)

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 17+ (ou versão compatível com Spring Boot)
- Oracle Database (configurado localmente ou via container)
- Node.js e npm/yarn
- Docker (opcional, para facilitar o setup do banco)

### Backend

1. Configurar o Oracle Database e ajustar as credenciais no arquivo `application.properties` ou `application.yml` do backend.
2. Rodar as migrations com Flyway (automático ao iniciar a aplicação).
3. Executar a API Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend

1. Navegar até a pasta `bluecard-fed`
2. Instalar dependências:
   ```bash
   npm install
   ```
3. Rodar a aplicação Vue:
   ```bash
   npm run serve
   ```
4. Acessar via navegador: `http://localhost:8080`

---

## 📁 Estrutura do Projeto

```
/bluecard-fed       # Frontend Vue 3 + Pinia + TS
/bluecard-api       # Backend Spring Boot + Spring Security + JPA + Oracle DB + Flyway
```

---

## 🔐 Autenticação e Segurança

- Backend utiliza JWT para autenticação de usuários.
- Spring Security protege endpoints conforme papéis (usuário/admin).
- Frontend implementa guardas de rota baseadas no estado da autenticação.

---

## 👨‍💻 Autor

Lucas Tavares – projeto de estudo e prática pessoal.
