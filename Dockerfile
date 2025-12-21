FROM eclipse-temurin:17-jdk-alpine

# Force rebuild - Railway fix
WORKDIR /app
COPY target/*.jar app.jar

# Railway injects PORT dynamically - don't override it
EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]