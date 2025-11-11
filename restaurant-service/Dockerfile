# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Cài Maven thủ công
RUN apk add --no-cache maven

# Copy pom.xml (tăng hiệu suất cache)
COPY pom.xml .

# Tải dependencies để tận dụng cache Docker
RUN mvn dependency:resolve -B -U

# Copy toàn bộ source code vào container
COPY . .

# Build project (tạo file JAR trong /app/target/)
RUN mvn clean package -DskipTests

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:21-jre-alpine

# Tạo user không phải root
RUN addgroup -g 1000 appgroup && adduser -u 1000 -G appgroup -s /bin/sh -D appuser

WORKDIR /app

# Copy file JAR từ stage build sang stage run
COPY --from=builder /app/target/*.jar app.jar

# Phân quyền cho user không root
RUN chown -R appuser:appgroup /app

# Chuyển sang user không root
USER appuser

# Mở cổng ứng dụng
EXPOSE 8083

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8083/actuator/health || exit 1

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]

