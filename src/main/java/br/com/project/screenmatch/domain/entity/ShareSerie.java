package br.com.project.screenmatch.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
}
