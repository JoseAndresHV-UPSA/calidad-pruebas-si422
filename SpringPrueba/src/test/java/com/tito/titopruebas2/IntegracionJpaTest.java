package com.tito.titopruebas2;

import com.tito.titopruebas2.models.Cuenta;
import com.tito.titopruebas2.repositories.ICuentaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    ICuentaRepository iCuentaRepository;

    @Test
    void testFindById() {
        //Contexto
        Optional<Cuenta> cuenta = iCuentaRepository.findById(1L);

        //Probar
        assertTrue(cuenta.isPresent());
        assertEquals("Tito", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersona() {
        //Contexto
        Optional<Cuenta> cuenta = iCuentaRepository.findByPersona("Tito");

        //Probar
        assertTrue(cuenta.isPresent());
        assertEquals("Tito", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersonaException() {
        //Contexto
        Optional<Cuenta> cuenta = iCuentaRepository.findByPersona("Toti");

        //Activar la exception
        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);

        //Probar
        assertFalse(cuenta.isPresent());
    }

    @Test
    void testFindAll() {
        //Contexto given
        List<Cuenta> cuentas = iCuentaRepository.findAll();

        //Probar
        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }

    @Test
    void testSave() {
        //Contexto given
        Cuenta cuenta = new Cuenta(
                null,
                "Luisa",
                new BigDecimal("3000")
        );
        iCuentaRepository.save(cuenta);

        //Contexto when
        Cuenta cuentaGuardada = iCuentaRepository.findByPersona("Luisa").orElseThrow();

        //Contexto then
        assertEquals(3L, cuentaGuardada.getId());
        assertEquals("Luisa", cuentaGuardada.getPersona());
        assertEquals("3000", cuentaGuardada.getSaldo().toPlainString());
    }

    @Test
    void testUpdate() {
        //Contexto given
        Cuenta cuenta = iCuentaRepository.findById(1L).orElseThrow();
        cuenta.setSaldo(new BigDecimal("2000"));
        iCuentaRepository.save(cuenta);

        //Contexto when
        Cuenta cuentaActualizada = iCuentaRepository.findById(1L).orElseThrow();

        //Contexto then
        assertEquals(1L, cuentaActualizada.getId());
        assertEquals("Tito", cuentaActualizada.getPersona());
        assertEquals("2000", cuentaActualizada.getSaldo().toPlainString());
    }

    @Test
    void testDelete() {
        //Contexto given
        Cuenta cuenta = iCuentaRepository.findById(1L).orElseThrow();
        iCuentaRepository.delete(cuenta);

        //Contexto when
        Optional<Cuenta> cuentaEliminada = iCuentaRepository.findById(1L);
        List<Cuenta> cuentas = iCuentaRepository.findAll();

        //Contexto then
        assertFalse(cuentaEliminada.isPresent());
        assertEquals(1, cuentas.size());
    }
}