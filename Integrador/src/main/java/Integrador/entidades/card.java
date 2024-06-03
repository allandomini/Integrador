package Integrador.entidades;

public class card {
    private Long cardId;
    private String tituloCard;
    private Long tipoIdFk;

    public card(Long cardId, String tituloCard, Long tipoIdFk) {
        this.cardId = cardId;
        this.tituloCard = tituloCard;
        this.tipoIdFk = tipoIdFk;
    }

    // Getters and setters

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getTituloCard() {
        return tituloCard;
    }

    public void setTituloCard(String tituloCard) {
        this.tituloCard = tituloCard;
    }

    public Long getTipoIdFk() {
        return tipoIdFk;
    }

    public void setTipoIdFk(Long tipoIdFk) {
        this.tipoIdFk = tipoIdFk;
    }
}

