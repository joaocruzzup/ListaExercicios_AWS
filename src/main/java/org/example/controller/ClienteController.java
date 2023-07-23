package org.example.controller;

import org.example.repository.ClienteRepository;
import org.example.service.ClienteService;

public class ClienteController implements ClienteRepository {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void listAll() {
        clienteService.listAll();
    }

    @Override
    public void listById(Long id) {
        clienteService.listById(id);
    }

    @Override
    public void add(String nome, String cpf) {
        clienteService.add(nome, cpf);
    }

    @Override
    public void update(Long id, String coluna, String valorAtualizado) {
        clienteService.update(id, coluna, valorAtualizado);
    }

    @Override
    public void delete(Long id) {
        clienteService.delete(id);
    }
}
