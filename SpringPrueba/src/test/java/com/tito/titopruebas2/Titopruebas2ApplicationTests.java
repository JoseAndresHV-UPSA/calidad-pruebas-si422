package com.tito.titopruebas2;

import com.tito.titopruebas2.exceptions.DineroInsuficienteException;
import com.tito.titopruebas2.models.Banco;
import com.tito.titopruebas2.models.Cuenta;
import com.tito.titopruebas2.repositories.IBancoRepository;
import com.tito.titopruebas2.repositories.ICuentaRepository;
import com.tito.titopruebas2.services.CuentaServiceImpl;
import com.tito.titopruebas2.services.ICuentaService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static com.tito.titopruebas2.Datos.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.tito.titopruebas2.Datos.*;

import java.math.BigDecimal;


@SpringBootTest
class Titopruebas2ApplicationTests {

    //Crear nuestro propios Mocks
    @MockBean
    ICuentaRepository iCuentaRepository;
    @MockBean
    IBancoRepository iBancoRepository;
    @Autowired
    CuentaServiceImpl iCuentaService;

    @Test
    void contextLoads2() {
        //Configurar el contexto para las pruebas
        when(iCuentaRepository.findById(1L)).thenReturn(crearCuenta01());
        when(iCuentaRepository.findById(2L)).thenReturn(crearCuenta02());
        when(iBancoRepository.findById(1L)).thenReturn(crearBanco());

        //Realizar las pruebas
        //Revisar como se encuentran los saldos antes de hacer la transferencia
        BigDecimal saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        BigDecimal saldoDestino = iCuentaService.revisarSaldoCuenta(2L);

        //Ahora podemos probar o testear los saldos
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        //Probar o testear la transferencia
        iCuentaService.transferencias(1L, 2L, new BigDecimal("100"),1L);
        //Debemos probar en cuanto quedaron los saldos después de hacer la transferencia
        saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        saldoDestino = iCuentaService.revisarSaldoCuenta(2L);

        int total = iCuentaService.revisarTotalTransferencia(1L);
        assertEquals(1,total);

        assertEquals("900",saldoOrigen.toPlainString());
        assertEquals("2100",saldoDestino.toPlainString());

        verify(iCuentaRepository, times(3)).findById(1L);
        verify(iCuentaRepository, times(3)).findById(2L);
        verify(iCuentaRepository, times(2)).save(any(Cuenta.class));

        verify(iBancoRepository, times(2)).findById(1L);
        verify(iBancoRepository).save(any(Banco.class));

    }

    @Test
    void contextLoads() {
        //Configurar el contexto para las pruebas
        when(iCuentaRepository.findById(1L)).thenReturn(crearCuenta01());
        when(iCuentaRepository.findById(2L)).thenReturn(crearCuenta02());
        when(iBancoRepository.findById(1L)).thenReturn(crearBanco());

        //Realizar las pruebas
        //Revisar como se encuentran los saldos antes de hacer la transferencia
        BigDecimal saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        BigDecimal saldoDestino = iCuentaService.revisarSaldoCuenta(2L);

        //Ahora podemos probar o testear los saldos
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        //Probar si la excepción salta cuando queremos debitar un monto que no existe o un monto mayor al saldo que existe.
        assertThrows(DineroInsuficienteException.class, ()-> {
            iCuentaService.transferencias(1L, 2L, new BigDecimal("1200"),1L);
        });

        //Probar o testear la transferencia
        //Debemos probar en cuanto quedaron los saldos después de hacer la transferencia
        saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        saldoDestino = iCuentaService.revisarSaldoCuenta(2L);

        int total = iCuentaService.revisarTotalTransferencia(1L);
        assertEquals(0,total);

        assertEquals("1000",saldoOrigen.toPlainString());
        assertEquals("2000",saldoDestino.toPlainString());

        verify(iCuentaRepository, times(3)).findById(1L);
        verify(iCuentaRepository, times(2)).findById(2L);
        verify(iCuentaRepository, never()).save(any(Cuenta.class));

        verify(iBancoRepository).findById(1L);
        verify(iBancoRepository, never()).save(any(Banco.class));

    }

    @Test
    void contextLoads3() {
        when(iCuentaRepository.findById(1L)).thenReturn(crearCuenta01());

        Cuenta cuenta1 = iCuentaService.findById(1L);
        Cuenta cuenta2 = iCuentaService.findById(1L);

        assertSame(cuenta1, cuenta2);
        assertSame("Tito", cuenta1.getPersona());

        verify(iCuentaRepository, times(2)).findById(1L);
    }
}
