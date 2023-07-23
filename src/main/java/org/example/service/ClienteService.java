package org.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.Conexao.getConnection;

public class ClienteService {

    private Statement statement;

    public ClienteService() {
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAll() {
        String sql = "SELECT * FROM clientes ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");

                System.out.printf("ID: %d | Nome: %s | CPF: %s | %n", id, nome, cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listById(Long idDigitado) {
        String sql = String.format("SELECT * FROM clientes WHERE id = '%d'", idDigitado);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");

                System.out.printf("ID: %d | Nome: %s | CPF: %s | %n", id, nome, cpf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String nome, String cpf) {
        String sql = String.format("INSERT INTO clientes (nome, cpf)" +
                        " VALUES ('%s', '%s')",
                nome, cpf);
        try {
            statement.executeUpdate(sql);
            System.out.println("Cliente " + nome + " adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, String coluna, String valorAtualizado) {
        if (coluna.equalsIgnoreCase("nome") || coluna.equalsIgnoreCase("cpf")) {
            String sql = String.format("UPDATE clientes SET %s = '%s' where id = '%d'", coluna, valorAtualizado, id);
            try {
                statement.executeUpdate(sql);
                System.out.println("Cliente de ID " + id + " atualizado com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Apenas é possível atualizar nome ou cpf");
        }
    }

    public void delete(Long id) {
        String sql = String.format("DELETE FROM clientes WHERE id = '%d'", id);
        try {
            statement.executeUpdate(sql);
            System.out.println("Cliente de ID " + id + " deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
