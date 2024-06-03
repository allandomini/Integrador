package Integrador.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Integrador.ConectServer;
import Integrador.entidades.card;
import Integrador.entidades.tipo;
import repositories.CardRepositories;

public class CardController {
    private CardRepositories cardRepository;
    private Scanner scanner;

    public CardController(CardRepositories cardRepository, Scanner scanner) {
        this.cardRepository = cardRepository;
        this.scanner = scanner;
    }

    public void criarCartao() {
        System.out.println("Digite o título do cartão:");
        String titulo = scanner.nextLine();
        System.out.println("Digite o ID do tipo:");
        long tipoId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        card novoCartao = new card(null, titulo, tipoId);
        cardRepository.create(novoCartao);
        System.out.println("Cartão criado com sucesso!");
    }

    public void buscarCartao() {
        System.out.println("Digite o ID do cartão que deseja buscar:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        card cartao = (card) cardRepository.findById(id);
        if (cartao != null) {
            System.out.println("Cartão encontrado:");
            System.out.println("ID: " + cartao.getCardId());
            System.out.println("Título: " + cartao.getTituloCard());
            System.out.println("ID do Tipo: " + cartao.getTipoIdFk());
        } else {
            System.out.println("Nenhum cartão encontrado com o ID fornecido.");
        }
    }

    public void atualizarCartao() {
        System.out.println("Digite o ID do cartão que deseja atualizar:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Escolha o novo tipo do cartão:");

        // Buscar todos os tipos disponíveis
        List<tipo> tipos = buscarTiposDisponiveis();
        for (tipo tipo : tipos) {
            System.out.println(tipo.getTipoId() + ". " + tipo.getDescricao());
        }

        System.out.println("Selecione o ID do novo tipo:");
        long novoTipoId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        card cartao = new card(id, null, novoTipoId);
        cardRepository.updateById(cartao);
        System.out.println("Cartão atualizado com sucesso!");
    }

    public void excluirCartao() {
        System.out.println("Digite o ID do cartão que deseja excluir:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do scanner

        cardRepository.delete(id);
        System.out.println("Cartão excluído com sucesso!");
    }

    public void listarCartoesComNomeDoTipo() {
        List<card> cartoes = cardRepository.findAll();
        for (card cartao : cartoes) {
            String nomeTipo = buscarNomeTipo(cartao.getTipoIdFk());
            System.out.println("ID: " + cartao.getCardId());
            System.out.println("Título: " + cartao.getTituloCard());
            System.out.println("Tipo: " + nomeTipo);
            System.out.println("--------------------");
        }
    }

    public void listarCartoesPorBoard(long boardId) {
        List<card> cartoes = buscarCartoesPorBoard(boardId);
        for (card cartao : cartoes) {
            String nomeTipo = buscarNomeTipo(cartao.getTipoIdFk());
            System.out.println("ID: " + cartao.getCardId());
            System.out.println("Título: " + cartao.getTituloCard());
            System.out.println("Tipo: " + nomeTipo);
            System.out.println("--------------------");
        }
    }

    private List<tipo> buscarTiposDisponiveis() {
        List<tipo> tipos = new ArrayList<>();

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tipo");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long tipoId = rs.getLong("tipo_id");
                String descricao = rs.getString("descricao");
                tipo tipo = new tipo(tipoId, descricao);
                tipos.add(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipos;
    }

    private String buscarNomeTipo(long tipoId) {
        String nomeTipo = null;

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT descricao FROM tipo WHERE tipo_id = ?")) {

            pstmt.setLong(1, tipoId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nomeTipo = rs.getString("descricao");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nomeTipo;
    }

    private List<card> buscarCartoesPorBoard(long boardId) {
        List<card> cartoes = new ArrayList<>();

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM card WHERE board_id_fk = ?")) {

            pstmt.setLong(1, boardId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                long cardId = rs.getLong("card_id");
                String titulo = rs.getString("titulo_card");
                long tipoId = rs.getLong("tipo_id_fk");

                card cartao = new card(cardId, titulo, tipoId);
                cartoes.add(cartao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartoes;
    }
}
