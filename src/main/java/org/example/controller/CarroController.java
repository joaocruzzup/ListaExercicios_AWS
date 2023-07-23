package org.example.controller;

import org.example.repository.CarroRepository;
import org.example.service.CarroService;

public class CarroController implements CarroRepository {

    private CarroService carroService;

    public CarroController (CarroService carroService){
        this.carroService = carroService;
    }

    @Override
    public void listAll() {
        carroService.listAll();
    }

    @Override
    public void listById(Long id) {
        carroService.listById(id);
    }

    @Override
    public void add(Long idCliente, String nome, String placa, String data, String hora) {
        carroService.add(idCliente, nome, placa, data, hora);
    }

    @Override
    public void update(Long id, String coluna, String valorAtualizado) {
        carroService.update(id, coluna, valorAtualizado);
    }

    @Override
    public void delete(Long id) {
        carroService.delete(id);
    }
}
