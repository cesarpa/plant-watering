# Project Specification: Plant & Garden Management System

## Overview
A backend system designed to manage various plants across different garden locations, tracking specific care requirements, containers, and maintenance schedules.

## Data Model

### 1. Entity: Garden
Represents a physical space containing a collection of plants.
- **name**: String (e.g., Front Yard, Balcony, Greenhouse).
- **plants**: List<Plant> (One-to-Many relationship).

### 2. Entity: EnergySource
Catalog of energy sources (e.g., types of light or environments).
- **id**: Unique identifier.
- **name**: String (e.g., Natural Sunlight, LED Grow Light).
- **description**: String (Optional details).

### 3. Entity: FertilizerType
Catalog of available nutrition options.
- **id**: Unique identifier.
- **name**: String (e.g., Organic, Mineral, Foliar).
- **description**: String (Optional details).

### 4. Entity: Pot
Represents the container in which a plant is grown.
- **id**: Unique identifier.
- **name**: String (e.g., Large Terracotta, Modern Ceramic).
- **material**: String (e.g., Clay, Plastic, Ceramic).
- **size**: String (e.g., Small, Medium, Large).

### 5. Entity: Plant
Represents an individual botanical specimen.
- **name**: String (Common or scientific name).
- **energy_id**: FK (Reference to EnergySource).
- **fertilizer_id**: FK (Reference to FertilizerType).
- **garden_id**: FK (Reference to Garden).
- **pot_id**: FK (Reference to Pot).
- **wateringFrequency**: Integer (Interval in days).
- **lastPruneDate**: Date (The last date the plant was pruned).

---

## Database Schema (Relational)

### Table: `gardens`
| Column | Type | Constraints |
| :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(100) | NOT NULL |

### Table: `energy_sources`
| Column | Type | Constraints |
| :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(100) | NOT NULL |
| `description` | TEXT | |

### Table: `fertilizer_types`
| Column | Type | Constraints |
| :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(100) | NOT NULL |
| `description` | TEXT | |

### Table: `pots`
| Column | Type | Constraints |
| :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(100) | NOT NULL |
| `material` | VARCHAR(50) | |
| `size` | VARCHAR(50) | |

### Table: `plants`
| Column | Type | Constraints |
| :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(100) | NOT NULL |
| `watering_frequency` | INT | CHECK (watering_frequency > 0) |
| `last_prune_date` | DATE | |
| `energy_id` | BIGINT | FOREIGN KEY (`energy_sources.id`) |
| `fertilizer_id` | BIGINT | FOREIGN KEY (`fertilizer_types.id`) |
| `garden_id` | BIGINT | FOREIGN KEY (`gardens.id`) |
| `pot_id` | BIGINT | FOREIGN KEY (`pots.id`) |

---

## Technical Requirements
- Implement full CRUD operations for all entities.
- Ensure `wateringFrequency` is always a positive integer.
- Enable filtering of plants by their parent Garden.
- **Database**: Use H2 in-memory database for development and testing.
- **ORM**: Map relationships using standard annotations (JPA/Hibernate).
- use the corresponding package structure for entities, repositories, services, and controllers.
- use Thymeleaf for any necessary view rendering (if applicable).
---

## Technical Stack & Constraints (Strict)
- **Language**: Java 21.
- **Framework**: Spring Boot 3.x.
- **Lombok**: Required. Use `@Data`, `@Builder`, `@NoArgsConstructor`, and `@AllArgsConstructor` to reduce boilerplate.
- **Persistence**: Use **Jakarta Persistence (JPA)** instead of the old `javax.persistence` (Spring Boot 3+ standard).
- **Validation**: Use `jakarta.validation.constraints` for fields like `name` (@NotBlank) and `wateringFrequency` (@Min(1)).
- **Mapping**: Use standard JPA annotations for relationships (`@OneToMany`, `@ManyToOne`, `@JoinColumn`).
- **Structure**: Follow a layered architecture with clear separation of concerns (Entities, Repositories, Services, Controllers).

## Thymeleaf Views (Optional)
- **Garden List View**: Display all gardens and their associated plants.
- **Plant Detail View**: Show detailed information about a specific plant, including care requirements and history(last_prune_date).
- **Energy Source and Fertilizer Management Views**: Allow users to manage energy sources and fertilizer types.
- **Pot Management View**: Enable users to manage pot types and their attributes.
- **Watering Schedule View**: Display upcoming watering needs based on `wateringFrequency` and `lastPruneDate`.

## Future Implementation Tasks
- [x] Define Database Schema.
- [x] Set up ORM models and relationships.
- [ ] Implement business logic for watering alerts.
- [ ] Design RESTful API endpoints.