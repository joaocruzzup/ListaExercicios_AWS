package org.example;

import org.example.view.MenuView;

import static org.example.Conexao.getConnection;

public class Main {
    public static void main(String[] args) {
        getConnection();
        MenuView menuView = new MenuView();
        menuView.iniciar();

    }
}
