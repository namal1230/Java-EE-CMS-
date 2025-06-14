
# 🌟 **Complaint Management System (CMS) – Project Overview**

### 📌 **Project Context**

This Complaint Management System (CMS) is a **research-driven, individual development assignment** for the Municipal IT Division, focused on applying **Jakarta EE and JSP** in a real-world scenario. The system allows **internal employees** and **administrative staff** to:

* **Submit complaints**
* **Track complaint status**
* **Manage and resolve complaints**

All interactions are handled through **synchronous HTTP GET/POST form submissions**, adhering to classic web application standards without the use of AJAX or asynchronous mechanisms.

---

### 📌 **Core Functionalities**

#### 🔑 **Authentication Module**

* User login with session management (maintain logged-in state).
* Role-based access:

    * **Employee:** Submit, view, edit, delete own unresolved complaints.
    * **Admin:** View all complaints, update status/remarks, delete any complaint.

#### 🔑 **Complaint Management**

* **Employee**

    * Submit a new complaint (via form POST).
    * View submitted complaints (via form GET).
    * Edit / delete own complaints if not resolved (via POST for state change).

* **Admin**

    * View list of all complaints (GET).
    * Update complaint status (In Progress, Resolved, Closed) + add remarks (POST).
    * Delete any complaint (POST).

---

### 📌 **Technology Stack**

| Component                | Technology                                                   |
| ------------------------ | ------------------------------------------------------------ |
| **Frontend (View)**      | JSP, HTML, CSS, JavaScript (for client-side validation only) |
| **Backend (Controller)** | Jakarta EE (Servlets)                                        |
| **Model (Data)**         | JavaBeans (POJOs), DAO classes                               |
| **Database**             | MySQL (via Apache Commons DBCP connection pooling)           |
| **Server**               | Apache Tomcat                                                |

---

### 📌 **Architectural Pattern**

➡ **MVC (Model-View-Controller)**

* **View:** JSP pages render UI and display data.
* **Controller:** Servlets handle request routing, form submissions, business logic.
* **Model:** JavaBeans + DAO layer handle data persistence and database operations.

---

### 📌 **System Flow**

```plaintext
JSP Form (HTML GET/POST)
    ↓
Servlet (Controller logic)
    ↓
DAO (Database CRUD via DBCP connection pool)
    ↓
Redirect / forward to JSP (View)
```

✅ SQL dump will be provided at:
`/db/schema.sql`


---

# ⚙️ **Setup and Configuration Guide**

This guide explains how to set up, configure, and run the **Complaint Management System (CMS)** prototype locally.

---

## 1️⃣ **Prerequisites**

Ensure the following software is installed on your system:

| Tool              | Recommended Version                  |
| ----------------- |--------------------------------------|
| **Java JDK**      | 11+                                  |
| **Apache Tomcat** | 10.x (Jakarta EE compatible)         |
| **MySQL Server**  | 8.x                                  |
| **Git**           | Latest                               |
| **IDE**           | IntelliJ IDEA                        |
| **Maven**         | (optional, if using Maven for build) |

---

## 2️⃣ **Clone the Repository**

Clone your public GitHub repository:

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

---

## 3️⃣ **Database Setup**

1️⃣ Start your MySQL server.

2️⃣ Open your MySQL client (e.g., MySQL Workbench, command-line).

3️⃣ Create a database:

```sql
CREATE DATABASE cms;
```

4️⃣ Import the provided SQL dump:

```bash
mysql -u <your-username> -p cms_db < db/schema.sql
```

👉 Replace `<your-username>` with your MySQL username.

---

## 4️⃣ **Configure Database Connection (DBCP)**

Update the **DBCP configuration** in your `DBCP.java`:

```java
BasicDataSource ds = new BasicDataSource();
ds.setUrl("jdbc:mysql://localhost:3306/cms_db");
ds.setUsername("your_db_username");
ds.setPassword("your_db_password");
ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
```

✅ Ensure MySQL JDBC driver JAR is in your project’s `lib` folder (or added via Maven if using).

---

## 5️⃣ **Project Build & Deployment**

1️⃣ Open your project in your IDE.

2️⃣ Configure your server:

* Add Apache Tomcat server in your IDE.
* Set deployment artifact (WAR or exploded directory).

3️⃣ Deploy the app:

* Right-click project → *Run on Server* (in IntelliJ)
* OR manually copy the WAR file to `TOMCAT_HOME/webapps`

---

## 6️⃣ **Run the Application**

🌐 Open your browser:

```
http://localhost:8080/<your-app-context>/
```

Example:

```
http://localhost:8080/cms/
```

---

## 7️⃣ **Default Users (for testing)**

> Insert into DB manually or include in `schema.sql`:

```sql
INSERT INTO users (username, password, role) VALUES
('employee1', 'password123', 'EMPLOYEE'),
('admin1', 'adminpass', 'ADMIN');
```

⚠️ *Note: Passwords are stored as plain text for prototype simplicity. Hash them in production.*

---

## 8️⃣ **Directory Structure**

Example structure:

```
/src
  /controller      → Servlets
  /model           → JavaBeans, DAO classes
  /view            → JSP files
  /db              → DB utilities, DBCP config
/web
  /css
  /js
  /WEB-INF
    web.xml
/db
  schema.sql
README.md
```

---

## 9️⃣ **Login**

📝 Navigate to the login page:

```
http://localhost:8080/cms/index.jsp
```

✅ Test logins:

* **Employee:** `employee1 / password123`
* **Admin:** `admin1 / adminpass`

---

## 10️⃣ **Troubleshooting**

❗ Common issues:

* *DB connection failed?* → Check URL, username/password, MySQL server status.
* *App not starting?* → Verify WAR deployed correctly. Check Tomcat logs.
* *JDBC driver missing?* → Download MySQL Connector/J and add to `lib`.

---

## 11️⃣ **Version Control**

This project is under Git version control. Use:

```bash
git status
git add .
git commit -m "Your message"
git push origin main
```

---

### ✅ **Final Notes**

* All interactions use synchronous form-based GET/POST (no AJAX).
* System follows MVC: JSP → Servlet → DAO → DB.
* Compatible with any Jakarta EE-enabled server.

---




