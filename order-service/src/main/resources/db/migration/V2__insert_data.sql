-- Insert sample orders
INSERT INTO orders (
    id,
    customer_id,
    restaurant_id,
    tracking_id,
    price,
    order_status,
    failure_messages,
    created_at
) VALUES
    (
        '550e8400-e29b-41d4-a716-446655440001'::UUID,
        '650e8400-e29b-41d4-a716-446655440001'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        '850e8400-e29b-41d4-a716-446655440001'::UUID,
        300.00,
        'PAID'::order_status,
        NULL,
        CURRENT_TIMESTAMP
    ),
    (
        '550e8400-e29b-41d4-a716-446655440002'::UUID,
        '650e8400-e29b-41d4-a716-446655440002'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        '850e8400-e29b-41d4-a716-446655440002'::UUID,
        260.00,
        'PENDING'::order_status,
        NULL,
        CURRENT_TIMESTAMP
    ),
    (
        '550e8400-e29b-41d4-a716-446655440003'::UUID,
        '650e8400-e29b-41d4-a716-446655440003'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        '850e8400-e29b-41d4-a716-446655440003'::UUID,
        450.00,
        'CANCELLED'::order_status,
        'Order was cancelled by customer',
        CURRENT_TIMESTAMP
    );

-- Insert sample order items
INSERT INTO order_items (
    order_id,
    product_id,
    quantity,
    price,
    sub_total
) VALUES
    -- Order 1: Pizza Paradise - 2x Margherita Pizza
    (
        '550e8400-e29b-41d4-a716-446655440001'::UUID,
        '950e8400-e29b-41d4-a716-446655440001'::UUID,
        2,
        150.00,
        300.00
    ),
    -- Order 2: Burger House - 1x Classic Burger + 1x Cheese Burger
    (
        '550e8400-e29b-41d4-a716-446655440002'::UUID,
        '950e8400-e29b-41d4-a716-446655440004'::UUID,
        1,
        120.00,
        120.00
    ),
    (
        '550e8400-e29b-41d4-a716-446655440002'::UUID,
        '950e8400-e29b-41d4-a716-446655440005'::UUID,
        1,
        140.00,
        140.00
    ),
    -- Order 3: Sushi Kingdom - 1x California Roll + 1x Salmon Sashimi
    (
        '550e8400-e29b-41d4-a716-446655440003'::UUID,
        '950e8400-e29b-41d4-a716-446655440007'::UUID,
        1,
        200.00,
        200.00
    ),
    (
        '550e8400-e29b-41d4-a716-446655440003'::UUID,
        '950e8400-e29b-41d4-a716-446655440008'::UUID,
        1,
        250.00,
        250.00
    );