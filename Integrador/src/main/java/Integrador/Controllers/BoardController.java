package Integrador.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Integrador.ConectServer;

public class BoardController {
    private Scanner scanner;

    public BoardController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void criarBoard() {
        System.out.print("Digite o título do novo board: ");
        String titulo = scanner.nextLine();

        String sql = "INSERT INTO board (titulo) VALUES (?)";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, titulo);
            pstmt.executeUpdate();

            System.out.println("Novo board criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar o board: " + e.getMessage());
        }
    }

    public void listarBoards() {
        System.out.println("=== Boards ===");

        String sql = "SELECT * FROM board";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                long boardId = rs.getLong("board_id");
                String titulo = rs.getString("titulo");
                System.out.println(boardId + ". " + titulo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os boards: " + e.getMessage());
        }
    }

    public void abrirBoard() {
        listarBoards();
        System.out.print("Digite o ID do board que deseja abrir: ");
        long boardId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer

        String sql = "SELECT * FROM board WHERE board_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, boardId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                System.out.println("=== Detalhes do Board ===");
                System.out.println("ID: " + boardId);
                System.out.println("Título: " + titulo);
            } else {
                System.out.println("Board não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao abrir o board: " + e.getMessage());
        }
    }

    public void deletarBoard() {
        listarBoards();
        System.out.print("Digite o ID do board que deseja deletar: ");
        long boardId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer

        String sql = "DELETE FROM board WHERE board_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, boardId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Board deletado com sucesso!");
            } else {
                System.out.println("Nenhum board encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o board: " + e.getMessage());
        }
    }

    public void atualizarBoard() {
        listarBoards();
        System.out.print("Digite o ID do board que deseja atualizar: ");
        long boardId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Digite o novo título do board: ");
        String novoTitulo = scanner.nextLine();

        String sql = "UPDATE board SET titulo = ? WHERE board_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, novoTitulo);
            pstmt.setLong(2, boardId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Board atualizado com sucesso!");
            } else {
                System.out.println("Nenhum board encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o board: " + e.getMessage());
        }
    }
}
