package com.example.segundoparcialhurtado.repositories;

import com.example.segundoparcialhurtado.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
