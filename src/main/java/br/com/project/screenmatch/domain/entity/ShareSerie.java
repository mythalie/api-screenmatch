package br.com.project.screenmatch.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "share_series")
public class ShareSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;
    private String recipientEmail;
    private String message;
    private LocalDateTime dateSharing = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Serie getSerie() {
        return serie;
    }
}
