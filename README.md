# Enterprise Hybrid Automation Framework

![Enterprise Automation CI](https://github.com/mridul-980/enterprise-hybrid-automation-framework/actions/workflows/automation.yml/badge.svg)

> A production-ready Hybrid Test Automation Framework built using **Java, Selenium WebDriver, TestNG, Rest Assured, Maven, Docker, Docker Compose, and Selenium Grid**.

This framework supports **UI Automation**, **API Automation**, **Local & Remote Execution**, **Dockerized Test Execution**, and follows enterprise automation best practices with a scalable, maintainable architecture.

---

## 📌 Features

### ✅ UI Automation

- Selenium WebDriver
- Java 17
- TestNG
- Page Object Model (POM)
- ThreadLocal WebDriver
- Cross Browser Support
- Local & Remote Execution
- Selenium Grid Integration
- Docker Compose

---

### ✅ API Automation

- Rest Assured
- GET
- POST
- PUT
- PATCH
- DELETE
- Authentication
- Path Parameters
- Query Parameters
- Serialization
- Deserialization
- JSON Schema Validation

---

### ✅ Framework Capabilities

- Factory Design Pattern
- Runtime Configuration
- Environment-based Configuration
- Generic API Client
- Request & Response Specifications
- Data Driven Testing
- JSON Test Data
- Logging (Log4j2)
- Extent Reports
- Screenshots on Failure

---

### ✅ DevOps

- Git & GitHub
- Docker
- Docker Compose
- Selenium Grid
- Jenkins Ready

---

# 🏗 Framework Architecture

```text
                 BaseTest
                     │
             BrowserManager
                     │
             BrowserFactory
                     │
         ┌───────────┴───────────┐
         │                       │
    Local Driver           Remote Driver
         │                       │
 Chrome / Edge / Firefox    Selenium Grid
                                     │
                              Docker Container
```

---

# 🛠 Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 17 |
| UI Automation | Selenium WebDriver |
| API Automation | Rest Assured |
| Testing | TestNG |
| Build Tool | Maven |
| Design Pattern | Factory Pattern |
| Logging | Log4j2 |
| Reporting | Extent Reports |
| Containerization | Docker |
| Orchestration | Docker Compose |
| Grid | Selenium Grid |
| CI/CD | Jenkins |

---

# 📂 Project Structure

```text
enterprise-hybrid-automation-framework
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── api
│   │   │   ├── auth
│   │   │   ├── constants
│   │   │   ├── drivers
│   │   │   ├── factory
│   │   │   ├── listeners
│   │   │   ├── pages
│   │   │   ├── specs
│   │   │   └── utilities
│   │
│   ├── test
│   │   ├── java
│   │   │   ├── tests
│   │   │   └── testdata
│   │
│   └── resources
│       ├── config
│       ├── schemas
│       └── testdata
│
├── Dockerfile
├── docker-compose.yml
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

# ▶ Running Tests

## UI Tests (Local)

```bash
mvn clean test
```

---

## API Tests

```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng-api.xml
```

---

## Remote Execution using Selenium Grid

Update:

```properties
execution=remote
```

Start Selenium Grid

```bash
docker compose up -d
```

Execute Tests

```bash
mvn clean test
```

---

# 🐳 Docker

## Build Docker Image

```bash
docker build -t enterprise-hybrid-automation-framework .
```

## Execute API Tests

```bash
docker run --rm enterprise-hybrid-automation-framework
```

---

# 🌐 Selenium Grid

Start Grid

```bash
docker compose up -d
```

Open

```
http://localhost:4444
```

---

# 📊 Reports

- Extent Reports
- TestNG Reports
- Screenshots on Failure
- Log4j2 Logs

---

# 🎯 Design Patterns Used

- Factory Pattern
- Page Object Model (POM)
- Singleton (Configuration Management)
- ThreadLocal Driver Management

---

# 🚀 Future Enhancements

- Parallel Cross-Browser Execution
- GitHub Actions CI/CD
- Allure Reporting
- Browser Matrix Execution
- Database Testing
- Performance Testing
- Cloud Selenium Grid
- AI-powered Test Reporting

---

# 👨‍💻 Author

**Mridul Tripathi**

Software QA Engineer | SDET | Test Automation Engineer

GitHub:

https://github.com/mridul-980/enterprise-hybrid-automation-framework

---

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.