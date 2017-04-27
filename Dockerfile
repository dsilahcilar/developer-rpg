FROM maven:3.3.3-jdk-8
ADD . /src
WORKDIR /src
ENV CAR_DIR /src/src/main/resources/cards
ENV STORAGE_PATH /src/src/main/resources/storage/state.txt
RUN mvn clean package
CMD java -jar /src/target/*.jar