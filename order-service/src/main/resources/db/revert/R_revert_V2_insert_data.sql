DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM flyway_schema_history WHERE version = '2';