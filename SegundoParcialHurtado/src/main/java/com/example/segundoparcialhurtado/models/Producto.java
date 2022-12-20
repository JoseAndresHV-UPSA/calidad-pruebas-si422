package com.example.segundoparcialhurtado.models;

import com.example.segundoparcialhurtado.exceptions.CantidadInvalidaException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Entity
@Table(name = "Productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    private Integer stock;

    public void disminuirStock(Integer cantidad) {
        if (cantidad <= 0) {
            throw new CantidadInvalidaException("La cantidad a vender debe ser mayor a 0");
        }
        if (cantidad > this.stock) {
            throw new CantidadInvalidaException("La cantidad a vender es mayor al stock");
        }
        this.stock -= cantidad;
    }
}
