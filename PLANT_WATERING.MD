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
- **id**: Unique identifier.
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
- **Repositories**: Use Spring Data JPA repositories for data access.
- **Service Layer**: Implement a service layer to handle business logic and interactions between controllers and repositories.
  - create 3 methods in the service layer for each entity: `getAll`, `getById`, `createOrUpdate`, and `delete`.
- **Validation**: Use `jakarta.validation.constraints` for fields like `name` (@NotBlank) and `wateringFrequency` (@Min(1)).
- **Mapping**: Use standard JPA annotations for relationships (`@OneToMany`, `@ManyToOne`, `@JoinColumn`).
- **Structure**: Follow a layered architecture with clear separation of concerns (Entities, Repositories, Services, Controllers).

## Controllers & Web Routing (Thymeleaf)
- **Controller Type**: Use `@Controller` (not `@RestController`) for all web controllers.
- **Return Types**: Methods must return a `String` representing the Thymeleaf template path.
- **Model Mapping**: Use `org.springframework.ui.Model` to pass data to the views.
- **Naming Convention**:
  - Controllers should be named `[Entity]WebController.java`.
  - Templates should be located in `src/main/resources/templates/` and named `[entity]-list.html`, `[entity]-form.html`, etc.

### Specific Routes & View Mapping:
| Controller | Route | Template Name | Action |
| :--- | :--- | :--- | :--- |
| **GardenController** | `/gardens` | `garden-list.html` | List all gardens and their plants |
| **PlantController** | `/plants/{id}` | `plant-detail.html` | View specific plant details |
| **PotController** | `/pots` | `pot-list.html` | CRUD Management for Pots |
| **EnergyController** | `/energy-sources` | `energysource-list.html` | CRUD Management for Energy |
| **FertilizerController**| `/fertilizers` | `fertilizer-list.html` | CRUD Management for Fertilizers |
| **ScheduleController** | `/schedule` | `watering-schedule.html` | View upcoming tasks |

## Thymeleaf View Specifications
- **Layout**: All views should share a common fragments (header/footer) if possible.
- **Forms**: Use `th:object` and `th:field` for data binding in `-form.html` views.
- **Feedback**: Implement simple alert messages using `redirectAttributes` (e.g., "Pot created successfully").


Markdown
## Implementation Progress & Roadmap
- [x] Define Database Schema (H2 In-Memory).
- [x] Set up ORM models and relationships (JPA & Lombok).
- [ ] Implement Service Layer (CRUD logic for all entities).
- [ ] Create Web Controllers and Thymeleaf Route Mapping.
- [ ] Design Frontend Templates (Bootstrap 5 + Thymeleaf).
- [ ] Implement Business Logic: Watering Alerts (calculate next watering date).
- [ ] **Advanced**: Implement a Task Scheduler to notify about overdue pruning/watering.
- [ ] **Bonus**: Add Search/Filter functionality by Garden and Plant Name.