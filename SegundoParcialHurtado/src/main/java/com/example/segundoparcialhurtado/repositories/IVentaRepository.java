package com.example.segundoparcialhurtado.repositories;

import com.example.segundoparcialhurtado.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
