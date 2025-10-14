# BookMyMovie - Movie Ticket Booking System

A Spring Boot application that provides RESTful APIs for movie ticket booking and management.

## Features

- User authentication and authorization using JWT
- Movie management (Admin operations)
- Show scheduling and theater management
- Ticket booking system
- User profile management

## Tech Stack

- Java
- Spring Boot
- Spring Security with JWT
- Spring Data JPA
- Maven

## Project Structure

```
src/
├── main/
    ├── java/
    │   └── com/example/BookMyMovie/
    │       ├── configuration/     # Security and other configurations
    │       ├── controller/        # REST API endpoints
    │       ├── dto/              # Data Transfer Objects
    │       ├── exception/        # Custom exceptions
    │       ├── model/            # Entity classes
    │       ├── repository/       # Data access layer
    │       ├── security/         # JWT and security utilities
    │       └── service/          # Business logic layer
    └── resources/
        └── application.properties # Application configuration
```

## API Endpoints

### User Management
- POST `/api/auth/register` - Register a new user
- POST `/api/auth/login` - User login

### Movie Management (Admin)
- POST `/api/admin/movies` - Add a new movie
- PUT `/api/admin/movies/{id}` - Update movie details
- DELETE `/api/admin/movies/{id}` - Delete a movie

### Show Management
- POST `/api/movieShows` - Add a new movieShow
- GET `/api/movieShows` - Get all movieShows
- GET `/api/movieShows/{id}` - Get movieShow details

### Booking Management
- POST `/api/bookings` - Book tickets
- GET `/api/bookings/{id}` - Get booking details
- PUT `/api/bookings/{id}` - Update booking status

## Getting Started

### Prerequisites
- Java JDK
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository
```bash
git clone https://github.com/vermastuti/BookMyMovie.git
```

2. Navigate to the project directory
```bash
cd BookMyMovie
```

3. Build the project
```bash
./mvnw clean install
```

4. Run the application
```bash
./mvnw spring-boot:run
```

The application will start running at `http://localhost:9002`

## Security

The application uses JWT (JSON Web Token) for authentication. Protected endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
