package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    private CarroView carroView;
    private ClienteView clienteView;
    private PermanenciaView permanenciaView;
    private Scanner sc;

    public MenuView(){
        carroView = new CarroView();
        clienteView = new ClienteView();
        permanenciaView = new PermanenciaView();
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            imprimirMenu();
            opcao = selecionarOpcao();
            limparConsole();

            switch (opcao) {
                case 1:
                    clienteView.imprimirClienteMenu();
                    break;
                case 2:
                    carroView.imprimirCarroMenu();
                    break;
                case 3:
                    permanenciaView.imprimirPermanenciaMenu();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    public void imprimirMenu() {
        System.out.println("---- MENU PRINCIPAL ----");
        System.out.println("Escolha uma das seguintes opções");
        System.out.println("1 - Acessar Menu CLIENTE");
        System.out.println("2 - Acessar Menu CARRO");
        System.out.println("3 - Acessar Menu RESERVA/PERMANÊNCIA");
        System.out.println("0 - Sair do programa");
        System.out.println("Digite aqui a opção: ");
    }

    public static int selecionarOpcao() {
        Scanner sc = new Scanner(System.in);
        try {
            int opcao = sc.nextInt();
            sc.nextLine();
            limparConsole();
            return opcao;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            sc.nextLine();
            return 0;
        }
    }

    public static void limparConsole(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
