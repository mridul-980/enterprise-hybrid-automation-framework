# Base Image
FROM eclipse-temurin:17-jdk

# Working Directory
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Copy only pom.xml first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy remaining project
COPY . .

# Execute API Tests
CMD ["mvn","clean","test","-Dsurefire.suiteXmlFiles=testng-api.xml"]