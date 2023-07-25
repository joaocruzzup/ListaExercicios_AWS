package org.example.view;

import org.example.controller.ClienteController;
import org.example.service.ClienteService;

import java.util.Scanner;

import static org.example.view.MenuView.limparConsole;
import static org.example.view.MenuView.selecionarOpcao;

public class ClienteView {
    private ClienteController clienteController;
    private MetodosView metodosView;
    private Scanner sc;

    public ClienteView() {
        ClienteService clienteService = new ClienteService();
        sc = new Scanner(System.in);
        clienteController = new ClienteController(clienteService);
        metodosView = new MetodosView();
    }

    public void imprimirClienteMenu(){
        int opcao;
        do {
            System.out.println("------ Menu Cliente ------");
            System.out.println("Escolha uma das seguintes opções");
            System.out.println("1 - Consultar todos os Clientes");
            System.out.println("2 - Consultar Cliente específico");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Deletar Cliente");
            System.out.println("0 - Voltar ao Menu Principal");

            opcao = selecionarOpcao();
            limparConsole();

            switch (opcao) {
                case 1:
                    clienteController.listAll();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 2:
                    imprimirConsultarClienteEspecifico();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 3:
                    imprimirCadastrarCliente();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 4:
                    imprimirAtualizarCliente();
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

    public void imprimirConsultarClienteEspecifico(){
        System.out.print("Digite o ID do cliente ");
        Long id = metodosView.lerIdValido();
        sc.nextLine();
        clienteController.listById(id);
    }

    public void imprimirCadastrarCliente(){
        System.out.print("Digite o nome do Cliente: ");
        String nome = sc.nextLine();
        System.out.print("Digite o cpf do Cliente (123.123.123-12) :");
        String cpf = metodosView.lerCpfValido();
        clienteController.add(nome, cpf);
    }

    public void imprimirAtualizarCliente(){
        System.out.println("Digite o id do Cliente: ");
        Long idCliente = metodosView.lerIdValido();
        sc.nextLine();
        System.out.println("Você deseja atualizar qual informação? (nome, cpf)");
        System.out.println("Digite aqui: ");
        String coluna = sc.nextLine();
        System.out.println("Digite o novo valor que você deseja atualizar");
        String valorAtualizado = sc.nextLine();
        clienteController.update(idCliente, coluna, valorAtualizado);
    }
}
