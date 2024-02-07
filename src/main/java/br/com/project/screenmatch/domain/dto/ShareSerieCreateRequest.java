package br.com.project.screenmatch.domain.dto;

import br.com.project.screenmatch.domain.entity.Serie;

import java.util.List;

public class ShareSerieCreateRequest {
    private Long serieId;
    private String recipientEmail;
    private String message;

    public String getMessage() {
        return message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public Long getSerieId() {
        return serieId;
    }
}
