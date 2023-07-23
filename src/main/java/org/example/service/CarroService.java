package org.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

import static org.example.Conexao.getConnection;

public class CarroService {

    private Statement statement;

    public CarroService() {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAll() {
        String sql = "SELECT * FROM carros ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idCliente = resultSet.getLong("id_cliente");
                String nome = resultSet.getString("nome");
                String placa = resultSet.getString("placa");
                LocalDate dataEntrada = resultSet.getDate("data_entrada").toLocalDate();
                Time hora = resultSet.getTime("hora_entrada");

                System.out.printf("ID do Carro: %d | ID do cliente: %s | Nome: %s | Placa: %s | Data de entrada: %s | Hora de entrada: %s |%n", id, idCliente, nome, placa, dataEntrada, hora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listById(Long idDigitado) {
        String sql = String.format("SELECT * FROM carros WHERE id = '%d'", idDigitado);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idCliente = resultSet.getLong("id_cliente");
                String nome = resultSet.getString("nome");
                String placa = resultSet.getString("placa");
                LocalDate dataEntrada = resultSet.getDate("data_entrada").toLocalDate();
                Time hora = resultSet.getTime("hora_entrada");

                System.out.printf("ID do Carro: %d | ID do cliente: %s | Nome: %s | Placa: %s | Data de entrada: %s | Hora de entrada: %s |%n", id, idCliente, nome, placa, dataEntrada, hora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Long idCliente, String nome, String placa, String data, String hora) {
        String sql = String.format("INSERT INTO clientes (id_cliente, nome, placa, data_entrada, hora_entrada)" +
                        " VALUES ('%d', '%s', '%s', '%s', '%s')",
                idCliente, nome, placa, data, hora);
        try {
            statement.executeUpdate(sql);
            System.out.println("Carro " + nome + " adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, String coluna, String valorAtualizado) {
        if (coluna.equalsIgnoreCase("nome") || coluna.equalsIgnoreCase("placa")) {
            String sql = String.format("UPDATE carros SET %s = '%s' where id = '%d'", coluna, valorAtualizado, id);
            try {
                statement.executeUpdate(sql);
                System.out.println("Carro de ID " + id + " atualizado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Apenas é possível atualizar nome ou placa");
        }
    }

    public void delete(Long id) {
        String sql = String.format("DELETE FROM carros WHERE id = '%d'", id);
        try {
            statement.executeUpdate(sql);
            System.out.println("Carro de ID " + id + " deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
