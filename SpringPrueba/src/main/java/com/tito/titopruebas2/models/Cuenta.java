package com.tito.titopruebas2.models;

import com.tito.titopruebas2.exceptions.DineroInsuficienteException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "nombre")
    private String persona;
    private BigDecimal saldo;

    public void debito(BigDecimal monto){
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DineroInsuficienteException("No existe saldo suficiente en la cuenta!");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito (BigDecimal monto){
        this.saldo = saldo.add(monto);
    }
}
