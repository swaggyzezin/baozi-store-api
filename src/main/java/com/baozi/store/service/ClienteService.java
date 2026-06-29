package com.baozi.store.service;

import com.baozi.store.exception.ResourceNotFoundException;
import com.baozi.store.model.Cliente;
import com.baozi.store.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));
    }

    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id " + id);
        }
        clienteRepository.deleteById(id);
    }

    public Cliente update(Long id, Cliente clienteDetails) {
        Cliente cliente = findById(id);
        cliente.setNome(clienteDetails.getNome());
        cliente.setClienteDesde(clienteDetails.getClienteDesde());
        return clienteRepository.save(cliente);
    }
}
