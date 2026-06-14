package com.api.av1.controller;

import com.api.av1.model.Cliente;
import com.api.av1.model.Item;
import com.api.av1.model.Pedido;
import com.api.av1.model.Produto;
import com.api.av1.repository.ClienteRepository;
import com.api.av1.repository.PedidoRepository;
import com.api.av1.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/av1/teste")
public class TesteController {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public TesteController(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarDados() {
        try {
            Cliente cliente = new Cliente("11122233344", "Cliente Teste", "teste@email.com", "999999999");
            clienteRepository.save(cliente);

            Produto produto = new Produto("SKU-TV", "Televisão Teste", 10);
            produtoRepository.save(produto);

            Pedido pedido = new Pedido();
            pedido.setOrderId("PEDIDO-TESTE-01");
            pedido.setCliente(cliente);
            pedido.setValorTotal(new BigDecimal("5000.00"));
            pedido.setStatus("Pendente");

            Item item = new Item();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(5);
            item.setPrecoUnitario(new BigDecimal("1000.00"));

            List<Item> itens = new ArrayList<>();
            itens.add(item);
            pedido.setItens(itens);

            pedidoRepository.save(pedido);

            return ResponseEntity.ok("Dados de teste gerados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
