package com.tito.titopruebas2.services;

import com.tito.titopruebas2.models.Banco;
import com.tito.titopruebas2.models.Cuenta;
import com.tito.titopruebas2.repositories.IBancoRepository;
import com.tito.titopruebas2.repositories.ICuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Service
public class CuentaServiceImpl implements ICuentaService{
    private ICuentaRepository iCuentaRepository;
    private IBancoRepository iBancoRepository;

    public CuentaServiceImpl(ICuentaRepository iCuentaRepository, IBancoRepository iBancoRepository) {
        this.iCuentaRepository = iCuentaRepository;
        this.iBancoRepository = iBancoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findById(Long id) {
        return iCuentaRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldoCuenta(Long cuentaId) {
        Cuenta cuenta = iCuentaRepository.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    @Transactional(readOnly = true)
    public int revisarTotalTransferencia(Long bancoId) {
        Banco banco = iBancoRepository.findById(bancoId).orElseThrow();
        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional
    public void transferencias(Long nroCuentaOrigen, Long nroCuentaDestino, BigDecimal monto, Long bancoId) {

        //Debitar la cuenta
        Cuenta cuentaOrigen = iCuentaRepository.findById(nroCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(monto);
        iCuentaRepository.save(cuentaOrigen);

        //Acreditar la cuenta
        Cuenta cuentaDestino = iCuentaRepository.findById(nroCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        iCuentaRepository.save(cuentaDestino);

        //Actualizar el total de transferencias que se está realizando sobre un banco
        Banco banco = iBancoRepository.findById(bancoId).orElseThrow();
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        iBancoRepository.save(banco);
    }
}
