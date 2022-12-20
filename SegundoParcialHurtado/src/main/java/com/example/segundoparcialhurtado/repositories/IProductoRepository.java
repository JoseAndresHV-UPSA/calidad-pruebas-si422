package com.example.segundoparcialhurtado.repositories;

import com.example.segundoparcialhurtado.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
