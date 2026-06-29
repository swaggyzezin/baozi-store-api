package com.baozi.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class PedidoRequest {
    @NotNull
    private Long clienteId;

    @NotNull
    private Long produtoId;

    @NotNull
    @Min(1)
    private Integer quantidade;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
