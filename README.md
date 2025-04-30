# Power Unit Manager API

A Spring Boot application designed to manage power unit components for Formula 1 teams. This API provides endpoints to track and manage power unit components, their manufacturers, and replacement history.

## Technologies Used

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- PostgreSQL
- Flyway (Database Migration)
- Maven

## Prerequisites

- Java 21 or higher
- Maven
- Docker and Docker Compose (for running the database)
- PostgreSQL (if running locally)

## Getting Started

1. Clone the repository:
```bash
git clone [repository-url]
cd power-unit-manager
```

2. Start the PostgreSQL database using Docker Compose:
```bash
docker-compose up -d
```

3. Build and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Power Unit Controller
- `GET /api/power-units` - Get all power units
- `GET /api/power-units/{id}` - Get power unit by ID
- `POST /api/power-units` - Create a new power unit
- `PUT /api/power-units/{id}` - Update a power unit
- `DELETE /api/power-units/{id}` - Delete a power unit

### Component Controller
- `GET /api/components` - Get all components
- `GET /api/components/{id}` - Get component by ID
- `POST /api/components` - Create a new component
- `PUT /api/components/{id}` - Update a component
- `DELETE /api/components/{id}` - Delete a component

### Manufacturer Controller
- `GET /api/manufacturers` - Get all manufacturers
- `GET /api/manufacturers/{id}` - Get manufacturer by ID
- `POST /api/manufacturers` - Create a new manufacturer
- `PUT /api/manufacturers/{id}` - Update a manufacturer
- `DELETE /api/manufacturers/{id}` - Delete a manufacturer

### Replacement History Controller
- `GET /api/replacement-history` - Get all replacement history records
- `GET /api/replacement-history/{id}` - Get replacement history by ID
- `POST /api/replacement-history` - Create a new replacement history record
- `PUT /api/replacement-history/{id}` - Update a replacement history record
- `DELETE /api/replacement-history/{id}` - Delete a replacement history record

## Database Configuration

The application uses PostgreSQL as its database. The default configuration is:
- Host: localhost
- Port: 5432
- Database: power_unit_db
- Username: postgres
- Password: postgres

These settings can be modified in the `application.properties` file.

## Project Structure

```
src/main/java/com/powerunitmanager/
├── controller/     # REST API endpoints
├── model/          # Entity classes
├── repository/     # JPA repositories
├── service/        # Business logic
├── dto/            # Data Transfer Objects
└── scheduler/      # Scheduled tasks
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 