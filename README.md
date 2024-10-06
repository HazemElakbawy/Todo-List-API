# To-Do List API 📝

- This project is a RESTful API for managing a to-do list with user authentication.
- It provides endpoints for user registration, login, and CRUD operations on to-do items.
- This project is part of the community-driven projects published on [roadmap.sh](https://roadmap.sh/projects/todo-list-api).
- Feel free to visit the [projects page](https://roadmap.sh/projects) to explore more.


## Technologies Used 🛠️

- Java 22 ☕
- Spring Boot 🍃
- Spring Security 🔒
- Spring Data JPA 📊
- SQL Server 🗄️
- Docker & Docker Compose 🐳
- Maven 📦
- Swagger UI 📚


## Features 🌟

- User registration and authentication
- Create, read, update, and delete to-do items
- Pagination and filtering for to-do list
- Token-based authentication
- Error handling and security measures
- Data validation
- API documentation using Swagger UI

## Prerequisites ⚙️

- Java 22 JDK
- Docker and Docker Compose
- Maven

## Getting Started 🚀

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/todo-list-api.git
   cd todo-list-api
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Start the application and database using Docker Compose:
   ```
   docker-compose up -d
   ```

4. The API will be available at `http://localhost:8080`

## Swagger UI 📊

  - You can explore and test the API using Swagger UI. After starting the application, open the following URL in your browser: `http://localhost:8080/swagger-ui/index.html`
  - This will load the interactive Swagger interface where you can view API documentation, test API endpoints, and inspect request/response details.

## API Endpoints 🔗

### User Registration
- `POST /register`
  - Request:
    ```json
      {
        "name": "John Doe",
        "email": "john@doe.com",
        "password": "password"
      }
    ```
  - Response:
    ```json
      {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXplbTIyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzI4MjI0OTI1LCJleHAiOjM2MTcyODIyNDkyNX0.XjewZByBs3mi59U6UEtHFlx9XGcA39OnKAMSe7sPXbHSroFLz17HsIlwT-XnIb0ur0T4xEZBb2R9iCfOtoQQAA"
      }
    ```

### User Login
- `POST /login`
    - Request: 
        ```json
          {
            "email": "john@doe.com",
            "password": "password"
          }
        ```
    - Response:
      ```json
        {
          "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXplbTIyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzI4MjI0OTI1LCJleHAiOjM2MTcyODIyNDkyNX0.XjewZByBs3mi59U6UEtHFlx9XGcA39OnKAMSe7sPXbHSroFLz17HsIlwT-XnIb0ur0T4xEZBb2R9iCfOtoQQAA"
        }
      ```

### Create a To-Do Item
- `POST /todos`
    - Headers: `Authorization: Bearer <token>`
    - Request: 
      ```json
        {
          "title": "Buy groceries",
          "description": "Buy milk, eggs, and bread"
        }
      ```
    - Response: 
      ```json
        {
          "id": 1,
          "title": "Buy groceries",
          "description": "Buy milk, eggs, and bread"
        }
      ```

### Update a To-Do Item
- `PUT /todos/{id}`
    - Headers: `Authorization: Bearer <token>`
    - Request: 
        ```json
          {
            "title": "Buy groceries",
            "description": "Buy milk, eggs, bread, and cheese"
          }
        ```
    - Response: 
      ```json
        {
          "id": 1,
          "title": "Buy groceries",
          "description": "Buy milk, eggs, bread, and cheese"
        } 
      ```

### Delete a To-Do Item
- `DELETE /todos/{id}`
    - Headers: `Authorization: Bearer <token>`
    - Response: 204 No Content

### Get To-Do Items
- `GET /todos?page=1&limit=10`
    - Headers: `Authorization: Bearer <token>`
    - Response:
    ```json
      {
        "data": [
          {
            "id": 1,
            "title": "Buy groceries",
            "description": "Buy milk, eggs, bread"
          },
          {
            "id": 2,
            "title": "Pay bills",
            "description": "Pay electricity and water bills"
          }
        ],
        "page": 1,
        "limit": 10,
        "total": 2
      }
    ```

## Security 🔒

- Passwords are hashed before storing in the database
- Token-based authentication is implemented
- Input validation is performed on all endpoints

## Error Handling ⚠️

The API uses appropriate HTTP status codes and error messages:
- 400 Bad Request: For invalid input
- 401 Unauthorized: For authentication failures
- 403 Forbidden: For authorization failures
- 404 Not Found: For resources that don't exist
- 500 Internal Server Error: For server-side errors