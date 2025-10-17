DROP TYPE IF EXISTS order_status_enum CASCADE;
DROP TYPE IF EXISTS payment_status_enum CASCADE;
DROP TYPE IF EXISTS payment_method_enum CASCADE;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DELETE FROM flyway_schema_history WHERE version = '1';