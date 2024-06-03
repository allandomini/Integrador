package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Integrador.ConectServer;
import Integrador.entidades.User;

public class UserRepositories {

    public void create(User user) {
        String sql = "INSERT INTO user (nome, senha) VALUES (?, ?)";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getSenha());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE User_id = ?";
        User user = null;

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                user = new User(id, nome, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByName(String nome) {
        String sql = "SELECT * FROM user WHERE nome = ?";
        User user = null;

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("User_id");
                String senha = rs.getString("senha");
                user = new User(id, nome, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("User_id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                users.add(new User(id, nome, senha));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void updateById(User user) {
        String sql = "UPDATE user SET nome = ?, senha = ? WHERE User_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getSenha());
            pstmt.setLong(3, user.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM user WHERE User_id = ?";

        try (Connection conn = ConectServer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
