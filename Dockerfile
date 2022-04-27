FROM openjdk:18-alpine3.13 AS base

WORKDIR /opt/app
RUN apk update && apk add maven

COPY ./src ./src
COPY ./pom.xml ./
RUN mvn generate-sources && mvn -B -Ddocker.build.skip=true package test
RUN mvn -B -DskipTests package

# ENTRYPOINT ["pwd"]
# CMD ["watch", "/"]
ENTRYPOINT [ "mvn" ]
