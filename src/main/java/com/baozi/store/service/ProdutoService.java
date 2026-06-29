package com.baozi.store.service;

import com.baozi.store.exception.ResourceNotFoundException;
import com.baozi.store.model.Produto;
import com.baozi.store.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id " + id));
    }

    public void deleteById(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id " + id);
        }
        produtoRepository.deleteById(id);
    }

    public Produto update(Long id, Produto produtoDetails) {
        Produto produto = findById(id);
        produto.setNome(produtoDetails.getNome());
        produto.setPreco(produtoDetails.getPreco());
        produto.setEstoque(produtoDetails.getEstoque());
        return produtoRepository.save(produto);
    }
}
