package org.example.service;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void add(Long idCarro, String data_saida, String hora_saida) {
        BigDecimal valor = BigDecimal.valueOf(permanenciaValue(idCarro));
        int valorFormatado = valor.intValue();
        String sql = String.format("INSERT INTO permanencias (idCarro, data_saida, hora_saida, valor)" +
                        " VALUES ('%d', '%s', '%s', '%s')",
                idCarro, data_saida, hora_saida, valorFormatado);
        try {
            statement.executeUpdate(sql);
            System.out.println("Permanência adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ToDo Pensar numa forma de atualizar a permanência
    public void update(Long id, String coluna, String valorAtualizado) {
        if (coluna.equalsIgnoreCase("valor")) {
            String sql = String.format("UPDATE permanencias SET %s = '%s' where id = '%d'", coluna, valorAtualizado, id);
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

    public long permanenciaValue(long id){
        String sql = String.format("SELECT data_entrada, hora_entrada, data_saida, hora_saida FROM carros " +
                "JOIN permanencias ON carros.id = permanencias.idCarro " +
                "WHERE carros.id = '%d'", id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                String dataEntrada = resultSet.getString("data_entrada");
                String horaEntrada = resultSet.getString("hora_entrada");
                String dataSaida = resultSet.getString("data_saida");
                String horaSaida = resultSet.getString("hora_saida");

                Long minutos = calculePermanenciaValue(dataEntrada, horaEntrada, dataSaida, horaSaida);
                int valor = 0;
                if (minutos <= 60 ){
                    valor = 10;
                }
                else if (minutos <= 720) {
                    int adicional = (int) Math.ceil((minutos - 60) / 30.0);
                    valor = 10 + (adicional * 2);
                }
                else {
                    valor = 90;
                }
                return valor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long calculePermanenciaValue(String dataEntrada, String horaEntrada,
                                        String dataSaida, String horaSaida) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime entrada = LocalDateTime.parse(dataEntrada + " " + horaEntrada , formatter);
        LocalDateTime saida = LocalDateTime.parse(dataSaida + " " + horaSaida , formatter);

        return java.time.Duration.between(entrada, saida).toMinutes();
    }



}
