package com.api.av1.controller;

import com.api.av1.service.ProcessamentoPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final ProcessamentoPedidoService processamentoPedidoService;

    public PedidoController(ProcessamentoPedidoService processamentoPedidoService) {
        this.processamentoPedidoService = processamentoPedidoService;
    }

    @PostMapping("/processar-fila")
    public ResponseEntity<String> processarFila() {
        try {
            processamentoPedidoService.processarFilaDePedidos();
            return ResponseEntity.ok("Fila de pedidos processada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao processar fila: " + e.getMessage());
        }
    }
}
