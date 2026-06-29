package com.baozi.store;

import com.baozi.store.model.Cliente;
import com.baozi.store.model.Produto;
import com.baozi.store.repository.ClienteRepository;
import com.baozi.store.repository.PedidoRepository;
import com.baozi.store.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        produtoRepository.deleteAll();
    }

    @Test
    void shouldCreatePedidoWithExistingClienteAndProduto() throws Exception {
        Cliente cliente = clienteRepository.save(new Cliente(null, "Ana", LocalDate.of(2024, 1, 10)));
        Produto produto = produtoRepository.save(new Produto(null, "Baozi", new BigDecimal("7.50"), true));

        String payload = "{\"clienteId\":" + cliente.getId() + ",\"produtoId\":" + produto.getId() + ",\"quantidade\":3}";

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.quantidade").value(3))
                .andExpect(jsonPath("$.cliente.id").value(cliente.getId()))
                .andExpect(jsonPath("$.produto.id").value(produto.getId()));
    }
}
