# FitSync Backend – Spring Boot Fitness API

This is the **backend** for the FitSync fitness tracking application. It provides a complete REST API for managing users, meals, workouts, calorie summaries, goals, weight logs, and personalized workout recommendations.

Built with **Java (Amazon Corretto 23)**, **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **MySQL**.  
**Connected to the JavaFX frontend:** [FitSync UI Repository](https://github.com/RoaAssaad/FitSync-UI)

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

## 🔗 Frontend

This backend is fully integrated with the JavaFX frontend available at:  
[https://github.com/RoaAssaad/FitSync-UI](https://github.com/RoaAssaad/FitSync-UI)

