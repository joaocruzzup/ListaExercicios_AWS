package org.example.repository;

public interface PermanenciaRepository {
    void listAll();
    void listById(Long id);
    void add(Long idCarro, String data_saida, String hora_saida);
    void update(Long id, String coluna, String valorAtualizado);
    void delete(Long id);
}
