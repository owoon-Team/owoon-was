FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

RUN chmod +x ./gradlew && ./gradlew --no-daemon dependencies

COPY src ./src

RUN ./gradlew --no-daemon bootJar -x test

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

ENV TZ=Asia/Seoul
ENV JAVA_OPTS="-Xms128m -Xmx512m"

COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
