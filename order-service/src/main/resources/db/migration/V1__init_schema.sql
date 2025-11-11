-- Cần extension để tạo UUID tự động
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE order_status AS ENUM (
  'PENDING',
  'PAID',
  'APPROVED',
  'CANCELLING',
  'CANCELLED'
);

CREATE TABLE orders (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  customer_id UUID NOT NULL,
  restaurant_id UUID NOT NULL,
  tracking_id UUID NOT NULL,
  price NUMERIC(10,2) NOT NULL,
  order_status order_status NOT NULL DEFAULT 'PENDING',
  failure_messages VARCHAR,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE order_items (
  id BIGSERIAL PRIMARY KEY,
  order_id UUID NOT NULL,
  product_id UUID NOT NULL,
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
  sub_total NUMERIC(10,2) NOT NULL CHECK (sub_total >= 0),
  CONSTRAINT fk_order_id FOREIGN KEY (order_id)
      REFERENCES orders(id) ON DELETE CASCADE
);

CREATE INDEX idx_orders_tracking_id ON orders(tracking_id);
CREATE INDEX idx_orders_customer_id ON orders(customer_id);
