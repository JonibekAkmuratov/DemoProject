#!/bin/bash

echo "🔧 Manual Docker Fix - Step by Step"

# Stop everything first
echo "1. Stopping all services..."
docker-compose down --remove-orphans 2>/dev/null || true

# Check current Dockerfile
echo "2. Current Dockerfile content:"
head -5 Dockerfile

echo ""
echo "3. Creating new Dockerfile..."

# Create working Dockerfile
cat > Dockerfile << 'EOF'
# Multi-stage build using Eclipse Temurin
FROM eclipse-temurin:21-jdk AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Working directory
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

# Install packages for document conversion and curl
RUN apt-get update && apt-get install -y \
    libreoffice \
    fonts-dejavu-core \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Create app directory
WORKDIR /app

# Copy jar from build stage
COPY --from=build target/DemoProject.jar app.jar

# Create non-root user
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

echo "✅ New Dockerfile created"

# Remove version from docker-compose.yml
echo "4. Fixing docker-compose.yml..."
sed -i.bak '/^version:/d' docker-compose.yml
echo "✅ Version removed from docker-compose.yml"

# Verify .env file
echo "5. Checking .env file..."
if [ ! -f .env ]; then
    cat > .env << 'EOF'
POSTGRES_DB=demo
POSTGRES_USER=postgres
POSTGRES_PASSWORD=root123
JWT_SECRET=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
TELEGRAM_BOT_TOKEN=
TELEGRAM_CHAT_ID=
TELEGRAM_BOT_ENABLED=false
REDIS_PASSWORD=redis123
SPRING_PROFILES_ACTIVE=docker
APP_TIMEZONE=Asia/Tashkent
LOG_LEVEL=INFO
COMPOSE_PROJECT_NAME=demo-project
EOF
    echo "✅ .env file created"
else
    echo "✅ .env file exists"
fi

# Clean Docker cache
echo "6. Cleaning Docker cache..."
docker builder prune -f
docker system prune -f

# Start PostgreSQL first
echo "7. Starting PostgreSQL..."
docker-compose up -d postgres redis

# Wait for PostgreSQL
echo "8. Waiting for PostgreSQL to be ready..."
sleep 15

# Build and start app
echo "9. Building application (this may take 3-5 minutes)..."
docker-compose build --no-cache demo-app

echo "10. Starting application..."
docker-compose up -d demo-app

# Wait and check health
echo "11. Waiting for application to start..."
sleep 45

echo "12. Checking application health..."
for i in {1..20}; do
    if curl -s http://localhost:8080/actuator/health | grep -q "UP"; then
        echo "✅ Application is healthy!"
        break
    fi
    echo "⏳ Attempt $i/20 - waiting..."
    sleep 10
done

# Show final status
echo ""
echo "🎯 Final Status:"
docker-compose ps

echo ""
echo "📍 Application URLs:"
echo "🌍 Main App: http://localhost:8080"
echo "📚 Swagger: http://localhost:8080/swagger-ui.html"
echo "❤️ Health: http://localhost:8080/actuator/health"

echo ""
echo "🔍 Quick Test:"
curl -s http://localhost:8080/actuator/health || echo "❌ Application not responding yet"

echo ""
echo "📊 View logs: docker-compose logs -f demo-app"
echo "🛑 Stop all: docker-compose down"