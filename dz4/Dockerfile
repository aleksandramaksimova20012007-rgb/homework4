FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY src/Main.java ./src/Main.java

RUN javac src/Main.java

CMD ["java", "-cp", "src", "Main"]