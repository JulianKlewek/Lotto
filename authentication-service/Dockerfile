#Authentication service dockerfile
#FROM maven:3.8.6-eclipse-temurin-17-alpine AS base
#FROM eclipse-temurin:21 AS base
FROM maven:3.8.8-eclipse-temurin-21-alpine AS base
WORKDIR /build
#COPY --chmod=0755 mvnw mvnw
#COPY .mvn/ .mvn/
#ENV PATH="/build:${PATH}"

FROM base AS test
RUN echo "Running UNIT TEST commands"
RUN ls -a
COPY src ./src
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2  \
    mvn -B test

FROM base AS deps
WORKDIR /build
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -Dskip.ut=true

FROM deps AS package
WORKDIR /build
COPY src ./src
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    mvn clean package -Dskip.ut=true && \
    ls -al target && \
    mv target/$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted && \
    ls -al target/extracted

FROM extract AS development
RUN echo "running development commands"
WORKDIR /build
RUN cp -r /build/target/extracted/dependencies/. ./  && \
    cp -r /build/target/extracted/spring-boot-loader/. ./  && \
    cp -r /build/target/extracted/snapshot-dependencies/. ./  && \
    cp -r /build/target/extracted/application/. ./
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001"
CMD [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]

FROM maven:3.8.8-eclipse-temurin-21-alpine AS final
#ARG UID=10001
#RUN adduser \
#    --disabled-password \
#    --gecos "" \
#    --home "/nonexistent" \
#    --shell "/sbin/nologin" \
#    --no-create-home \
#    --uid "${UID}" \
#    appuser
#USER appuser
COPY src ./src
COPY pom.xml .
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

