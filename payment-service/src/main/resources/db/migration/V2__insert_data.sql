-- Insert sample payments
INSERT INTO payments (
    id,
    order_id,
    customer_id,
    restaurant_id,
    amount,
    payment_method,
    payment_status,
    order_items,
    transaction_id,
    failure_reason,
    created_at,
    updated_at
) VALUES
    (
        '950e8400-e29b-41d4-a716-446655440001'::UUID,
        '550e8400-e29b-41d4-a716-446655440001'::UUID,
        '650e8400-e29b-41d4-a716-446655440001'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        300.00,
        'ONLINE'::payment_method,
        'COMPLETED'::payment_status,
        '[{"id": "850e8400-e29b-41d4-a716-446655440001", "name": "Pizza Margherita", "price": 200.00, "quantity": 1}, {"id": "850e8400-e29b-41d4-a716-446655440002", "name": "Coca Cola", "price": 100.00, "quantity": 1}]'::jsonb,
        'a10e8400-e29b-41d4-a716-446655440001'::UUID,
        NULL,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '950e8400-e29b-41d4-a716-446655440002'::UUID,
        '550e8400-e29b-41d4-a716-446655440002'::UUID,
        '650e8400-e29b-41d4-a716-446655440002'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        260.00,
        'COD'::payment_method,
        'PENDING'::payment_status,
        '[{"id": "850e8400-e29b-41d4-a716-446655440003", "name": "Chicken Burger", "price": 180.00, "quantity": 1}, {"id": "850e8400-e29b-41d4-a716-446655440004", "name": "French Fries", "price": 80.00, "quantity": 1}]'::jsonb,
        NULL,
        NULL,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '950e8400-e29b-41d4-a716-446655440003'::UUID,
        '550e8400-e29b-41d4-a716-446655440003'::UUID,
        '650e8400-e29b-41d4-a716-446655440003'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        450.00,
        'ONLINE'::payment_method,
        'FAILED'::payment_status,
        '[{"id": "850e8400-e29b-41d4-a716-446655440005", "name": "Sushi Set", "price": 350.00, "quantity": 1}, {"id": "850e8400-e29b-41d4-a716-446655440006", "name": "Green Tea", "price": 100.00, "quantity": 1}]'::jsonb,
        NULL,
        'Insufficient funds in account',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );
