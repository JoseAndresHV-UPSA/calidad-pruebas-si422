package com.example.segundoparcialhurtado.services;

import com.example.segundoparcialhurtado.models.Cliente;
import com.example.segundoparcialhurtado.models.Producto;
import com.example.segundoparcialhurtado.models.Venta;
import com.example.segundoparcialhurtado.repositories.IClienteRepository;
import com.example.segundoparcialhurtado.repositories.IProductoRepository;
import com.example.segundoparcialhurtado.repositories.IVentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VentaServiceImpl implements IVentaService {

    private IClienteRepository clienteRepository;
    private IProductoRepository productoRepository;
    private IVentaRepository ventaRepository;

    public VentaServiceImpl(IClienteRepository clienteRepository, IProductoRepository productoRepository, IVentaRepository ventaRepository) {
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElseThrow();
    }

    @Override
    public BigDecimal obtenerPrecioProductoPorId(Long id) {
        return obtenerProductoPorId(id).getPrecio();
    }

    @Override
    public Integer obtenerStockProductoPorId(Long id) {
        return obtenerProductoPorId(id).getStock();
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElseThrow();
    }

    @Override
    public Venta realizarVenta(Long idCliente, Long idProducto, Integer cantidad) {
        Cliente cliente = obtenerClientePorId(idCliente);
        Producto producto = obtenerProductoPorId(idProducto);

        producto.disminuirStock(cantidad);

        Venta venta = new Venta();
        venta.setIdCliente(idCliente);
        venta.setIdProducto(idProducto);
        venta.calcularTotal(cantidad, producto.getPrecio());
        ventaRepository.save(venta);
        return venta;
    }
}
