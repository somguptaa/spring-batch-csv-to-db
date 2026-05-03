# 📦 Spring Batch CSV to Database (MySQL)

A **Spring Batch** application that reads data from a CSV file and writes it into a **MySQL database** using efficient batch processing.

---

## 🚀 Features

* Read large CSV files using Spring Batch
* Process and transform data before insertion
* Store data in MySQL database
* Chunk-based processing for high performance
* Fault-tolerant with skip and retry support
* Easy configuration using Spring Boot

---

## 🛠️ Tech Stack

* Java 8+
* Spring Boot
* Spring Batch
* Spring Data JPA / JDBC
* MySQL
* Maven / Gradle

---

## 📂 Project Structure

```
spring-batch-csv-to-db/
│
├── src/main/java/com/batch
│   ├── config/          # Batch configuration
│   ├── model/           # Entity classes
│   ├── processor/       # Item processors
│   ├── reader/          # CSV reader
│   ├── writer/          # DB writer
│   └── BatchApplication.java
│
├── src/main/resources/
│   ├── application.yml
│   └── data.csv
│
└── pom.xml
```

---

## ⚙️ Configuration

### 1. CSV File

Place your CSV file in:

```
src/main/resources/data.csv
```

Example:

```csv
id,name,email
1,John Doe,john@example.com
2,Jane Doe,jane@example.com
```

---

### 2. MySQL Database Setup

Create a database in MySQL:

```sql
CREATE DATABASE batch_db;
```

---

### 3. Application Properties (MySQL)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/batch_db
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  batch:
    jdbc:
      initialize-schema: always
```

---

## 🔄 Batch Job Flow

1. **ItemReader** → Reads data from CSV
2. **ItemProcessor** → Transforms data
3. **ItemWriter** → Writes data into MySQL

---

## 🧩 Key Components

### 📖 Item Reader

Uses `FlatFileItemReader` to read CSV file.

### 🔄 Item Processor

Handles validation and transformation of records.

### 💾 Item Writer

Writes data into MySQL using:

* `JpaItemWriter`
* `JdbcBatchItemWriter`

---

## ⚡ Performance Tips

* Tune chunk size:

```java
.chunk(100)
```

* Use indexing on frequently queried columns
* Enable batch inserts in MySQL

---

## 📌 Future Improvements

* REST API to trigger batch jobs
* Scheduler (Cron-based execution)
* Support for multiple file formats (Excel/JSON)
* Dockerize the application

---

## 🤝 Contributing

Feel free to fork and submit pull requests.

---

## 📄 License

This project is free not under any License.
