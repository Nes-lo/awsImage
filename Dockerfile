
FROM maven:3.9

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp


RUN mvn clean install

CMD ["java","-jar","target/ProjectExam-1.0-SNAPSHOT.jar"]
