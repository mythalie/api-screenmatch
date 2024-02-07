package br.com.project.screenmatch.domain.dto;

import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.domain.entity.ShareSerie;


public class ShareSerieResponse {
    private Long id;
    private Serie serie;
    private String recipientEmail;
    private String message;


    public ShareSerieResponse(ShareSerie shareSerie) {
        this.id = shareSerie.getId();
        this.serie = shareSerie.getSerie();
        this.recipientEmail = shareSerie.getRecipientEmail();
        this.message = shareSerie.getMessage();
    }

    public Long getId() {
        return id;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
