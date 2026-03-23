# spring-boot-angular

# 🚀 Gerenciador de Usuários (Spring Boot & Angular)

Este projeto é uma aplicação full-stack para gerenciamento de usuários, permitindo operações de busca, criação e edição. O sistema utiliza uma arquitetura moderna com backend em Java e frontend em Angular.

## 🛠️ Tecnologias Utilizadas

### **Backend**
* **Java 17**
* **Spring Boot 3.2.4**
* **Spring Data JPA**: Persistência de dados.
* **H2 Database**: Banco de dados em memória para desenvolvimento.
* **Spring Security**: Configuração de segurança e CORS.
* **Maven**: Gerenciador de dependências.

### **Frontend**
* **Angular**
* **Angular Material**: Componentes de UI (Inputs, Selects, Datepicker).
* **RxJS**: Programação reativa para chamadas de API.
* **TypeScript**.

---

## 🏗️ Estrutura e Funcionalidades Principais

* **Busca via Procedure**: O sistema utiliza uma `ALIAS` (Stored Procedure) no H2 para recuperar a lista de usuários.
* **Edição Inteligente**: Recuperação automática de fuso horário para datas e mapeamento de objetos complexos no `mat-select` via `compareWith`.
* **Persistência**: Script `import.sql` para inicialização automática da base de dados.

---

## ⚙️ Configuração do Ambiente

### **1. Backend (Spring Boot)**
1.  Navegue até a pasta `spring-boot-server`.
2.  Certifique-se de que o arquivo `src/main/resources/application.properties` está configurado para o H2.
3.  Execute o comando para instalar as dependências e rodar:
    ```bash
    mvnw clean install
    mvnw spring-boot:run
    ```
4.  O console do H2 estará disponível em: `http://localhost:8080/h2-console`
    * **JDBC URL**: `jdbc:h2:mem:testdb`
    * **User**: `sa` | **Password**: *(vazio)*

### **2. Frontend (Angular)**
1.  Navegue até a pasta do projeto Angular.
2.  Instale as dependências do Node:
    ```bash
    npm install
    ```
3.  Inicie o servidor de desenvolvimento:
    ```bash
    ng serve
    ```
4.  Acesse a aplicação em: `http://localhost:4200`

---

## 📋 Endpoints Principais (API)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **GET** | `/api/users` | Retorna todos os usuários (via Procedure). |
| **GET** | `/api/users/{id}` | Busca um usuário específico por ID. |
| **POST** | `/api/users` | Cadastra um novo usuário. |
| **PUT** | `/api/users/{id}` | Atualiza os dados de um usuário existente. |

---

## 🧪 Notas de Implementação

### **Stored Procedure no H2**
A aplicação define automaticamente a seguinte procedure no arranque (via `import.sql`):
```sql
CREATE ALIAS IF NOT EXISTS BUSCAR_TODOS_USUARIOS AS $$
import java.sql.*;
ResultSet buscar(Connection conn) throws SQLException {
    return conn.createStatement().executeQuery("SELECT * FROM USERS");
}
$$;
```
```sql
CREATE ALIAS IF NOT EXISTS BUSCAR_TODOS_USUARIOS AS $$
import java.sql.*;
ResultSet buscar(Connection conn) throws SQLException {
    return conn.createStatement().executeQuery("SELECT * FROM USERS");
}
$$;
```

### **Tratamento de Datas**
Para evitar problemas de fuso horário (UTC), o frontend processa strings de data convertendo-as para o horário local do navegador antes de preencher o formulário de edição.

---

## ✒️ Autor
* **Diego Paraizo** - *Desenvolvimento Full Stack*
