FROM maven:3.6-alpine as dependencies
ADD target/discovery.jar ./target/
# собираем проект
RUN mkdir target/extracted && java -Djarmode=layertools -jar ./target/discovery.jar extract --destination target/extracted

FROM openjdk:8-jdk-alpine as build
#FROM tomcat:9.0.58-jre8-openjdk-slim as build
# копирем результат предыдущей стадии
COPY --from=dependencies ./target/extracted/dependencies/ ./
COPY --from=dependencies ./target/extracted/spring-boot-loader/ ./
COPY --from=dependencies ./target/extracted/snapshot-dependencies/ ./
COPY --from=dependencies ./target/extracted/application/ ./
ADD src/main/resources/application.yml ./BOOT-INF/classes/application.yml
ENV JAVA_IMG_OPTS="-Dspring.jmx.enabled=false -noverify -XX:TieredStopAtLevel=1 -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
ENV PORT=80
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} ${JAVA_IMG_OPTS} -Dspring.profiles.active=prod org.springframework.boot.loader.JarLauncher"]
