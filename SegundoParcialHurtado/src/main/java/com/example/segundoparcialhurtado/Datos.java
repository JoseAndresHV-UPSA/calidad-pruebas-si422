package com.example.segundoparcialhurtado;

import com.example.segundoparcialhurtado.models.Cliente;
import com.example.segundoparcialhurtado.models.Producto;
import com.example.segundoparcialhurtado.models.Venta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {
    public static Optional<Cliente> crearCliente01() {
        return Optional.of(new Cliente(
                1L,
                "Jose Andres"
        ));
    }

    public static Optional<Producto> crearProducto01() {
        return Optional.of(new Producto(
                1L,
                "Coca-cola",
                BigDecimal.valueOf(10),
                Integer.valueOf(1000)
        ));
    }

    public static Optional<Producto> crearProducto02() {
        return Optional.of(new Producto(
                2L,
                "Laptop",
                BigDecimal.valueOf(1000),
                Integer.valueOf(10)
        ));
    }

    public static Optional<Producto> crearProducto03() {
        return Optional.of(new Producto(
                3L,
                "Smartphone",
                BigDecimal.valueOf(500),
                Integer.valueOf(20)
        ));
    }

    public static Optional<Venta> crearVentaSinDescuento() {
        return Optional.of(new Venta(
                1L,
                1L,
                1L,
                Integer.valueOf(1),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(10)
        ));
    }

    public static Optional<Venta> crearVentaDescuento20() {
        return Optional.of(new Venta(
                2L,
                1L,
                2L,
                Integer.valueOf(1),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(200.0),
                BigDecimal.valueOf(800.0)
        ));
    }

    public static Optional<Venta> crearVentaDescuento30() {
        return Optional.of(new Venta(
                3L,
                1L,
                3L,
                Integer.valueOf(5),
                BigDecimal.valueOf(2500),
                BigDecimal.valueOf(750.0),
                BigDecimal.valueOf(1750.0)
        ));
    }

    public static Optional<Venta> crearVentaDescuento40() {
        return Optional.of(new Venta(
                4L,
                1L,
                2L,
                Integer.valueOf(4),
                BigDecimal.valueOf(4000),
                BigDecimal.valueOf(1600.0),
                BigDecimal.valueOf(2400.0)
        ));
    }
}
