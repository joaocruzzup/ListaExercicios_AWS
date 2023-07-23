package org.example.service;

import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

import static org.example.Conexao.getConnection;

public class PermanenciaService {

    private Statement statement;

    public PermanenciaService() {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAll() {
        String sql = "SELECT * FROM permanencias ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idCarro = resultSet.getLong("idCarro");
                String dataSaida = resultSet.getString("data_saida");
                String horaSaida = resultSet.getString("hora_saida");
                BigDecimal valor = resultSet.getBigDecimal("valor");

                System.out.printf("ID da permanência: %d | ID do carro: %d | Data da saída: %s | Hora da saída: %s | Valor: %.2f | %n", id, idCarro, dataSaida, horaSaida, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listById(Long idDigitado) {
        String sql = String.format("SELECT * FROM permanencias WHERE id = '%d'", idDigitado);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idCarro = resultSet.getLong("idCarro");
                String dataSaida = resultSet.getString("data_saida");
                String horaSaida = resultSet.getString("hora_saida");
                BigDecimal valor = resultSet.getBigDecimal("valor");

                System.out.printf("ID da permanência: %d | ID do carro: %d | Data da saída: %s | Hora da saída: %s | Valor: %.2f | %n", id, idCarro, dataSaida, horaSaida, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ToDo Adicionar uma lógica para calcular o valor a ser pago
    public void add(Long idCarro, String data_saida, String hora_saida) {
        String sql = String.format("INSERT INTO clientes (idCarro, data_saida, hora_saida, data_entrada, hora_entrada)" +
                        " VALUES ('%d', '%s', '%s')",
                idCarro, data_saida, hora_saida);
        try {
            statement.executeUpdate(sql);
            System.out.println("Permanência adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ToDo Pensar numa forma de atualizar a permanência
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
        String sql = String.format("DELETE FROM permanencias WHERE id = '%d'", id);
        try {
            statement.executeUpdate(sql);
            System.out.println("Carro de ID " + id + " deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
