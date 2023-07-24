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

    public CarroView() {
        CarroService carroService = new CarroService();
        sc = new Scanner(System.in);
        carroController = new CarroController(carroService);
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
            System.out.println("5 - Deletar Carro");

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

                default:
                    System.out.println("Digite uma opção válida");
                    limparConsole();
                    break;
            }
        } while (opcao != 0);
    }

    public void imprimirConsultarCarroEspecifico(){
        System.out.print("Digite o ID do cliente ");
        Long id = sc.nextLong();
        sc.nextLine();
        carroController.listById(id);
    }

    //ToDo verificar se está sendo adicionado no formato correto
    public void imprimirCadastrarCarro(){
        System.out.print("Digite o id do Cliente: ");
        Long idCliente = sc.nextLong();
        System.out.print("Digite o nome do carro");
        String cpf = sc.nextLine();
        System.out.print("Digite a placa do carro");
        String placa = sc.nextLine();
        System.out.print("Digite a data de entrada");
        String data = sc.nextLine();
        System.out.print("Digite a hora de entrada");
        String hora = sc.nextLine();

        carroController.add(idCliente, cpf, placa, data, hora);
    }

    public void imprimirAtualizarCarro(){
        System.out.println("Digite o id do Carro: ");
        Long idCarro = sc.nextLong();
        sc.nextLine();
        System.out.println("Você deseja atualizar qual informação? (nome, placa)");
        System.out.println("Digite aqui: ");
        String coluna = sc.nextLine();
        System.out.println("Digite o novo valor que você deseja atualizar");
        String valorAtualizado = sc.nextLine();
        carroController.update(idCarro, coluna, valorAtualizado);
    }
}
