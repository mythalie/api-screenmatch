package br.com.project.screenmatch.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ShareSerieCreateRequest {
    @NotNull
    private Long serieId;
    @NotBlank
    @Email
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
