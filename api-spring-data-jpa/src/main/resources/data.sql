-- Clientes
INSERT INTO client (name, email) VALUES ('Ana Torres', 'ana@example.com');
INSERT INTO client (name, email) VALUES ('Luis Ramirez', 'luis@example.com');
INSERT INTO client (name, email) VALUES ('Carla Gomez', 'carla@example.com');

-- Productos
INSERT INTO product (name, price) VALUES ('Teclado', 45.90);
INSERT INTO product (name, price) VALUES ('Mouse', 25.50);
INSERT INTO product (name, price) VALUES ('Monitor', 899.00);

-- Órdenes simples sin items (para probar listados)
INSERT INTO orders (date, status, client_id) VALUES (CURRENT_TIMESTAMP, 'PENDING', 1);
INSERT INTO orders (date, status, client_id) VALUES (CURRENT_TIMESTAMP, 'PAID', 2);
