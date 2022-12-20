INSERT Clientes(nombre) VALUES('Jose Andres');

INSERT Productos(nombre, precio, stock) VALUES('Coca-cola', 10, 1000);
INSERT Productos(nombre, precio, stock) VALUES('Laptop', 1000, 10);
INSERT Productos(nombre, precio, stock) VALUES('Smartphone', 500, 20);

INSERT Ventas(id_cliente, id_producto, cantidad, subtotal, descuento, total) VALUES (1, 1, 1, 10, 0, 10);
INSERT Ventas(id_cliente, id_producto, cantidad, subtotal, descuento, total) VALUES (1, 2, 1, 1000, 200, 800);
INSERT Ventas(id_cliente, id_producto, cantidad, subtotal, descuento, total) VALUES (1, 3, 5, 2500, 750, 1750);
INSERT Ventas(id_cliente, id_producto, cantidad, subtotal, descuento, total) VALUES (1, 2, 4, 4000, 1600, 2400);
