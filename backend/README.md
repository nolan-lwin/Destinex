# Team 08 CSCI205 Project: Destinex - The International Delivery Service

## Team Members and Roles

- **Professor:** Joshua Stough
- **Product Owner:** Nolan Lwin
- **Scrum Master:** Chang Min Bark
- **Developers:** Hung Pham, Hung Ngo

### Backend Description

This backend, built with Spring Boot and MongoDB, handles all our application's data processing, API management, and integration with the frontend. We constantly evolve our backend architecture and functionalities to align with customer needs and demands in our commitment to provide the best user experience.

### Key Features

#### RESTful API Endpoints
- **Efficient Data Handling**: Our REST API is designed to handle data requests efficiently. It supports standard operations like GET, POST, PUT, and DELETE, allowing users to interact with our database securely and intuitively.
- **Scalable and Secure**: With Spring Boot, we've ensured that our API is not only scalable to handle increasing loads but also secure using JWT token to protecting user data and interactions.
- **JSON Responses**: The API communicates using JSON, ensuring lightweight data exchange and compatibility with various frontend technologies.

#### WebSocket Endpoints
- **STOMP Over WebSocket**: We utilize STOMP as a simple, lightweight messaging protocol that works on top of WebSocket. This enables our application to handle messaging semantics like subscribe, unsubscribe, send, and receive, providing a flexible way to manage real-time data flow.
- **Message Broker Integration**: To efficiently route messages between the server and connected clients, we've integrated a message broker into our WebSocket implementation. This ensures smooth and scalable handling of messages, even when the number of concurrent users grows.
- **Real-Time Data Exchange**: This setup is perfect for features that require live data updates, such as notifications, real-time analytics, or live chats. The message broker efficiently handles the distribution of messages, ensuring timely and reliable data delivery to the appropriate recipients.

### Package Structure

Our application is structured into several packages, each with its specific responsibility:

- `main/java/com/team08/csci205_final_project`: Root package containing all application logic.
  - `advice`: Classes for logging and exception handling from controller.
  - `config`: Configuration classes for setting up application parameters and settings.
  - `controller`: Controllers managing HTTP request handling and response generation.
  - `event`: For event handling and related application logic.
  - `exception`: Custom exception classes and exception management.
  - `model`: Core classes representing application data and application logic.
    - `Auth`: Classes specific to authentication processes.
    - `DTO`: Data Transfer Objects for efficient data encapsulation.
      - `Job`, `Provider`, `User`: DTOs for respective entities.
    - `Job`, `Provider`, `Transaction`, `User`: Model classes for these entities.
  - `repository`: Interfaces for data access using MongoDB.
  - `security`: Security configurations and security exception handling.
  - `service`: Service classes containing the application's logic.

- `resources`: Non-code configurations.

- `test`: Contains all test code, mirroring the structure of the `main` directory for comprehensive testing.
  - `controller`, `repository`, `security`, `service`: Test packages corresponding to their respective application components.

### Third-Party Libraries

We've utilized the following third-party libraries, which are not part of the standard Java API:

1. **Spring Boot (version 3.1.5)** - A powerful framework that simplifies the development, deployment, and operation of Spring applications. [Spring Boot Main Page](https://spring.io/projects/spring-boot)
2. **MongoDB** - Our choice for the database, MongoDB is a NoSQL database that offers high performance, high availability, and easy scalability. [MongoDB Main Page](https://www.mongodb.com/)
3. **Lombok** - Lombok is a Java library that helps to reduce boilerplate code. It automatically generates getters, setters, constructors, and other common code during the compile-time process. [Lombok Main Page](projectlombok.org)
4. **jjwt-api (Version 0.11.5)** - We use `jjwt-api` for handling JSON Web Tokens (JWTs), crucial for secure authentication and communication in our application. [jjwt-api GitHub](https://github.com/jwtk/jjwt)
5. **Springdoc (Version 2.1.0)** - This library makes it easy to create and maintain accurate API documentation, which is essential for both internal development and external API consumers. [Springdoc GitHub](https://github.com/springdoc/springdoc-openapi)


### How to run it

Follow these steps to set up the backend of our project on your local machine:

1. **Navigate to the Backend Directory**:
    - Open a terminal or command prompt.
    - Change to the backend directory of the project:
      ```
      cd path/to/csci205_final_project/backend
      ```
      Replace `path/to` with the actual path to your project.

2. **Build and run the project**:
    - Use gradle wrapper to build the code:
      ```
      ./gradlew build
      ```
    - Run it:
      ```
      ./gradlew run
      ```

3. **Verify the Server is Running**:
    - Check the terminal output to confirm the server is running. You can access the [API Documentation](localhost:8080/docs/api/ui) for front-end developers and also use tools like Postman to test the API endpoints.