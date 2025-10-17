CREATE TYPE order_status_enum AS ENUM (
  'PENDING',
  'CONFIRMED',
  'SHIPPED',
  'DELIVERED',
  'CANCELLED'
);

CREATE TYPE payment_status_enum AS ENUM (
  'PENDING',
  'PAID',
  'FAILED',
  'REFUNDED'
);

CREATE TYPE payment_method_enum AS ENUM (
  'COD',
  'ONLINE'
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2) NOT NULL,
    final_amount DECIMAL(10,2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    order_status order_status_enum NOT NULL DEFAULT 'PENDING',
    payment_status payment_status_enum NOT NULL DEFAULT 'PENDING',
    payment_method payment_method_enum NOT NULL DEFAULT 'COD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);