# Start with a base image containing Java (version 17)
FROM openjdk:17-jdk-alpine

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the app directory in the container
WORKDIR /app

# Copy the application's source code from the current directory into the working directory in Docker
COPY . /app

## Install Maven via apk
#RUN apk add maven
#
## Package the application
#RUN mvn clean package -DskipTests

# Specify the entry point
ENTRYPOINT ["java", "-jar", "target/fileupload-0.0.1-SNAPSHOT.jar"]