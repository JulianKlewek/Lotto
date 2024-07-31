#Lotto engine dockerfile
FROM maven:3.8.6-eclipse-temurin-17-alpine AS BUILDER
WORKDIR app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17.0.6_10-jre-alpine AS LAYERS
WORKDIR layer
ARG JAR_FILE=lotto-engine-1.0-SNAPSHOT.jar
COPY --from=BUILDER /app/target/$JAR_FILE app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17.0.6_10-jre-alpine
WORKDIR application
COPY --from=LAYERS layer/dependencies ./
COPY --from=LAYERS layer/spring-boot-loader ./
COPY --from=LAYERS layer/snapshot-dependencies ./
COPY --from=LAYERS layer/application ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]