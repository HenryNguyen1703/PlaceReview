# Place Review

## Project Overview

**Place Review** is an application that allows customers to rate restaurants they have visited. If a restaurant receives a positive review, customers may be eligible for a cashback reward. The application helps to:

- Easily find and rate restaurants.
- Provide direct feedback to restaurants.
- Encourage honest reviews and customer engagement.
- Ensure transparency and trust through cashback for valid reviews.

The backend is developed using **Spring Boot**, exposing **RESTful APIs**. Customers authenticate using an **OTP code** stored in **Redis** to enhance security.

---

## Required Environment

Before running the project, ensure your environment has:

- **Docker** >= 20.x
- **Docker Compose** >= 1.29.x
- **Java** >= 17 (for building Spring Boot jar if needed)
- **Maven** (optional, only for building the jar)
- **Redis** (for OTP storage, can be a separate container)

Environment variables:

| Variable | Description | Example |
|----------|-------------|---------|
| `MYSQL_ROOT_PASSWORD` | Root password for MySQL | `root` |
| `MYSQL_DATABASE` | Database name | `placereview` |
| `REDIS_HOST` | Redis server host | `redis` |
| `REDIS_PORT` | Redis server port | `6379` |

---

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/HenryNguyen1703/PlaceReview.git
   cd placereview
   ```
2. **Build the Spring Boot application (if jar not available):**
     ```bash
     ./mvnw clean package
     ```
3. **Run the project using Docker Compose in detached mode:**
    ```bash
    docker-compose up -d
    ```

4. **Verify that all containers are running:**
    ```bash
    docker ps
    ```
    You should see **placereview app**, **mysqldb**, and **redis**.
  
5. **Access the application:**
   - Backend API: http://localhost:8080/swagger-ui/index.html#/
   - MySQL server: localhost:3307 
   - Redis server: localhost:6379
6. **Customer login using OTP:**
   - Customers will receive an **OTP code via email**.
   - They must enter this OTP in the application to successfully authenticate their account.
   - OTP codes are stored temporarily in Redis until they expire or are used.
7. **Stop the project:**
    ```bash
    docker-compose down
    ```
8. **Optional: Remove volumes to clear database data:**
     ```bash
     docker-compose down -v
     ```
