-- Insert sample restaurants
INSERT INTO restaurants (
    id,
    name,
    owner_id,
    status,
    created_at
) VALUES
    (
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        'Pizza Paradise',
        '850e8400-e29b-41d4-a716-446655440001'::UUID,
        'ACTIVE'::restaurant_status,
        CURRENT_TIMESTAMP
    ),
    (
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        'Burger House',
        '850e8400-e29b-41d4-a716-446655440002'::UUID,
        'ACTIVE'::restaurant_status,
        CURRENT_TIMESTAMP
    ),
    (
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        'Sushi Kingdom',
        '850e8400-e29b-41d4-a716-446655440003'::UUID,
        'INACTIVE'::restaurant_status,
        CURRENT_TIMESTAMP
    );

-- Insert sample menu items
INSERT INTO menu_items (
    id,
    restaurant_id,
    name,
    price,
    quantity,
    available
) VALUES
    -- Pizza Paradise menu
    (
        '950e8400-e29b-41d4-a716-446655440001'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        'Margherita Pizza',
        150.00,
        50,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440002'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        'Pepperoni Pizza',
        180.00,
        40,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440003'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        'Vegetarian Pizza',
        160.00,
        0,
        FALSE
    ),
    -- Burger House menu
    (
        '950e8400-e29b-41d4-a716-446655440004'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        'Classic Burger',
        120.00,
        60,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440005'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        'Cheese Burger',
        140.00,
        50,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440006'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        'Bacon Burger',
        160.00,
        40,
        TRUE
    ),
    -- Sushi Kingdom menu
    (
        '950e8400-e29b-41d4-a716-446655440007'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        'California Roll',
        200.00,
        35,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440008'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        'Salmon Sashimi',
        250.00,
        25,
        TRUE
    ),
    (
        '950e8400-e29b-41d4-a716-446655440009'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        'Tuna Nigiri',
        220.00,
        0,
        FALSE
    );

-- Insert sample restaurant orders
INSERT INTO restaurant_orders (
    id,
    order_id,
    restaurant_id,
    total_amount,
    approval_status,
    rejection_reason,
    created_at,
    updated_at
) VALUES
    (
        'a50e8400-e29b-41d4-a716-446655440001'::UUID,
        '550e8400-e29b-41d4-a716-446655440001'::UUID,
        '750e8400-e29b-41d4-a716-446655440001'::UUID,
        300.00,
        'APPROVED'::order_approval_status,
        NULL,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        'a50e8400-e29b-41d4-a716-446655440002'::UUID,
        '550e8400-e29b-41d4-a716-446655440002'::UUID,
        '750e8400-e29b-41d4-a716-446655440002'::UUID,
        260.00,
        'PENDING'::order_approval_status,
        NULL,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        'a50e8400-e29b-41d4-a716-446655440003'::UUID,
        '550e8400-e29b-41d4-a716-446655440003'::UUID,
        '750e8400-e29b-41d4-a716-446655440003'::UUID,
        450.00,
        'REJECTED'::order_approval_status,
        'Items not available at this time',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );

