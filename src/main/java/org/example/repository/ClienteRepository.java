package org.example.repository;

public interface ClienteRepository {
    void listAll();
    void listById(Long id);
    void add(String nome, String cpf);
    void update(Long id, String coluna, String valorAtualizado);
    void delete(Long id);
}
