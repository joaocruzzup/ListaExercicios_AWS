package org.example.repository;

public interface CarroRepository {
    void listAll();
    void listById(Long id);
    void add(Long idCliente, String nome, String placa, String data, String hora);
    void update(Long id, String coluna, String valorAtualizado);
    void delete(Long id);
}
