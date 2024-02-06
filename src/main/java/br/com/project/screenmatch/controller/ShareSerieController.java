package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.service.ShareSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shareSeries")
public class ShareSerieController {

    @Autowired
    ShareSerieService shareSerieService;

    @PostMapping("/share")
    public ResponseEntity<String> shareSerie(@PathVariable("id") Long serieId,
                                                    @RequestParam("recipientEmail") String recipientEmail,
                                                    @RequestParam("message") String message) {
        shareSerieService.shareSerie(serieId, recipientEmail, message);
        return ResponseEntity.ok("Série compartilhada com sucesso com " + recipientEmail);
    }
}
