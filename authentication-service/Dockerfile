#Authentication service dockerfile
FROM maven:3.8.6-eclipse-temurin-17-alpine AS base
WORKDIR /build
COPY src ./src

FROM base AS test
RUN echo "Running TEST commands" && \
    --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2  \
    mvn -B test

FROM base AS deps
WORKDIR /build
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -DskipTests

FROM deps AS package
WORKDIR /build
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests && \
    ls -al target && \
    cp target/*.jar target/app.jar

FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

FROM extract AS development
RUN echo "running development commands"
WORKDIR /build
RUN cp -r /build/target/extracted/dependencies/. ./  && \
    cp -r /build/target/extracted/spring-boot-loader/. ./  && \
    cp -r /build/target/extracted/snapshot-dependencies/. ./  && \
    cp -r /build/target/extracted/application/. ./
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001"
CMD [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]

FROM eclipse-temurin:17.0.6_10-jre-alpine AS final
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

#FROM eclipse-temurin:17.0.6_10-jre-alpine AS layers
#WORKDIR /layer
#ARG JAR_FILE=authentication-service-1.0-SNAPSHOT.jar
#COPY --from=base /app/target/$JAR_FILE app.jar
#RUN echo "running LAYERS commands"
#RUN java -Djarmode=layertools -jar app.jar extract

#FROM eclipse-temurin:17.0.6_10-jre-alpine
#RUN echo "running FINAL commands"
#WORKDIR /application
#COPY --from=layers layer/dependencies ./
#COPY --from=layers layer/spring-boot-loader ./
#COPY --from=layers layer/snapshot-dependencies ./
#COPY --from=layers layer/application ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]