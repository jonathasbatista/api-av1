package com.api.av1.service;

import com.api.av1.model.*;
import com.api.av1.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProcessamentoPedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentoRepository movimentoRepository;
    private final CompraRepository compraRepository;

    public ProcessamentoPedidoService(PedidoRepository pedidoRepository,
                                      ProdutoRepository produtoRepository,
                                      MovimentoRepository movimentoRepository,
                                      CompraRepository compraRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.movimentoRepository = movimentoRepository;
        this.compraRepository = compraRepository;
    }

    @Transactional
    public void processarFilaDePedidos() {
        List<Pedido> pedidosPendentes = pedidoRepository.findPedidosPendentesOrdenadosPorValor();

        for (Pedido pedido : pedidosPendentes) {
            boolean estoqueSuficiente = verificarEstoque(pedido);

            if (estoqueSuficiente) {
                atenderPedido(pedido);
            } else {
                gerarNecessidadeDeCompra(pedido);
            }
        }
    }

    private boolean verificarEstoque(Pedido pedido) {
        for (Item item : pedido.getItens()) {
            Produto produto = item.getProduto();
            if (!produto.temEstoqueSuficiente(item.getQuantidade())) {
                return false;
            }
        }
        return true;
    }

    private void atenderPedido(Pedido pedido) {
        for (Item item : pedido.getItens()) {
            Produto produto = item.getProduto();

            produto.removerEstoque(item.getQuantidade());
            produtoRepository.save(produto);

            Movimento movimento = new Movimento(
                    produto,
                    pedido.getOrderId(),
                    "SAIDA",
                    item.getQuantidade(),
                    produto.getEstoqueAtual()
            );
            movimentoRepository.save(movimento);
        }

        pedido.marcarComoAtendido();
        pedidoRepository.save(pedido);
    }

    private void gerarNecessidadeDeCompra(Pedido pedido) {
        for (Item item : pedido.getItens()) {
            Produto produto = item.getProduto();

            if (!produto.temEstoqueSuficiente(item.getQuantidade())) {
                Compra compra = new Compra(produto, item.getQuantidade());
                compraRepository.save(compra);
            }
        }

        pedido.marcarAguardandoEstoque();
        pedidoRepository.save(pedido);
    }
}
