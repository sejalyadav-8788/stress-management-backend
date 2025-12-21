FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY target/*.jar app.jar

# Railway injects PORT — we must use it
ENV PORT=8080
EXPOSE ${PORT}

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
