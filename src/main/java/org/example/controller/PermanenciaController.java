package org.example.controller;

import org.example.repository.PermanenciaRepository;
import org.example.service.PermanenciaService;

public class PermanenciaController implements PermanenciaRepository {
    private PermanenciaService permanenciaService;

    public PermanenciaController(PermanenciaService permanenciaService) {
        this.permanenciaService = permanenciaService;
    }

    @Override
    public void listAll() {
        permanenciaService.listAll();
    }

    @Override
    public void listById(Long id) {
        permanenciaService.listById(id);
    }

    @Override
    public void add(Long idCarro, String data_saida, String hora_saida) {
        permanenciaService.add(idCarro, data_saida, hora_saida);
    }

    @Override
    public void update(Long id, String coluna, String valorAtualizado) {
        permanenciaService.update(id, coluna, valorAtualizado);
    }

    @Override
    public void delete(Long id) {
        permanenciaService.delete(id);
    }
}
