# TaskFlow

[![CI](https://github.com/harshai07/taskflow/actions/workflows/ci.yml/badge.svg)](https://github.com/harshai07/taskflow/actions/workflows/ci.yml)

A task-management REST API built with **Spring Boot 3** and **Java 17**, created as the hands-on project for a 26-day Spring Boot interview-prep plan. It starts as a small, runnable REST service and grows — persistence, security, tests, Docker — one feature at a time.

> Roadmap / tracker: **https://springboot-26-days-harshai07s-projects.vercel.app**

## Tech stack

- Java 17, Spring Boot 3.2
- Spring Web (REST), Spring Validation
- Maven (with the Maven wrapper)
- JUnit 5 (tests)

Coming in later phases: Spring Data JPA + PostgreSQL, Spring Security + JWT, Actuator, Swagger/OpenAPI, Docker.

## Run it

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The app starts on `http://localhost:8080`.

## API

Tasks are kept in memory for now (swapped to a database in Phase 3).

| Method | Path          | Description            |
|--------|---------------|------------------------|
| GET    | `/api/tasks`     | List all tasks      |
| GET    | `/api/tasks/{id}`| Get one task        |
| POST   | `/api/tasks`     | Create a task       |
| PUT    | `/api/tasks/{id}`| Update a task       |
| DELETE | `/api/tasks/{id}`| Delete a task       |

### Example

```bash
# create
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring Boot","description":"Finish day 1","status":"TODO"}'

# list
curl http://localhost:8080/api/tasks
```

A blank title returns `400` with field-level validation errors; an unknown id returns `404` — both in a consistent error shape (see `GlobalExceptionHandler`).

## Project structure

```
src/main/java/com/taskflow
├── TaskflowApplication.java      # entry point
├── task
│   ├── Task.java                 # domain model (becomes a JPA @Entity in Phase 3)
│   ├── TaskStatus.java           # TODO / IN_PROGRESS / DONE
│   ├── TaskRepository.java       # in-memory store (becomes JpaRepository in Phase 3)
│   ├── TaskService.java          # business logic
│   ├── TaskController.java       # REST endpoints
│   └── dto
│       ├── TaskRequest.java      # validated input
│       └── TaskResponse.java     # output
└── common
    ├── ResourceNotFoundException.java
    ├── ApiError.java
    └── GlobalExceptionHandler.java   # @ControllerAdvice
```

## Tests

```bash
./mvnw test
```
