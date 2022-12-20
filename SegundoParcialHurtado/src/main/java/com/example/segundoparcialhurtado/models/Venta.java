package com.example.segundoparcialhurtado.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Entity
@Table(name = "Ventas")
public class Venta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_producto")
    private Long idProducto;

    private Integer cantidad;

    private BigDecimal subtotal;

    private BigDecimal descuento;

    private BigDecimal total;

    public void calcularTotal(Integer cantidad, BigDecimal precio) {
        this.cantidad = cantidad;
        this.subtotal = precio.multiply(BigDecimal.valueOf(cantidad));

        if (subtotal.compareTo(BigDecimal.valueOf(1000)) >= 0 && subtotal.compareTo(BigDecimal.valueOf(2000)) <= 0) {
            this.descuento = subtotal.multiply(BigDecimal.valueOf(0.2));
        }
        else if (subtotal.compareTo(BigDecimal.valueOf(2000)) > 0 && subtotal.compareTo(BigDecimal.valueOf(3000)) <= 0) {
            this.descuento = subtotal.multiply(BigDecimal.valueOf(0.3));
        }
        else if (subtotal.compareTo(BigDecimal.valueOf(3000)) > 0) {
            this.descuento = subtotal.multiply(BigDecimal.valueOf(0.4));
        }
        else {
            this.descuento = BigDecimal.ZERO;
        }

        this.total = this.subtotal.subtract(this.descuento);
    }
}
