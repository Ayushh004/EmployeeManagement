# Employee Management REST API

A simple, clean, layered Spring Boot REST API for managing employees.
Built with Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, and Maven.



```
src/main/java/com/example/employeemanagement
├── controller   -> EmployeeController.java
├── service      -> EmployeeService.java
├── repository   -> EmployeeRepository.java
├── model        -> Employee.java
└── EmployeeManagementApplication.java
src/main/resources
└── application.properties
```

---

## STEP-BY-STEP: HOW TO RUN THIS PROJECT

### Prerequisites (install these first)

1. **Java 17** (or newer) -> check with: `java -version`
2. **Maven** -> check with: `mvn -version`
   (If you don't have Maven installed separately, that's fine — most IDEs
   like IntelliJ have Maven built in, OR you can use the included `mvnw`
   wrapper script if present.)
3. **MySQL Server** running locally (MySQL Workbench or just the MySQL
   service is fine) -> check with: `mysql -u root -p`
4. **Postman** (for testing the API) -> download from postman.com

---

### Step 1: Create the MySQL Database

Open MySQL command line or MySQL Workbench and run:

```sql
CREATE DATABASE IF NOT EXISTS employee_db;
```

(Note: our `application.properties` already has
`createDatabaseIfNotExist=true`, so technically Spring Boot will create
this for you automatically. But it's good practice to know how to do it
manually too.)

---

### Step 2: Configure your MySQL credentials

Open:
```
src/main/resources/application.properties
```

Update these two lines with YOUR actual MySQL username and password:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

---

### Step 3: Open the project in your IDE

- Open IntelliJ IDEA (recommended) or any IDE that supports Maven.
- Choose **File -> Open** -> select the `employee-management` folder.
- The IDE will automatically detect `pom.xml` and download all
  dependencies (this can take a minute or two the first time —
  it needs an internet connection).

---

### Step 4: Run the application

**Option A — From your IDE:**
Find `EmployeeManagementApplication.java` and click the green "Run" arrow
next to the `main` method.

**Option B — From terminal (inside the project folder):**
```bash
mvn spring-boot:run
```

**Option C — Build a JAR and run it:**
```bash
mvn clean package -DskipTests
java -jar target/employee-management-0.0.1-SNAPSHOT.jar
```

If everything is configured correctly, you'll see logs ending with
something like:
```
Tomcat started on port(s): 8080 (http)
Started EmployeeManagementApplication in X seconds
```

This means your API is live at: `http://localhost:8080`

Hibernate will automatically create the `employees` table inside your
`employee_db` database (because of `spring.jpa.hibernate.ddl-auto=update`).

---

### Step 5: Test with Postman

Import the included `Employee-Management-API.postman_collection.json`
file into Postman (File -> Import), and you'll have every endpoint ready
to test. Or manually test using the URLs below.

| Action | Method | URL |
|---|---|---|
| Add Employee | POST | http://localhost:8080/api/employees |
| Get All (paginated) | GET | http://localhost:8080/api/employees?page=0&size=5 |
| Get By ID | GET | http://localhost:8080/api/employees/1 |
| Update | PUT | http://localhost:8080/api/employees/1 |
| Delete | DELETE | http://localhost:8080/api/employees/1 |
| Search by Name | GET | http://localhost:8080/api/employees/search?name=john |

Sample JSON body for POST/PUT:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "department": "Engineering",
  "salary": 55000
}
```

---

## Common Issues

- **"Communications link failure" / can't connect to MySQL**
  -> Make sure MySQL server is actually running, and the
  username/password in `application.properties` are correct.

- **Port 8080 already in use**
  -> Change `server.port=8080` to e.g. `server.port=8081` in
  `application.properties`.

- **"Access denied for user 'root'@'localhost'"**
  -> Your MySQL password in `application.properties` is wrong.

- **Validation errors return raw default error pages**
  -> This is expected since we intentionally did NOT implement Global
  Exception Handling (kept the project simple as per requirements).
  Spring's default validation error response (400 Bad Request with a
  JSON body listing field errors) will still appear — that's normal.
