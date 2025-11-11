-- Cho phép PostgreSQL sinh UUID ngẫu nhiên
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE payment_status AS ENUM (
  'PENDING',
  'COMPLETED',
  'FAILED',
  'CANCELLED',
  'REFUNDED'
);

CREATE TYPE payment_method AS ENUM (
  'ONLINE',
  'COD'
);

CREATE TABLE payments (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

  -- mapping sang order-service
  order_id UUID NOT NULL,
  customer_id UUID NOT NULL,

  -- thêm: nhà hàng nhận đơn (để bắn event ORDER_PAID cho restaurant)
  restaurant_id UUID NOT NULL,

  -- số tiền thanh toán (tổng của order tại thời điểm đó)
  amount NUMERIC(10,2) NOT NULL CHECK (amount > 0),

  payment_method payment_method NOT NULL DEFAULT 'COD',
  payment_status payment_status NOT NULL DEFAULT 'PENDING',

  order_items JSONB NOT NULL,

  transaction_id UUID UNIQUE,
  failure_reason TEXT,

  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE INDEX idx_payments_order_id ON payments(order_id);
CREATE INDEX idx_payments_customer_id ON payments(customer_id);

-- nếu hay query theo restaurant
CREATE INDEX idx_payments_restaurant_id ON payments(restaurant_id);
