package org.example.view;

import org.example.controller.CarroController;
import org.example.controller.PermanenciaController;
import org.example.service.CarroService;
import org.example.service.PermanenciaService;

import java.util.Scanner;

import static org.example.view.MenuView.limparConsole;
import static org.example.view.MenuView.selecionarOpcao;

public class PermanenciaView {

    private PermanenciaController permanenciaController;
    private Scanner sc;

    public PermanenciaView() {
        PermanenciaService permanenciaService = new PermanenciaService();
        sc = new Scanner(System.in);
        permanenciaController = new PermanenciaController(permanenciaService);
    }

    public void imprimirPermanenciaMenu(){
        int opcao;
        do {
            System.out.println("------ Menu Permanências ------");
            System.out.println("Escolha uma das seguintes opções");
            System.out.println("1 - Consultar todas as Permanências");
            System.out.println("2 - Consultar Permanência específica");
            System.out.println("3 - Cadastrar Permanência");
            System.out.println("4 - Atualizar Permanência");
            System.out.println("5 - Deletar Permanência");

            opcao = selecionarOpcao();
            limparConsole();

            switch (opcao) {
                case 1:
                    permanenciaController.listAll();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 2:
                    imprimirConsultarPermanenciaEspecifico();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 3:
                    imprimirCadastrarPermanencia();
                    System.out.println("Digite ENTER para voltar ao Menu");
                    sc.nextLine();
                    limparConsole();
                    break;
                case 4:
                    imprimirAtualizarPermanencia();
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

    public void imprimirConsultarPermanenciaEspecifico(){
        System.out.print("Digite o ID do cliente ");
        Long id = sc.nextLong();
        sc.nextLine();
        permanenciaController.listById(id);
    }

    //ToDo verificar se está sendo adicionado no formato correto
    public void imprimirCadastrarPermanencia(){
        System.out.print("Digite o id da permanência: ");
        Long idPermanencia = sc.nextLong();
        System.out.print("Digite a data de saída");
        String data = sc.nextLine();
        System.out.print("Digite a hora de saída");
        String hora = sc.nextLine();

        permanenciaController.add(idPermanencia, data, hora);
    }

    public void imprimirAtualizarPermanencia(){
        System.out.println("Digite o id da Permanência: ");
        Long idPermanencia = sc.nextLong();
        sc.nextLine();
        System.out.println("Você deseja atualizar qual informação? (data de saída, hora de saída)");
        System.out.println("Digite aqui: ");
        String coluna = sc.nextLine();
        System.out.println("Digite o novo valor que você deseja atualizar");
        String valorAtualizado = sc.nextLine();
        permanenciaController.update(idPermanencia, coluna, valorAtualizado);
    }
}