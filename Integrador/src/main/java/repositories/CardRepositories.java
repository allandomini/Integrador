package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.ConectServer;
import Integrador.entidades.card;

public class CardRepositories implements BasicCrud {

	@Override
	public Object create(Object object) {
		 card card = (card) object;
	        String sql = "INSERT INTO card (titulo_card, tipo_id_fk) VALUES (?, ?)";

	        try (Connection conn = ConectServer.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	            pstmt.setString(1, card.getTituloCard());
	            pstmt.setLong(2, card.getTipoIdFk());
	            pstmt.executeUpdate();

	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    card.setCardId(generatedKeys.getLong(1));
	                } else {
	                    throw new SQLException("Creating card failed, no ID obtained.");
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return card;
	}

	@Override
	public Object findById(Long id) {
        card card = null;
        String sql = "SELECT * FROM card WHERE card_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    card = new card(rs.getLong("card_id"), rs.getString("titulo_card"), rs.getLong("tipo_id_fk"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;

	}

	@Override
	public Object updateById(Object object) {
        card card = (card) object;
        String sql = "UPDATE card SET titulo_card = ?, tipo_id_fk = ? WHERE card_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, card.getTituloCard());
            pstmt.setLong(2, card.getTipoIdFk());
            pstmt.setLong(3, card.getCardId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;

	}
	public List<card> findAll() {
	    List<card> cards = new ArrayList<>();
	    String sql = "SELECT * FROM card";

	    try (Connection conn = ConectServer.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            card card = new card(rs.getLong("card_id"), rs.getString("titulo_card"), rs.getLong("tipo_id_fk"));
	            cards.add(card);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cards;
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
