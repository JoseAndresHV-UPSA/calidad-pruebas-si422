package com.tito.titopruebas2.controllers;

import com.tito.titopruebas2.models.Cuenta;
import com.tito.titopruebas2.models.TransaccionDTO;
import com.tito.titopruebas2.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta detalleCuenta(@PathVariable Long id) {
        return cuentaService.findById(id);
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto) {
        cuentaService.transferencias(dto. getCuentaOrigenId(), dto.getCuentaDestinoId(),
                                     dto.getMonto(), dto.getBancoId());
        //Crear el json
        Map<String, Object> response = new HashMap<>();
        response.put("Fecha", LocalDate.now().toString());
        response.put("Estado", "OK");
        response.put("Mensaje", "La transferencia se realizó con éxito");
        response.put("Transaccion", dto);

        return ResponseEntity.ok(response);
    }
}
