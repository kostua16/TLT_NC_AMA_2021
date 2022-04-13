FROM maven:3.6-alpine as dependencies
ADD target/guests.jar ./target/
# собираем проект
RUN mkdir target/extracted && java -Djarmode=layertools -jar ./target/guests.jar extract --destination target/extracted

FROM openjdk:8-jdk-alpine as build
#FROM tomcat:9.0.58-jre8-openjdk-slim as build
# копирем результат предыдущей стадии
COPY --from=dependencies ./target/extracted/dependencies/ ./
COPY --from=dependencies ./target/extracted/spring-boot-loader/ ./
COPY --from=dependencies ./target/extracted/snapshot-dependencies/ ./
COPY --from=dependencies ./target/extracted/application/ ./
ENV JAVA_IMG_OPTS="-Dspring.jmx.enabled=false -noverify -XX:TieredStopAtLevel=1 -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
ENV PORT=8761
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} ${JAVA_IMG_OPTS} org.springframework.boot.loader.JarLauncher"]
