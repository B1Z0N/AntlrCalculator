FROM openjdk:18-alpine3.13 AS base

WORKDIR /opt/app
RUN apk update && apk add curl # && apk add --no-cache bash 
RUN curl "https://www.antlr.org/download/antlr-4.10.1-complete.jar" -o antlr4.jar
ENV CLASSPATH=".:/opt/app/antlr4.jar:${CLASSPATH}"

COPY ./src/* ./
RUN java -jar antlr4.jar -no-listener -visitor Calculator.g4
RUN javac *.java

ENTRYPOINT ["java", "MainVisitor"]

# not to forget, grun is here
# alias grun='java org.antlr.v4.gui.TestRig'"
