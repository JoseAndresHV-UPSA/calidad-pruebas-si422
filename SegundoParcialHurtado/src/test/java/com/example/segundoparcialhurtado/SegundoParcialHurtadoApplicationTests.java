package com.example.segundoparcialhurtado;

import com.example.segundoparcialhurtado.exceptions.CantidadInvalidaException;
import com.example.segundoparcialhurtado.models.Cliente;
import com.example.segundoparcialhurtado.models.Venta;
import com.example.segundoparcialhurtado.repositories.IClienteRepository;
import com.example.segundoparcialhurtado.repositories.IProductoRepository;
import com.example.segundoparcialhurtado.repositories.IVentaRepository;
import com.example.segundoparcialhurtado.services.VentaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static com.example.segundoparcialhurtado.Datos.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SegundoParcialHurtadoApplicationTests {

    @MockBean
    IClienteRepository clienteRepository;
    @MockBean
    IProductoRepository productoRepository;
    @MockBean
    IVentaRepository ventaRepository;
    @Autowired
    VentaServiceImpl service;

    @Test
    void testVentaSinDescuento() {
        when(clienteRepository.findById(1L)).thenReturn(crearCliente01());
        when(productoRepository.findById(1L)).thenReturn(crearProducto01());
        when(ventaRepository.findById(1L)).thenReturn(crearVentaSinDescuento());

        //Test stock antes de la venta
        Integer stock = service.obtenerStockProductoPorId(1L);
        assertEquals(1000, stock);

        //Realizar venta
        Venta venta = service.realizarVenta(1L, 1L, 1);
        Venta ventaEsperada = service.obtenerVentaPorId(1L);

        //Test cliente
        Cliente cliente = service.obtenerClientePorId(venta.getIdCliente());
        Cliente clienteEsperado = service.obtenerClientePorId(1L);
        assertEquals(clienteEsperado.getNombre(), cliente.getNombre());

        //Test stock después de la venta
        stock = service.obtenerStockProductoPorId(1L);
        assertEquals(999, stock);

        //Test subtotal
        BigDecimal subtotal = venta.getSubtotal();
        assertEquals(BigDecimal.valueOf(10), subtotal);
        assertEquals(ventaEsperada.getSubtotal(), subtotal);

        //Test descuento
        BigDecimal descuento = venta.getDescuento();
        assertEquals(BigDecimal.ZERO, descuento);
        assertEquals(ventaEsperada.getDescuento(), descuento);

        //Test total
        BigDecimal total = venta.getTotal();
        assertEquals(BigDecimal.valueOf(10), total);
        assertEquals(ventaEsperada.getTotal(), total);

        verify(clienteRepository, times(3)).findById(anyLong());
        verify(productoRepository, times(3)).findById(anyLong());
        verify(ventaRepository, times(1)).findById(anyLong());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testVentaDescuento20() {
        when(clienteRepository.findById(1L)).thenReturn(crearCliente01());
        when(productoRepository.findById(2L)).thenReturn(crearProducto02());
        when(ventaRepository.findById(2L)).thenReturn(crearVentaDescuento20());

        //Test stock antes de la venta
        Integer stock = service.obtenerStockProductoPorId(2L);
        assertEquals(10, stock);

        //Realizar venta
        Venta venta = service.realizarVenta(1L, 2L, 1);
        Venta ventaEsperada = service.obtenerVentaPorId(2L);

        //Test cliente
        Cliente cliente = service.obtenerClientePorId(venta.getIdCliente());
        Cliente clienteEsperado = service.obtenerClientePorId(1L);
        assertEquals(clienteEsperado.getNombre(), cliente.getNombre());

        //Test stock después de la venta
        stock = service.obtenerStockProductoPorId(2L);
        assertEquals(9, stock);

        //Test subtotal
        BigDecimal subtotal = venta.getSubtotal();
        assertEquals(BigDecimal.valueOf(1000), subtotal);
        assertEquals(ventaEsperada.getSubtotal(), subtotal);

        //Test descuento
        BigDecimal descuento = venta.getDescuento();
        assertEquals(BigDecimal.valueOf(200.0), descuento);
        assertEquals(ventaEsperada.getDescuento(), descuento);

        //Test total
        BigDecimal total = venta.getTotal();
        assertEquals(BigDecimal.valueOf(800.0), total);
        assertEquals(ventaEsperada.getTotal(), total);

        verify(clienteRepository, times(3)).findById(anyLong());
        verify(productoRepository, times(3)).findById(anyLong());
        verify(ventaRepository, times(1)).findById(anyLong());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testVentaDescuento30() {
        when(clienteRepository.findById(1L)).thenReturn(crearCliente01());
        when(productoRepository.findById(3L)).thenReturn(crearProducto03());
        when(ventaRepository.findById(3L)).thenReturn(crearVentaDescuento30());

        //Test stock antes de la venta
        Integer stock = service.obtenerStockProductoPorId(3L);
        assertEquals(20, stock);

        //Realizar venta
        Venta venta = service.realizarVenta(1L, 3L, 5);
        Venta ventaEsperada = service.obtenerVentaPorId(3L);

        //Test cliente
        Cliente cliente = service.obtenerClientePorId(venta.getIdCliente());
        Cliente clienteEsperado = service.obtenerClientePorId(1L);
        assertEquals(clienteEsperado.getNombre(), cliente.getNombre());

        //Test stock después de la venta
        stock = service.obtenerStockProductoPorId(3L);
        assertEquals(15, stock);

        //Test subtotal
        BigDecimal subtotal = venta.getSubtotal();
        assertEquals(BigDecimal.valueOf(2500), subtotal);
        assertEquals(ventaEsperada.getSubtotal(), subtotal);

        //Test descuento
        BigDecimal descuento = venta.getDescuento();
        assertEquals(BigDecimal.valueOf(750.0), descuento);
        assertEquals(ventaEsperada.getDescuento(), descuento);

        //Test total
        BigDecimal total = venta.getTotal();
        assertEquals(BigDecimal.valueOf(1750.0), total);
        assertEquals(ventaEsperada.getTotal(), total);

        verify(clienteRepository, times(3)).findById(anyLong());
        verify(productoRepository, times(3)).findById(anyLong());
        verify(ventaRepository, times(1)).findById(anyLong());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testVentaDescuento40() {
        when(clienteRepository.findById(1L)).thenReturn(crearCliente01());
        when(productoRepository.findById(2L)).thenReturn(crearProducto02());
        when(ventaRepository.findById(4L)).thenReturn(crearVentaDescuento40());

        //Test stock antes de la venta
        Integer stock = service.obtenerStockProductoPorId(2L);
        assertEquals(10, stock);

        //Realizar venta
        Venta venta = service.realizarVenta(1L, 2L, 4);
        Venta ventaEsperada = service.obtenerVentaPorId(4L);

        //Test stock después de la venta
        stock = service.obtenerStockProductoPorId(2L);
        assertEquals(6, stock);

        //Test subtotal
        BigDecimal subtotal = venta.getSubtotal();
        assertEquals(BigDecimal.valueOf(4000), subtotal);
        assertEquals(ventaEsperada.getSubtotal(), subtotal);

        //Test descuento
        BigDecimal descuento = venta.getDescuento();
        assertEquals(BigDecimal.valueOf(1600.0), descuento);
        assertEquals(ventaEsperada.getDescuento(), descuento);

        //Test total
        BigDecimal total = venta.getTotal();
        assertEquals(BigDecimal.valueOf(2400.0), total);
        assertEquals(ventaEsperada.getTotal(), total);

        verify(clienteRepository, times(1)).findById(anyLong());
        verify(productoRepository, times(3)).findById(anyLong());
        verify(ventaRepository, times(1)).findById(anyLong());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testVentaCantidadExceptions() {
        when(clienteRepository.findById(1L)).thenReturn(crearCliente01());
        when(productoRepository.findById(1L)).thenReturn(crearProducto01());

        //Test stock antes de la venta
        Integer stock = service.obtenerStockProductoPorId(1L);
        assertEquals(1000, stock);

        //Realizar venta con cantidad 0
        assertThrows(CantidadInvalidaException.class, ()-> {
            Venta venta = service.realizarVenta(1L, 1L, 0);
        });
        //Realizar venta con cantidad mayor al stock
        assertThrows(CantidadInvalidaException.class, ()-> {
            Venta venta = service.realizarVenta(1L, 1L, 1001);
        });

        //Test stock después de la venta
        stock = service.obtenerStockProductoPorId(1L);
        assertEquals(1000, stock);

        verify(clienteRepository, times(2)).findById(anyLong());
        verify(productoRepository, times(4)).findById(anyLong());
        verify(ventaRepository, times(0)).save(any(Venta.class));
    }
}
