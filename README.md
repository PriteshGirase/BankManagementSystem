# 🏦 Bank Management System (BMS) - ATM GUI Application

A modernized, enterprise-grade **Bank Management System & ATM Simulator** built with **Java Swing**, **JDBC**, **MySQL / H2 Embedded Database**, and **Maven**.

---

## ✨ Features & Upgrades

- 🔒 **SQL Injection Security Fixes**: Parameterized SQL queries using `PreparedStatement` across all modules.
- ⚡ **Zero-Setup Embedded H2 Fallback**: Runs instantly out of the box with zero database configuration required. Also supports standard MySQL.
- 🛠️ **Maven Integration**: Manage dependencies cleanly and build with single commands.
- 🎨 **Modern Swing UI**: Styled with FlatLaf dark theme for a sleek presentation.
- 📋 **Full ATM Workflow**:
  - Multi-page Signup & Account Creation
  - Card & PIN Generation
  - Authenticated Login
  - Deposit & Withdrawal with Balance Constraints
  - Fast Cash & Balance Enquiry
  - Transaction Mini Statements
  - PIN Change

---

## 📁 Project Structure

```
Bank-Management-System/
├── pom.xml                        # Maven configuration & dependencies
├── README.md                      # General project documentation
├── INTELLIJ_SETUP_GUIDE.md        # Step-by-step IntelliJ IDEA setup guide
├── INTERVIEW_GUIDE.md             # Technical interview talking points & Q&A
├── Jar/                           # Pre-packaged JAR dependencies
└── src/
    └── main/
        ├── java/
        │   └── bank/management/system/
        │       ├── Connn.java              # Database Connection & Fallback Manager
        │       ├── Login.java              # Main Entry Point & Authentication
        │       ├── Signup.java             # Registration Page 1 (Personal Info)
        │       ├── Signup2.java            # Registration Page 2 (Additional Info)
        │       ├── Signup3.java            # Registration Page 3 (Account & Services)
        │       ├── main_Class.java         # ATM Transaction Dashboard
        │       ├── Deposit.java            # Money Deposit Screen
        │       ├── Withdrawl.java          # Cash Withdrawal Screen
        │       ├── FastCash.java           # Quick Withdrawal Options
        │       ├── BalanceEnquriy.java     # Real-time Balance Checking
        │       ├── mini.java               # Transaction Mini Statement
        │       └── Pin.java                # Security PIN Change
        └── resources/
            ├── database.properties         # Database connection settings (H2 / MySQL)
            ├── schema.sql                  # Automated database table DDL script
            └── icon/                       # UI Image assets and logos
```

---

## 🚀 How to Run in IntelliJ IDEA

Refer to [INTELLIJ_SETUP_GUIDE.md](file:///C:/Users/prite/.gemini/antigravity/scratch/BMS/Bank-Management-System--master/Bank-Management-System--master/INTELLIJ_SETUP_GUIDE.md) for full screenshots and detailed instructions.

1. Open **IntelliJ IDEA** -> Click **Open** -> Select the project root folder.
2. Select **JDK 17** under `File > Project Structure... > Project`.
3. Open `src/main/java/bank/management/system/Login.java`.
4. Right-click `Login.java` -> **Run 'Login.main()'**.

---

## 📚 Interview Preparation

Refer to [INTERVIEW_GUIDE.md](file:///C:/Users/prite/.gemini/antigravity/scratch/BMS/Bank-Management-System--master/Bank-Management-System--master/INTERVIEW_GUIDE.md) for:
- 60-Second Elevator Pitch
- Architecture Diagrams & Design Patterns (Singleton, Factory, Event-Driven)
- Security Fixes Explanation (PreparedStatement vs Raw Queries)
- Top 10 Technical Interview Questions & Answers tailored for this project
