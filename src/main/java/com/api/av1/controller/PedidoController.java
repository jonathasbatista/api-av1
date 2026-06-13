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

    @RestController
    @RequestMapping("/api/v1/fornecedor")
    public class FornecedorController {

        private final ProcessamentoPedidoService processamentoPedidoService;

        public FornecedorController(ProcessamentoPedidoService processamentoPedidoService) {
            this.processamentoPedidoService = processamentoPedidoService;
        }

        @PostMapping("/entrada/{sku}")
        public ResponseEntity<String> registrarEntrada(@PathVariable String sku, @RequestParam Integer quantidade) {
            try {
                processamentoPedidoService.registrarEntradaFornecedor(sku, quantidade);
                return ResponseEntity.ok("Entrada de estoque registrada com sucesso para o SKU: " + sku);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro ao registrar entrada: " + e.getMessage());
            }
        }
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
