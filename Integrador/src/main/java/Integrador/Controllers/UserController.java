package Integrador.Controllers;

import java.util.List;
import java.util.Scanner;
import Integrador.entidades.User;
import repositories.UserRepositories;

public class UserController {
    private UserRepositories userRepository;
    private Scanner scanner;

    public UserController(UserRepositories userRepository, Scanner scanner) {
        this.userRepository = userRepository;
        this.scanner = scanner;
    }

    public void criarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha do usuário:");
        String senha = scanner.nextLine();

        User novoUsuario = new User(null, nome, senha);
        userRepository.create(novoUsuario);
        System.out.println("Usuário criado com sucesso!");
    }

    public void buscarUsuarioPorId() {
        System.out.println("Digite o ID do usuário que deseja buscar:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        User usuario = userRepository.findById(id);
        if (usuario != null) {
            System.out.println("Usuário encontrado:");
            System.out.println("ID: " + usuario.getUserId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Senha: " + usuario.getSenha());
        } else {
            System.out.println("Nenhum usuário encontrado com o ID fornecido.");
        }
    }

    public void atualizarUsuario() {
        System.out.println("Digite o ID do usuário que deseja atualizar:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite o novo nome do usuário:");
        String novoNome = scanner.nextLine();
        System.out.println("Digite a nova senha do usuário:");
        String novaSenha = scanner.nextLine();

        User usuario = new User(id, novoNome, novaSenha);
        userRepository.updateById(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    public void excluirUsuario() {
        System.out.println("Digite o ID do usuário que deseja excluir:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        userRepository.delete(id);
        System.out.println("Usuário excluído com sucesso!");
    }

    public void listarTodosUsuarios() {
        List<User> usuarios = userRepository.findAll();
        for (User usuario : usuarios) {
            System.out.println("ID: " + usuario.getUserId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Senha: " + usuario.getSenha());
            System.out.println("--------------------");
        }
    }

    public boolean login() {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();

        User usuario = userRepository.findByName(nome);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
            return false;
        }
    }
}
