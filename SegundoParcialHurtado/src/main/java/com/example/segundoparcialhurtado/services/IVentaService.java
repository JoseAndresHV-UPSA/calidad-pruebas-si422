package com.example.segundoparcialhurtado.services;

import com.example.segundoparcialhurtado.models.Cliente;
import com.example.segundoparcialhurtado.models.Producto;
import com.example.segundoparcialhurtado.models.Venta;

import java.math.BigDecimal;

public interface IVentaService {
    Cliente obtenerClientePorId(Long id);
    Producto obtenerProductoPorId(Long id);
    BigDecimal obtenerPrecioProductoPorId(Long id);
    Integer obtenerStockProductoPorId(Long id);
    Venta obtenerVentaPorId(Long id);
    Venta realizarVenta(Long idCliente, Long idProducto, Integer cantidad);
}
