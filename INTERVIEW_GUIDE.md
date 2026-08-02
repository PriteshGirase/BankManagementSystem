# Technical Interview Guide - Bank Management System (BMS)

Use this guide to confidently explain, demonstrate, and answer technical interview questions about this Bank Management System project during software engineering job interviews.

---

## 🎯 1. Project 60-Second Elevator Pitch

> *"The Bank Management System is an enterprise-ready Java application designed to simulate automated teller machine (ATM) transactions and bank account lifecycle management. It features multi-step account registration, card generation, pin authentication, real-time balance calculations, deposit/withdrawal transaction processing, mini statements, and pin changes.*
>
> *I modernized this codebase by eliminating legacy SQL Injection vulnerabilities using parameterized PreparedStatements, introducing a dual-database connectivity architecture (MySQL with an automated embedded H2 fallback for zero-setup execution), incorporating Maven build automation, and applying clean coding principles."*

---

## 🏗️ 2. Architectural Overview & Key Design Decisions

### **Architecture Layering**
- **Presentation Layer**: Java Swing UI (`Login`, `Signup`, `Deposit`, `Withdrawl`, `FastCash`, `BalanceEnquriy`, `Pin`, `mini`) styled with FlatLaf modern Look & Feel.
- **Database Abstraction / Connection Layer**: `Connn.java` managing database connections, driver initialization, properties parsing, schema auto-creation, and parameterized statements.
- **Persistence Layer**: Relational Database Engine (MySQL / H2 Database) storing multi-table bank records (`signup`, `signuptwo`, `signupthree`, `login`, `bank`).

### **Design Patterns Used**
1. **Singleton Connection Pattern**: Connection logic centralized inside `Connn.java`.
2. **Factory & Strategy Pattern**: Dynamic selection of database driver (H2 vs MySQL) based on environment configuration in `database.properties`.
3. **Event-Driven Architecture**: Swing `ActionListener` handling user interactions asynchronously on the Event Dispatch Thread (EDT).

---

## 🛡️ 3. Major Security & Code Improvements

### **1. Fixing SQL Injection Vulnerabilities**
- **Legacy Flaw**: The original codebase used raw string concatenation (`"select * from login where card_number = '" + cardno + "' and pin = '" + pin + "'"`), making it susceptible to SQL Injection attacks (e.g. entering `' OR '1'='1`).
- **Your Fix**: Refactored all data access code across 10+ classes to use `PreparedStatement` parameterized placeholders (`?`). This ensures input parameters are sanitized and treated strictly as literals by the SQL query planner.

### **2. Resilient Zero-Setup Database Fallback**
- Added embedded H2 database support with automated `schema.sql` execution. If MySQL server is unavailable, the system automatically falls back to H2 so the demo never fails during a live interview evaluation!

---

## ❓ 4. Top 10 Interview Questions & Model Answers

### **Q1: What architecture does this project follow?**
**Answer:** It follows a 2-tier client-database architecture leveraging Java Swing for presentation and JDBC for database interaction. The connection logic is decoupled into a dedicated `Connn` utility class, and configuration is externalized into `database.properties`.

### **Q2: How did you prevent SQL Injection in this application?**
**Answer:** I replaced raw string concatenation inside `Statement.executeQuery()` with parameterized `PreparedStatement` objects across all forms (`Login`, `Deposit`, `Withdrawal`, etc.). Parameterization forces the database driver to compile the SQL command structure first before binding user input as data types rather than executable code.

### **Q3: How are account balances calculated in the `bank` table?**
**Answer:** Account balance is dynamically derived by aggregating historical ledger entries for a given account PIN:
$$\text{Balance} = \sum \text{Deposits} - \sum \text{Withdrawals}$$
This ensures an immutable audit trail of every transaction date, type, and amount.

### **Q4: How would you handle concurrent withdrawal requests on the same account?**
**Answer:** In a high-concurrency production environment, I would use **ACID database transactions** with pessimistic locking (`SELECT ... FOR UPDATE`) or optimistic locking with version numbers. In JDBC, we would set `connection.setAutoCommit(false)`, execute the balance check and withdrawal insert within a transaction block, and call `connection.commit()` or `connection.rollback()` upon failure.

### **Q5: Why did you use H2 database alongside MySQL?**
**Answer:** H2 is a lightweight, zero-dependency embedded Java SQL database. By implementing automatic H2 fallback in `Connn.java`, the project can run instantly without requiring manual MySQL server configuration, while still supporting MySQL for production environments.

### **Q6: How are card numbers and PINs generated during registration?**
**Answer:** Account registration is a 3-step workflow (`Signup` -> `Signup2` -> `Signup3`). Upon final submission in `Signup3`, pseudo-random 16-digit card numbers and 4-digit PINs are dynamically generated using `java.util.Random`, linked to the user's `formno`, and stored in `signupthree` and `login` tables.

### **Q7: How do Swing threading models work, and why is Event Dispatch Thread (EDT) important?**
**Answer:** Swing is single-threaded. UI creation and updates must occur on the Event Dispatch Thread (EDT). We use `SwingUtilities.invokeLater()` in `main()` to safely instantiate frame windows without causing race conditions or deadlocks.

### **Q8: What improvements would you make if converting this project to modern enterprise Java?**
**Answer:**
1. Migrate the backend to **Spring Boot 3** REST API (`@RestController`, `@Service`, `@Repository`).
2. Replace raw JDBC with **Spring Data JPA / Hibernate** ORM.
3. Hash PIN passwords using **BCrypt** instead of storing plain text.
4. Replace Swing desktop frontend with a modern **React / Next.js** single page web application.

### **Q9: How do you handle numeric validation for deposit/withdrawal amounts?**
**Answer:** Inputs are validated using `Double.parseDouble()` / `Integer.parseInt()` wrapped in `try-catch (NumberFormatException)`. Additional domain checks ensure amounts are strictly positive and do not exceed predefined ATM withdrawal limits (e.g. Rs. 10,000 max per transaction).

### **Q10: What build system does the project use?**
**Answer:** The project uses **Apache Maven** (`pom.xml`) for dependency management (MySQL Driver, H2, FlatLaf, JCalendar) and automated compilation (`mvn compile`, `mvn exec:java`).
