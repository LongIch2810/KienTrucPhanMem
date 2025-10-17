INSERT INTO orders (
    user_id,
    total_amount,
    discount_amount,
    final_amount,
    currency,
    order_status,
    payment_status,
    payment_method
) VALUES
    (1, 200.00, 0.00, 200.00,'USD', 'CONFIRMED', 'PAID', 'COD'),
    (2, 350.00, 50.00, 300.00,'USD', 'PENDING', 'PENDING', 'ONLINE'),
    (3, 150.00, 0.00, 150.00,'USD', 'CANCELLED', 'FAILED', 'ONLINE');

INSERT INTO order_items (
    order_id,
    product_id,
    quantity,
    price
) VALUES
    (1, 101, 2, 100.00),
    (2, 102, 1, 300.00),
    (3, 103, 3, 50.00);