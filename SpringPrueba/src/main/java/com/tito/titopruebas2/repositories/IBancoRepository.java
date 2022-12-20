package com.tito.titopruebas2.repositories;

import com.tito.titopruebas2.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBancoRepository extends JpaRepository<Banco, Long> {

}
