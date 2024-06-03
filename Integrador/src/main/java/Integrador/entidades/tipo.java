package Integrador.entidades;

public class tipo {
    private Long tipoId;
    private String descricao;

    public tipo(Long tipoId, String descricao) {
        this.tipoId = tipoId;
        this.descricao = descricao;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
