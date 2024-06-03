package Integrador.entidades;

public class User {
    private Long userId;
    private String nome;
    private String senha;

    public User(Long userId, String nome, String senha) {
        this.userId = userId;
        this.nome = nome;
        this.senha = senha;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
