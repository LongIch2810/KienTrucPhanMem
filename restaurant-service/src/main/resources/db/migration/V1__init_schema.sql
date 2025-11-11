-- Kích hoạt UUID random cho PostgreSQL
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE order_approval_status AS ENUM (
  'PENDING',   -- chờ xác nhận đơn
  'APPROVED',  -- đơn được chấp nhận
  'REJECTED'   -- từ chối vì hết hàng hoặc nhà hàng ngừng hoạt động
);

CREATE TYPE restaurant_status AS ENUM (
  'ACTIVE',
  'INACTIVE'
);

CREATE TABLE restaurants (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  owner_id UUID NOT NULL,
  status restaurant_status NOT NULL DEFAULT 'ACTIVE',
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE menu_items (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  restaurant_id UUID NOT NULL,
  name VARCHAR(255) NOT NULL,
  price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
  quantity INT NOT NULL CHECK (quantity >= 0),
  available BOOLEAN NOT NULL DEFAULT TRUE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE restaurant_orders (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  order_id UUID NOT NULL,
  restaurant_id UUID NOT NULL,
  total_amount NUMERIC(10,2) NOT NULL CHECK (total_amount >= 0),
  approval_status order_approval_status NOT NULL DEFAULT 'PENDING',
  rejection_reason TEXT,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE INDEX idx_restaurant_orders_order_id ON restaurant_orders(order_id);
CREATE INDEX idx_restaurant_orders_restaurant_id ON restaurant_orders(restaurant_id);
