package Integrador;

import java.util.Scanner;

import Integrador.Controllers.CardController;
import Integrador.Controllers.UserController;
import repositories.CardRepositories;
import repositories.UserRepositories;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardRepositories cardRepository = new CardRepositories();
        UserRepositories userRepository = new UserRepositories();
        CardController cardController = new CardController(cardRepository, scanner);
        UserController userController = new UserController(userRepository, scanner);

        // Sessão de login
        boolean loginSuccess = false;
        while (!loginSuccess) {
            System.out.println("Bem-vindo! Faça login para continuar.");
            loginSuccess = userController.login();
        }

        boolean sair = false;
        while (!sair) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciamento de Cartões");
            System.out.println("2. Gerenciamento de Usuários");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    menuGerenciamentoCartoes(cardController);
                    break;
                case 2:
                    menuGerenciamentoUsuarios(userController);
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
        scanner.close();
    }

    private static void menuGerenciamentoCartoes(CardController cardController) {
        Scanner scanner = new Scanner(System.in);
        boolean voltar = false;

        while (!voltar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar novo cartão");
            System.out.println("2. Buscar cartão por ID");
            System.out.println("3. Atualizar cartão por ID");
            System.out.println("4. Excluir cartão por ID");
            System.out.println("5. Mostrar todos os cartões");
            System.out.println("6. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cardController.criarCartao();
                    break;
                case 2:
                    cardController.buscarCartao();
                    break;
                case 3:
                    cardController.atualizarCartao();
                    break;
                case 4:
                    cardController.excluirCartao();
                    break;
                case 5:
                    cardController.listarCartoesComNomeDoTipo();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }

    private static void menuGerenciamentoUsuarios(UserController userController) {
        Scanner scanner = new Scanner(System.in);
        boolean voltar = false;

        while (!voltar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Buscar usuário por ID");
            System.out.println("3. Atualizar usuário por ID");
            System.out.println("4. Excluir usuário por ID");
            System.out.println("5. Mostrar todos os usuários");
            System.out.println("6. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    userController.criarUsuario();
                    break;
                case 2:
                    userController.buscarUsuarioPorId();
                    break;
                case 3:
                    userController.atualizarUsuario();
                    break;
                case 4:
                    userController.excluirUsuario();
                    break;
                case 5:
                    userController.listarTodosUsuarios();
                    break;
                case 6:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }
}
