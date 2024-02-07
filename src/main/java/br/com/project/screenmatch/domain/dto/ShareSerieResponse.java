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
}
