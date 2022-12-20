package com.tito.titopruebas2.services;

import com.tito.titopruebas2.models.Cuenta;

import java.math.BigDecimal;

public interface ICuentaService {
    Cuenta findById(Long id);
    BigDecimal revisarSaldoCuenta(Long cuentaId);
    int revisarTotalTransferencia(Long bancoId);
    void transferencias(Long nroCuentaOrigen, Long nroCuentaDestino, BigDecimal monto, Long  bancoId);
}
