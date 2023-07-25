package org.example.view;

import org.example.controller.CarroController;
import org.example.controller.ClienteController;
import org.example.service.CarroService;
import org.example.service.ClienteService;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Scanner;

import static org.example.view.MenuView.limparConsole;
import static org.example.view.MenuView.selecionarOpcao;

public class CarroView {
    private CarroController carroController;
    private Scanner sc;
    private MetodosView metodosView;

    public CarroView() {
        CarroService carroService = new CarroService();
        sc = new Scanner(System.in);
        carroController = new CarroController(carroService);
        metodosView = new MetodosView();
    }

    public void imprimirCarroMenu(){
        int opcao;
        do {
            System.out.println("------ Menu Carros ------");
            System.out.println("Escolha uma das seguintes opções");
            System.out.println("1 - Consultar todos os Carros");
            System.out.println("2 - Consultar Carro específico");
            System.out.println("3 - Cadastrar Carro");
            System.out.println("4 - Atualizar Carro");
            System.out.println("5 - Registrar saída Carro");
            System.out.println("0 - Voltar ao Menu Principal");


            opcao = selecionarOpcao();
            limparConsole();

            switch (opcao) {
                case 1:
                    carroController.listAll();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 2:
                    imprimirConsultarCarroEspecifico();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 3:
                    imprimirCadastrarCarro();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 4:
                    imprimirAtualizarCarro();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 5:
                    imprimirDeleteCarro();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;

                default:
                    System.out.println("Digite uma opção válida");
                    limparConsole();
                    break;
            }
        } while (opcao != 0);
    }

    public void imprimirConsultarCarroEspecifico(){
        System.out.print("Digite o ID do cliente ");
        Long id = metodosView.lerIdValido();
        carroController.listById(id);
    }

    public void imprimirCadastrarCarro(){
        System.out.print("Digite o id do Cliente: ");
        Long idCliente = metodosView.lerIdValido();
        System.out.print("Digite o nome do carro: ");
        String cpf = sc.nextLine();
        System.out.print("Digite a placa do carro: ");
        String placa = sc.nextLine();
        System.out.print("Digite a data de entrada: (YYYY-MM-DD) ");
        String data = metodosView.lerDataValida();
        System.out.print("Digite a hora de entrada: (HH:MM:SS) ");
        String hora = metodosView.lerHoraValida();

        carroController.add(idCliente, cpf, placa, data, hora);
    }

    public void imprimirAtualizarCarro(){
        System.out.println("Digite o id do Carro: ");
        Long idCarro = metodosView.lerIdValido();
        System.out.println("Você deseja atualizar qual informação? (nome, placa)");
        System.out.println("Digite aqui: ");
        String coluna = sc.nextLine();
        System.out.println("Digite o novo valor que você deseja atualizar");
        String valorAtualizado = sc.nextLine();
        carroController.update(idCarro, coluna, valorAtualizado);
    }

    public void imprimirDeleteCarro(){
        System.out.println("Digite o id do carro: ");
        Long idCarro = metodosView.lerIdValido();
        carroController.delete(idCarro);
    }
}
