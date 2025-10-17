# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:21-jdk-alpine AS builder

# Làm việc trong thư mục /app
WORKDIR /app

# 🛠️ Cài Maven thủ công vì image này KHÔNG có Maven sẵn
RUN apk add --no-cache maven

# Copy pom.xml (tăng hiệu suất cache)
COPY pom.xml .

# Nếu bạn có nhiều module thì copy các pom con, còn nếu chỉ 1 pom thì bỏ các dòng dưới đi
# COPY order-domain/pom.xml order-domain/
# COPY order-application-service/pom.xml order-application-service/
# COPY order-dataaccess/pom.xml order-dataaccess/
# COPY order-messaging/pom.xml order-messaging/
# COPY order-container/pom.xml order-container/

# Tải dependencies để tận dụng cache Docker
RUN mvn dependency:go-offline -B

# Copy toàn bộ source code vào container
COPY . .

# Build project (tạo file JAR trong /app/target/)
RUN mvn clean package -DskipTests

# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:21-jre-alpine

# Tạo user không phải root
RUN addgroup -g 1000 appgroup && adduser -u 1000 -G appgroup -s /bin/sh -D appuser

# Làm việc trong /app
WORKDIR /app

# Copy file JAR từ stage build sang stage run
# Đổi tên thành app.jar để dễ chạy (bạn có thể thay tên nếu muốn)
COPY --from=builder /app/target/*.jar app.jar

# Phân quyền cho user không root
RUN chown -R appuser:appgroup /app

# Chuyển sang user không root
USER appuser

# Mở cổng ứng dụng (Spring Boot mặc định là 8080, nếu bạn dùng 8181 thì sửa lại)
EXPOSE 8000

# Health check — kiểm tra endpoint /actuator/health mỗi 30s
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8000/actuator/health || exit 1

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
