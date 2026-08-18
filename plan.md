# Spring Boot learning roadmap

A structured path to learn Spring Boot from zero to confident, practical use.

## Proposed approach
- Use the current MongoDB CRUD app as the running practice project.
- Learn Spring Boot in layers: Java basics, core Spring concepts, web APIs, persistence, testing, and production readiness.
- Reinforce each topic with a small hands-on exercise on this app.
- Finish with a capstone version of the app that uses clean layering, validation, tests, and basic documentation.

## What this project already covers
- Java records for DTOs
- Text blocks for readable MongoDB queries
- REST controllers and request mapping
- Service layer and constructor injection
- MongoDB repositories and custom queries
- Jakarta validation on request DTOs
- Externalized configuration in `application.properties`
- CORS configuration
- Basic logging
- Virtual threads enabled in Spring Boot

## Next Spring Boot core concepts to learn in order
1. Centralized exception handling with `@ControllerAdvice`
2. Consistent API response shape and status handling
3. `@ConfigurationProperties` for typed configuration binding
4. Profiles and environment-specific configuration
5. Pagination, sorting, and filtering for list endpoints
6. Bean validation deeper dive, including custom constraints
7. MongoDB mapping details and query design tradeoffs
8. Testing slice by slice: controller, service, and repository tests
9. Spring Boot Actuator for health and metrics
10. API documentation with OpenAPI/Swagger
11. Security basics with Spring Security
12. Packaging and deployment basics

## Learning path by project milestone
### Milestone 1: Core web foundation
- Review Spring Boot application startup and component scanning
- Understand `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`
- Learn dependency injection through constructors

### Milestone 2: API design
- Add global validation/error handling
- Standardize responses and HTTP status codes
- Improve request/response DTO usage

### Milestone 3: Persistence
- Expand repository methods and query styles
- Compare derived queries, `@Query`, and MongoTemplate
- Learn when to use entities versus DTOs

### Milestone 4: Configuration and operations
- Move config to typed properties classes
- Add profiles for dev/test/prod
- Add Actuator health checks and metrics

### Milestone 5: Testing and quality
- Add controller tests with MockMvc
- Add service tests with mocks
- Add repository/integration tests against MongoDB

### Milestone 6: Production readiness
- Add security basics
- Document the API with OpenAPI
- Learn build, packaging, and deployment workflow

## Todo list
1. Build a Java and web fundamentals refresher.
2. Learn Spring Boot application structure and dependency injection.
3. Understand REST controllers, request/response handling, and validation.
4. Practice MongoDB persistence with repositories and service layering.
5. Add centralized error handling and consistent API responses.
6. Learn configuration binding, profiles, and externalized settings.
7. Add pagination, sorting, and richer query options.
8. Write tests for controllers, services, and repository behavior.
9. Learn Actuator, OpenAPI, and basic security.
10. Review packaging, deployment basics, and a final capstone checklist.

## Notes
- Keep the app small and iterative; each lesson should end with a working change.
- Prefer understanding over memorization: every concept should map to a concrete file in the project.
- Revisit the current codebase to compare “before” and “after” as skills improve.
