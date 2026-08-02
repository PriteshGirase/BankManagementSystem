# Step-by-Step IntelliJ IDEA Setup Guide for Bank Management System (BMS)

Follow this guide to open, configure, and run the **Bank Management System** in IntelliJ IDEA (Community or Ultimate edition).

---

## 🚀 Option A: Open as Maven Project (Recommended)

Since the project includes a standard `pom.xml`, IntelliJ IDEA can automatically import all dependencies (MySQL Connector, H2 Database, FlatLaf Theme, JCalendar) without manual JAR configuration.

### Step 1: Open Project in IntelliJ IDEA
1. Launch **IntelliJ IDEA**.
2. Click **Open** (or `File > Open...`).
3. Select the folder:
   `C:\Users\prite\.gemini\antigravity\scratch\BMS\Bank-Management-System--master\Bank-Management-System--master`
4. Click **OK**.
5. If prompted, select **Trust Project**.

### Step 2: Ensure SDK / JDK is Selected
1. Open **File > Project Structure...** (or press `Ctrl + Alt + Shift + S`).
2. Go to **Project** settings on the left tab.
3. Under **SDK**, select **JDK 17** (or JDK 8/11/21 if 17 is unavailable).
4. Under **Project language level**, select **17 - SDK default** (or matching your JDK).
5. Click **Apply** and **OK**.

### Step 3: Run the Application
1. In the Project Explorer on the left, navigate to:
   `src/main/java/bank/management/system/Login.java`
2. Right-click on `Login.java`.
3. Select **Run 'Login.main()'** (or press `Shift + F10`).
4. The ATM Login window will appear on screen!

---

## 🛠️ Option B: Open as Standard Java Project (Using Included `.jar` Files)

If you prefer not to use Maven:

### Step 1: Open Project
1. Go to `File > Open...` and select the project folder.

### Step 2: Add External JAR Libraries
1. Open **File > Project Structure...** (`Ctrl + Alt + Shift + S`).
2. Click on **Libraries** under *Project Settings*.
3. Click the `+` (Plus icon) -> Select **Java**.
4. Navigate to the `Jar` folder inside the project directory:
   `Jar/jcalendar-1.4.jar`
   `Jar/mysql-connector-j-8.3.0.jar`
5. Click **OK** to add both libraries.
6. Click **Apply** and **OK**.

### Step 3: Mark Source Folder
1. If `src/main/java` is not highlighted in blue:
   - Right-click `src/main/java` folder in Project Explorer.
   - Select **Mark Directory as > Sources Root**.

### Step 4: Run
- Right-click `Login.java` -> **Run 'Login.main()'**.

---

## 🗄️ Database Configuration (Zero-Setup vs MySQL)

### Zero Setup Mode (Default - H2 Embedded Database)
- The project is configured out-of-the-box to use an embedded **H2 Database**.
- It requires **NO MySQL installation** or local server running!
- Database files (`bms_db.mv.db`) and table schemas are created automatically on first run via `schema.sql`.

### MySQL Production Mode (Optional)
If you want to run against a local MySQL database:
1. Open `src/main/resources/database.properties`.
2. Change `db.mode` from `h2` to `mysql`:
   ```properties
   db.mode=mysql
   mysql.url=jdbc:mysql://localhost:3306/bank?createDatabaseIfNotExist=true
   mysql.user=root
   mysql.password=YOUR_MYSQL_PASSWORD
   ```
3. Start your local MySQL service. The app will automatically connect and create required tables.

---

## 🧪 Testing Account Credentials

- Click **SIGN UP** on the Login screen to create a new bank account with personal details, income, and account type.
- Note down the generated **Card Number** (16 digits) and **PIN** (4 digits) displayed in the pop-up dialog upon registration!
- Use these credentials to sign in and test:
  - Deposit
  - Cash Withdrawal
  - Fast Cash
  - Balance Enquiry
  - Mini Statement
  - PIN Change
