package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class ShareSerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private EmailService emailService;
    
    public void shareSerie(Long serieId, String recipientEmail, String message) {
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(() -> new NotFoundException("Série não encontrada com o ID: " + serieId));

        emailService.sendEmail(serie, recipientEmail, message);
    }
}
