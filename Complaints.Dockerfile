FROM maven:3.6-alpine as dependencies
ADD pom.xml ./
ADD code_quality/pom.xml ./code_quality
ADD projects/pom.xml ./projects
ADD projects/hotel_automation_project/pom.xml ./projects/hotel_automation_project
ADD projects/hotel_automation_project/bank_service/pom.xml ./projects/hotel_automation_project/bank_service
ADD projects/hotel_automation_project/booking_service/pom.xml ./projects/hotel_automation_project/booking_service
ADD projects/hotel_automation_project/common/pom.xml ./projects/hotel_automation_project/common
ADD projects/hotel_automation_project/complaint_handling_service/pom.xml ./projects/hotel_automation_project/complaint_handling_service
ADD projects/hotel_automation_project/discovery_evr/pom.xml ./projects/hotel_automation_project/discovery_evr
ADD projects/hotel_automation_project/gateway/pom.xml ./projects/hotel_automation_project/gateway
ADD projects/hotel_automation_project/guest_service/pom.xml ./projects/hotel_automation_project/guest_service
ADD projects/hotel_automation_project/staff_service/pom.xml ./projects/hotel_automation_project/staff_service
# устанавливаем зависимости проекта
RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies -ntp
ADD projects/hotel_automation_project/common/src ./projects/hotel_automation_project/common/src
ARG BUILD_MOD
ADD projects/hotel_automation_project/${BUILD_MOD}/src ./projects/hotel_automation_project/${BUILD_MOD}/src
# собираем проект
RUN mvn -pl projects/hotel_automation_project/common,projects/hotel_automation_project/${BUILD_MOD} compile -DskipTests=true && mkdir target/extracted && java -Djarmode=layertools -jar projects/hotel_automation_project/${BUILD_MOD}/target/*.jar extract --destination target/extracted

FROM openjdk:8-jdk-alpine as build
#FROM tomcat:9.0.58-jre8-openjdk-slim as build
# копирем результат предыдущей стадии
COPY --from=dependencies ./target/extracted/dependencies/ ./
COPY --from=dependencies ./target/extracted/spring-boot-loader/ ./
COPY --from=dependencies ./target/extracted/snapshot-dependencies/ ./
COPY --from=dependencies ./target/extracted/application/ ./
ENV JAVA_IMG_OPTS="-Dspring.jmx.enabled=false -noverify XX:TieredStopAtLevel=1 -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
ENV PORT=8085
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} ${JAVA_IMG_OPTS} org.springframework.boot.loader.JarLauncher"]
