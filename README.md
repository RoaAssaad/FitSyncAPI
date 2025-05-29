#  FitSync Backend – Spring Boot Fitness API

This is the **backend** for the FitSync fitness tracking application. It provides a complete REST API for managing users, meals, workouts, calorie summaries, goals, weight logs, and personalized workout recommendations.

Built with **Java (Amazon Corretto 23)**, **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **MySQL**.

---

##  Features

-  **Meal Logging** – Track meals with food name, calories, and meal type  
-  **Workout Logging** – Log workouts with type, duration, and calories burned  
-  **Calorie Tracking** – Aggregate calories consumed and burned per day  
-  **Goal Management** – Set and update user-specific calorie goals  
-  **Weight Tracking** – Log and review weight changes over time  
-  **Daily & Weekly Summaries** – Auto-generated summaries of user activity  
-  **Workout Recommendations** – Suggest workouts based on user goals  
-  **User Support** – User management 

---

## 🛠️ Tech Stack

- **Java 23 (Amazon Corretto)**
- **JDBC**  
- **Spring Boot**  
- **Spring Data JPA**  
- **Hibernate ORM**  
- **MySQL**  
- **RESTful APIs**  
- **Swagger/OpenAPI** (for documentation)

---

##  Database Schema

Uses MySQL with the following core tables:
- `users`
- `meals`
- `workouts`
- `user_meals`
- `user_workouts`
- `user_weights`
- `goals`
- `daily_summary`
- `exercises`

Import `fitsync.sql` to set up your schema.

---

## 📂 Project Structure

```
src/
├── controller/         # REST Controllers
├── model/              # JPA Models
├── repository/         # Spring Data Repositories
├── service/            # Business Logic
├── dto/                # Data Transfer Objects

```

---

##  API Documentation

After running the app, open:  
http://localhost:8080/swagger-ui/index.html

---

##  Running Locally

1. **Clone the repository**
```
git clone https://github.com/RoaAssaad/FitSyncAPI.git
cd FitSyncAPI
```

2. **Set up MySQL database**
   - Create a database called `fitsync`
   - Import `fitsync.sql` using MySQL Workbench or CLI

3. **Configure `application.properties`**
```
spring.datasource.url=jdbc:mysql://localhost:3306/fitsync
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. **Run the application**
```
./mvnw spring-boot:run
```

---

##   Main Endpoints Overview

| Method | Endpoint                  | Description                        |
|--------|---------------------------|------------------------------------|
| GET    | /api/v1/meals             | Get all meals                      |
| POST   | /api/v1/user-meals        | Log a meal for a user              |
| PUT    | /api/v1/user-workouts     | Update a workout log               |
| DELETE | /api/v1/user-meals/{id}   | Delete a logged meal               |
| GET    | /api/today/{userId}       | Get today’s calorie summary        |
| GET    | /api/v1/weekly-progress   | Get 7-day calories chart           |

---

