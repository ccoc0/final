-- Jollibee Menu System Database
-- REAL Jollibee Philippines Menu Items (2024 Prices)
-- Import this SQL file to phpMyAdmin

CREATE DATABASE IF NOT EXISTS restaurant_pos;
USE restaurant_pos;

-- Admin credentials table
CREATE TABLE IF NOT EXISTS admin_credentials (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 username VARCHAR(50) NOT NULL UNIQUE,
                                                 password VARCHAR(100) NOT NULL
);

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
                                          available TINYINT DEFAULT 1,
                                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELETE FROM menu_items;

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

-- CHICKEN CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Chickenjoy 1pc Solo', 'Chicken', 82.00, 'Crispy and juicy fried chicken with rice and gravy'),
                                                                ('Chickenjoy 2pc Solo', 'Chicken', 155.00, 'Two pieces of crispy fried chicken with rice and gravy'),
                                                                ('Chickenjoy 3pc Solo', 'Chicken', 230.00, 'Three pieces of crispy fried chicken with rice and gravy'),
                                                                ('Chickenjoy w/ Jolly Spaghetti', 'Chicken', 135.00, '1pc Chickenjoy with Jolly Spaghetti'),
                                                                ('Chickenjoy w/ Jolly Hotdog', 'Chicken', 125.00, '1pc Chickenjoy with Jolly Hotdog'),
                                                                ('Spicy Chickenjoy 1pc Solo', 'Chicken', 92.00, 'Spicy crispy fried chicken with rice and gravy'),
                                                                ('Spicy Chickenjoy 2pc Solo', 'Chicken', 175.00, 'Two pieces of spicy crispy fried chicken'),
                                                                ('Spicy Chickenjoy w/ Jolly Spaghetti', 'Chicken', 145.00, '1pc Spicy Chickenjoy with Jolly Spaghetti'),
                                                                ('Chickenjoy Bucket 6pc', 'Chicken', 450.00, 'Family bucket with 6 pieces of Chickenjoy'),
                                                                ('Chickenjoy Bucket 8pc', 'Chicken', 580.00, 'Family bucket with 8 pieces of Chickenjoy');

-- BURGERS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Yumburger', 'Burgers', 50.00, 'Classic Jollibee burger with special dressing'),
                                                                ('Yumburger w/ Cheese', 'Burgers', 62.00, 'Classic Yumburger with melted cheese'),
                                                                ('Cheesy Yumburger', 'Burgers', 75.00, 'Yumburger loaded with cheese'),
                                                                ('Champ Jr.', 'Burgers', 72.00, 'Junior quarter pound burger with cheese'),
                                                                ('Champ', 'Burgers', 99.00, 'Quarter pound burger with cheese, pickles, onions, lettuce'),
                                                                ('Big Champ', 'Burgers', 149.00, 'Big quarter pound double patty burger with double cheese'),
                                                                ('Aloha Champ', 'Burgers', 139.00, 'Champ burger with sweet pineapple ring and honey cured bacon');

-- PASTA CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Jolly Spaghetti Solo', 'Pasta', 65.00, 'Sweet style spaghetti with hotdog slices and cheese'),
                                                                ('Jolly Spaghetti Family Pan', 'Pasta', 240.00, 'Family size spaghetti good for 4-5 persons'),
                                                                ('Jolly Spaghetti Party Pan', 'Pasta', 440.00, 'Party size spaghetti good for 8-10 persons'),
                                                                ('Palabok Fiesta Solo', 'Pasta', 75.00, 'Rice noodles with shrimp sauce, chicharon, and toppings'),
                                                                ('Palabok Fiesta Family Pan', 'Pasta', 280.00, 'Family size palabok good for 4-5 persons'),
                                                                ('Palabok Fiesta Party Pan', 'Pasta', 520.00, 'Party size palabok good for 8-10 persons'),
                                                                ('Macaroni Soup', 'Pasta', 55.00, 'Creamy macaroni soup with chicken');

-- RICE MEALS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Chickenjoy w/ Rice', 'Rice Meals', 99.00, '1pc Chickenjoy with steamed rice'),
                                                                ('Spicy Chickenjoy w/ Rice', 'Rice Meals', 109.00, '1pc Spicy Chickenjoy with steamed rice'),
                                                                ('Corned Beef w/ Rice', 'Rice Meals', 85.00, 'Premium corned beef served with rice'),
                                                                ('Corned Beef w/ Rice & Egg', 'Rice Meals', 95.00, 'Premium corned beef with rice and fried egg'),
                                                                ('Bangus w/ Rice', 'Rice Meals', 115.00, 'Boneless milkfish with rice'),
                                                                ('Pork Chop w/ Rice', 'Rice Meals', 125.00, 'Grilled pork chop with rice'),
                                                                ('Beef Tapa w/ Rice & Egg', 'Rice Meals', 105.00, 'Cured beef tapa with rice and egg'),
                                                                ('Longganisa w/ Rice & Egg', 'Rice Meals', 95.00, 'Filipino sweet sausage with rice and egg'),
                                                                ('Tosilog w/ Rice & Egg', 'Rice Meals', 95.00, 'Pork tocino with rice and egg');

-- DRINKS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Coke Regular', 'Drinks', 45.00, 'Regular size Coca-Cola 12oz'),
                                                                ('Coke Large', 'Drinks', 60.00, 'Large size Coca-Cola 16oz'),
                                                                ('Coke Float', 'Drinks', 75.00, 'Coca-Cola with creamy vanilla ice cream'),
                                                                ('Pineapple Juice', 'Drinks', 50.00, 'Refreshing pineapple juice'),
                                                                ('Iced Tea', 'Drinks', 45.00, 'Refreshing house iced tea'),
                                                                ('Coffee', 'Drinks', 40.00, 'Hot brewed coffee'),
                                                                ('Hot Chocolate', 'Drinks', 45.00, 'Creamy hot chocolate');

-- DESSERTS CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('Peach Mango Pie', 'Desserts', 50.00, 'Crispy pie with sweet peach and mango filling'),
                                                                ('Banana Langka Pie', 'Desserts', 50.00, 'Crispy pie with banana and jackfruit filling'),
                                                                ('Choco Mousse Pie', 'Desserts', 55.00, 'Crispy pie with rich chocolate mousse'),
                                                                ('Sundae Chocolate', 'Desserts', 35.00, 'Creamy soft serve with chocolate syrup'),
                                                                ('Sundae Caramel', 'Desserts', 35.00, 'Creamy soft serve with caramel syrup'),
                                                                ('Sundae Strawberry', 'Desserts', 35.00, 'Creamy soft serve with strawberry syrup'),
                                                                ('Peach Mango Float', 'Desserts', 75.00, 'Layered peach mango cream dessert');

-- SIDES CATEGORY
INSERT INTO menu_items (name, category, price, description) VALUES
                                                                ('French Fries Regular', 'Sides', 55.00, 'Crispy golden french fries'),
                                                                ('French Fries Large', 'Sides', 75.00, 'Large serving of crispy french fries'),
                                                                ('Jolly Crispy Fries', 'Sides', 65.00, 'Extra crispy seasoned fries'),
                                                                ('Mashed Potato', 'Sides', 45.00, 'Creamy mashed potato with gravy'),
                                                                ('Corn Cup', 'Sides', 40.00, 'Buttered sweet corn'),
                                                                ('Extra Rice', 'Sides', 25.00, 'Steamed white rice'),
                                                                ('Extra Gravy', 'Sides', 20.00, 'Extra serving of chicken gravy'),
                                                                ('Jolly Hotdog Solo', 'Sides', 65.00, 'Classic Jolly Hotdog with dressing');