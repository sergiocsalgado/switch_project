# Base image
FROM node:18.15.0 as frontend-build

# Set the working directory within the container image
WORKDIR /app

# Copy all web application files to container image
COPY ./web_application/ ./

# Install dependencies
RUN npm ci

# Build
RUN npm run build

# Expose port to access the server
EXPOSE 3000

# Command to run the app
CMD [ "npx", "serve", "build" ]
