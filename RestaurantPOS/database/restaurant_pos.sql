-- Jollibee Menu System Database
-- Import this SQL file to phpMyAdmin after creating the database

CREATE DATABASE IF NOT EXISTS restaurant_pos;
USE restaurant_pos;

-- Admin credentials table
CREATE TABLE IF NOT EXISTS admin_credentials (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Insert default admin credentials (username: admin, password: admin)
INSERT INTO admin_credentials (username, password) VALUES ('admin', 'admin')
ON DUPLICATE KEY UPDATE username = 'admin';

-- Menu items table
CREATE TABLE IF NOT EXISTS menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Buyer history table
CREATE TABLE IF NOT EXISTS buyer_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    buyer_name VARCHAR(100) NOT NULL,
    items_ordered TEXT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50) DEFAULT 'Cash',
    status VARCHAR(20) DEFAULT 'Completed'
);

-- Insert Jollibee Menu Items with Philippine Peso prices
-- Based on actual Jollibee Philippines menu prices (as of 2024)

-- CHICKEN CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Chickenjoy 1pc', 'Chicken', 82.00, 'Crispy and juicy fried chicken with gravy'),
('Chickenjoy 2pc', 'Chicken', 155.00, 'Two pieces of crispy fried chicken with gravy'),
('Chickenjoy 3pc', 'Chicken', 230.00, 'Three pieces of crispy fried chicken with gravy'),
('Spaghetti with Chickenjoy', 'Chicken', 135.00, '1pc Chickenjoy with Jolly Spaghetti'),
('Chickenjoy Bucket (6pc)', 'Chicken', 450.00, 'Family size bucket of 6 Chickenjoy pieces'),
('Chickenjoy Bucket (8pc)', 'Chicken', 580.00, 'Family size bucket of 8 Chickenjoy pieces'),
('Jolly Hotdog with Chickenjoy', 'Chicken', 125.00, '1pc Chickenjoy with Jolly Hotdog');

-- BURGERS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Yumburger', 'Burgers', 50.00, 'Classic Jollibee burger with special dressing'),
('Yumburger with Cheese', 'Burgers', 62.00, 'Yumburger with melted cheese'),
('Cheesy Yumburger', 'Burgers', 75.00, 'Double cheese Yumburger'),
('Champ', 'Burgers', 95.00, 'Quarter pound burger with cheese'),
('Champ Jr.', 'Burgers', 72.00, 'Junior version of the Champ burger'),
('Big Champ', 'Burgers', 145.00, 'Big quarter pound burger with double cheese'),
('Aloha Champ', 'Burgers', 135.00, 'Champ with pineapple and bacon');

-- PASTA CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Jolly Spaghetti', 'Pasta', 65.00, 'Sweet style spaghetti with hotdog slices'),
('Jolly Spaghetti Family Pan', 'Pasta', 240.00, 'Family size spaghetti good for 4-5 persons'),
('Palabok', 'Pasta', 75.00, 'Rice noodles with shrimp sauce and toppings'),
('Palabok Family Pan', 'Pasta', 280.00, 'Family size palabok good for 4-5 persons'),
('Macaroni Soup', 'Pasta', 55.00, 'Creamy macaroni soup with chicken');

-- RICE MEALS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Chickenjoy with Rice', 'Rice Meals', 99.00, '1pc Chickenjoy with steamed rice'),
('Corned Beef with Rice', 'Rice Meals', 85.00, 'Corned beef served with rice and egg'),
('Bangus with Rice', 'Rice Meals', 115.00, 'Boneless milkfish with rice'),
('Pork Chop with Rice', 'Rice Meals', 125.00, 'Grilled pork chop with rice'),
('Corned Beef with Rice & Egg', 'Rice Meals', 95.00, 'Corned beef with rice and fried egg'),
('Beef Tapa with Rice', 'Rice Meals', 105.00, 'Cured beef with rice and egg');

-- DRINKS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Coke Regular', 'Drinks', 45.00, 'Regular size Coca-Cola'),
('Coke Large', 'Drinks', 60.00, 'Large size Coca-Cola'),
('Pineapple Juice', 'Drinks', 50.00, 'Refreshing pineapple juice'),
('Iced Tea', 'Drinks', 45.00, 'House iced tea'),
('Coffee', 'Drinks', 40.00, 'Hot brewed coffee'),
('Hot Chocolate', 'Drinks', 45.00, 'Creamy hot chocolate');

-- DESSERTS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
('Peach Mango Pie', 'Desserts', 50.00, 'Crispy pie with peach and mango filling'),
('Banana Langka Pie', 'Desserts', 50.00, 'Crispy pie with banana and jackfruit'),
('Sundae Chocolate', 'Desserts', 35.00, 'Soft serve ice cream with chocolate'),
('Sundae Caramel', 'Desserts', 35.00, 'Soft serve ice cream with caramel'),
('Peach Mango Float', 'Desserts', 75.00, 'Creamy peach mango dessert'),
('Tuna Pie', 'Desserts', 55.00, 'Savory pie with tuna filling');

-- SIDES CATEGORY  
INSERT INTO menu_items (name, category, price, description) VALUES
('French Fries Regular', 'Sides', 55.00, 'Crispy golden french fries'),
('French Fries Large', 'Sides', 75.00, 'Large crispy french fries'),
('Jolly Crispy Fries', 'Sides', 65.00, 'Extra crispy seasoned fries'),
('Mashed Potato', 'Sides', 45.00, 'Creamy mashed potato with gravy'),
('Corn Cup', 'Sides', 40.00, 'Buttered sweet corn'),
('Rice', 'Sides', 25.00, 'Steamed white rice'),
('Gravy', 'Sides', 20.00, 'Extra chicken gravy');
