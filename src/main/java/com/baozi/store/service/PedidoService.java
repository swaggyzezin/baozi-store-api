package com.baozi.store.service;

import com.baozi.store.model.Cliente;
import com.baozi.store.model.Pedido;
import com.baozi.store.model.Produto;
import com.baozi.store.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public Pedido createPedido(Long clienteId, Long produtoId, Integer quantidade) {
        Cliente cliente = clienteService.findById(clienteId);
        Produto produto = produtoService.findById(produtoId);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(quantidade);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new com.baozi.store.exception.ResourceNotFoundException("Pedido não encontrado com id " + id));
    }

    public void deleteById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new com.baozi.store.exception.ResourceNotFoundException("Pedido não encontrado com id " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
