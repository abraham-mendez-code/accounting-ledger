USE sys;

# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          DoughFlow                                      #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS doughflow;

CREATE DATABASE IF NOT EXISTS doughflow;

USE doughflow;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE profiles (
    user_id INT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(200) NOT NULL,
    address VARCHAR(200) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE categories (
    category_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    PRIMARY KEY (category_id)
);

CREATE TABLE products (
    product_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category_id INT NOT NULL,
    description TEXT,
    subcategory VARCHAR(20),
    image_url VARCHAR(200),
    stock INT NOT NULL DEFAULT 0,
    featured BOOL NOT NULL DEFAULT 0,
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE orders (
    order_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    date DATETIME NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    delivery_fee DECIMAL(10, 2) NOT NULL DEFAULT 0,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_line_items (
    order_line_item_id INT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    sales_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    discount DECIMAL(10, 2) NOT NULL DEFAULT 0,
    PRIMARY KEY (order_line_item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- new tables
CREATE TABLE shopping_cart (
	user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


/*  INSERT Users  */
INSERT INTO users (username, hashed_password, role) 
VALUES  ('user','$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.','ROLE_USER'),
        ('admin','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_ADMIN'),
        ('eric','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_USER');

/* INSERT Profiles */
INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip)
VALUES  (1, 'Joe', 'Joesephus', '800-555-1234', 'joejoesephus@email.com', '789 Oak Avenue', 'Dallas', 'TX', '75051'),
        (2, 'Noal', 'Yaang', '800-555-1212', 'noal.yaang@email.com', '456 Elm Street','Dallas','TX','75052'),
        (3, 'Erik', 'Crisco', '800-555-9876', 'erik.crisco@email.com', '123 Birch Parkway','Dallas','TX','75051')     ;

/* INSERT Categories */
INSERT INTO categories (name, description) 
VALUES  ('Drinks', 'Beverages including soft drinks, juices, bottled water, and coffee.'),
        ('Pasta', 'Prepared pasta entrees available in multiple styles and sauces.'),
		('Pizza', 'Made-to-order pizzas offered in various sizes and topping combinations.');


/* INSERT Products */
-- Drinks
INSERT INTO products (name, price, category_id, description, image_url, stock, featured, subcategory) 
VALUES  ('Coca-Cola', 2.49, 1, 'Classic Coca-Cola served ice cold.', 'coca-cola.jpg', 120, 1, 'Soda'),
		('Diet Coke', 2.49, 1, 'Sugar-free Coca-Cola with the same great taste.', 'diet-coke.jpg', 90, 0, 'Soda'),
		('Sprite', 2.49, 1, 'Crisp lemon-lime soda, refreshing and caffeine-free.', 'sprite.jpg', 85, 0, 'Soda'),
		('Dr Pepper', 2.49, 1, 'Bold blend of 23 flavors.', 'dr-pepper.jpg', 75, 0, 'Soda'),
		('Bottled Water', 1.99, 1, 'Purified bottled drinking water.', 'bottled-water.jpg', 150, 1, 'Water'),
		('Sparkling Water', 2.29, 1, 'Carbonated water with a clean, crisp taste.', 'sparkling-water.jpg', 70, 0, 'Water'),
		('Lemonade', 2.99, 1, 'Freshly made lemonade with real lemons.', 'lemonade.jpg', 60, 1, 'Juice'),
		('Iced Tea', 2.79, 1, 'Fresh brewed iced tea, lightly sweetened.', 'iced-tea.jpg', 65, 0, 'Tea'),
		('Sweet Tea', 2.79, 1, 'Southern-style sweet iced tea.', 'sweet-tea.jpg', 55, 0, 'Tea'),
		('Orange Juice', 3.49, 1, '100% pure orange juice, no added sugar.', 'orange-juice.jpg', 45, 0, 'Juice'),
		('Apple Juice', 3.49, 1, 'Crisp and refreshing apple juice.', 'apple-juice.jpg', 40, 0, 'Juice'),
		('Nitro Cold Brew', 4.99, 1, 'Smooth nitro-infused cold brew coffee.', 'nitro-cold-brew.jpg', 50, 1, 'Coffee'),
		('Hot Coffee', 2.99, 1, 'Freshly brewed hot coffee.', 'hot-coffee.jpg', 70, 0, 'Coffee'),
		('Energy Drink', 3.99, 1, 'Carbonated energy drink for a quick boost.', 'energy-drink.jpg', 30, 0, 'Energy');

-- Pasta
INSERT INTO products (name, price, category_id, description, image_url, stock, featured, subcategory) 
VALUES 	('Spaghetti Marinara', 11.99, 2, 'Spaghetti pasta served with house-made marinara sauce.', 'spaghetti-marinara.jpg', 40, 1, 'Classic'),
		('Spaghetti Meatballs', 13.99, 2, 'Spaghetti with marinara sauce and seasoned meatballs.', 'spaghetti-meatballs.jpg', 35, 1, 'Classic'),
		('Fettuccine Alfredo', 12.99, 2, 'Fettuccine pasta tossed in a creamy Alfredo sauce.', 'fettuccine-alfredo.jpg', 30, 1, 'Cream'),
		('Chicken Alfredo', 15.49, 2, 'Fettuccine Alfredo topped with grilled chicken.', 'chicken-alfredo.jpg', 25, 0, 'Cream'),
		('Penne Arrabbiata', 12.49, 2, 'Penne pasta with spicy tomato sauce.', 'penne-arrabbiata.jpg', 28, 0, 'Spicy'),
		('Baked Ziti', 13.49, 2, 'Baked ziti with marinara sauce and melted cheese.', 'baked-ziti.jpg', 32, 1, 'Baked'),
		('Lasagna', 14.99, 2, 'Layered pasta with meat sauce, ricotta, and mozzarella.', 'lasagna.jpg', 20, 1, 'Baked'),
		('Cheese Ravioli', 12.99, 2, 'Cheese-filled ravioli served with marinara sauce.', 'cheese-ravioli.jpg', 30, 0, 'Stuffed'),
		('Spinach Ravioli', 13.49, 2, 'Spinach-filled ravioli with garlic butter sauce.', 'spinach-ravioli.jpg', 22, 0, 'Stuffed'),
		('Penne Primavera', 12.99, 2, 'Penne pasta tossed with seasonal vegetables.', 'penne-primavera.jpg', 25, 0, 'Vegetarian'),
		('Garlic Butter Pasta', 10.99, 2, 'Pasta tossed in garlic butter and herbs.', 'garlic-butter-pasta.jpg', 35, 0, 'Simple'),
		('Seafood Linguine', 16.99, 2, 'Linguine pasta with mixed seafood in garlic sauce.', 'seafood-linguine.jpg', 15, 1, 'Seafood'),
		('Four Cheese Pasta', 13.99, 2, 'Pasta tossed in a rich four-cheese sauce.', 'four-cheese-pasta.jpg', 20, 0, 'Cheese');

-- Pizza
INSERT INTO products (name, price, category_id, description, image_url, stock, featured, subcategory) 
VALUES  ('Cheese Pizza', 10.99, 3, 'Classic pizza with mozzarella cheese and tomato sauce.', 'cheese-pizza.jpg', 50, 1, 'Classic'),
		('Pepperoni Pizza', 12.99, 3, 'Mozzarella cheese, tomato sauce, and pepperoni.', 'pepperoni-pizza.jpg', 45, 1, 'Classic'),
		('Margherita Pizza', 13.49, 3, 'Fresh mozzarella, basil, and tomato sauce.', 'margherita-pizza.jpg', 30, 1, 'Specialty'),
		('Meat Lovers Pizza', 15.99, 3, 'Loaded with pepperoni, sausage, bacon, and ham.', 'meat-lovers.jpg', 25, 0, 'Specialty'),
		('Supreme Pizza', 15.49, 3, 'Pepperoni, sausage, peppers, onions, olives, and mushrooms.', 'supreme-pizza.jpg', 28, 1, 'Specialty'),
		('BBQ Chicken Pizza', 14.99, 3, 'Grilled chicken, BBQ sauce, and red onions.', 'bbq-chicken.jpg', 22, 0, 'Specialty'),
		('Hawaiian Pizza', 13.99, 3, 'Ham and pineapple over mozzarella and sauce.', 'hawaiian.jpg', 20, 0, 'Specialty'),
		('Veggie Pizza', 13.49, 3, 'Mushrooms, peppers, onions, olives, and tomatoes.', 'veggie-pizza.jpg', 26, 0, 'Vegetarian'),
		('White Pizza', 14.49, 3, 'Garlic ricotta base with mozzarella and herbs.', 'white-pizza.jpg', 18, 0, 'Specialty'),
		('Buffalo Chicken Pizza', 15.49, 3, 'Buffalo sauce, chicken, and mozzarella cheese.', 'buffalo-chicken.jpg', 20, 1, 'Specialty'),
		('Four Cheese Pizza', 14.99, 3, 'Blend of mozzarella, parmesan, provolone, and ricotta.', 'four-cheese-pizza.jpg', 24, 0, 'Cheese'),
		('Build Your Own Pizza', 9.99, 3, 'Custom pizza with choice of sauce, cheese, and toppings.', 'build-your-own.jpg', 60, 1, 'Custom');

-- add shopping cart items
INSERT INTO shopping_cart (user_id, product_id, quantity)
VALUES  (3, 8, 1),
        (3, 10, 1);