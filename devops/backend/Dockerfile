# Base image
FROM maven:3.9.1 as builder

# Set the working directory within the container image
WORKDIR /app

# Copy the source code to the container
COPY ./ ./

# Build the project
RUN mvn package -DskipTests

# Create the final container
FROM tomcat:9.0.54-jdk11

# Remove the default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to Tomcat webapps directory
COPY --from=builder /app/target/switch2022project-g5-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port to access the server
EXPOSE 8080

# Command to run the app
CMD ["catalina.sh", "run"]
