package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.*;
import br.com.project.screenmatch.service.ShareSerieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shareSeries")
@SecurityRequirement(name = "bearer-key")
public class ShareSerieController {

    @Autowired
    ShareSerieService service;

    @GetMapping
    public ResponseEntity<List<ShareSerieResponse>> getShareSerie() {
        List<ShareSerieResponse> allShareSerie = service.getAllShareSerie();
        return ResponseEntity.ok(allShareSerie);
    }

    @PostMapping("/share")
    public ResponseEntity create(@RequestBody @Valid ShareSerieCreateRequest createRequest) {
        ResponseBase<ShareSerieResponse> createReturn = service.create(createRequest);
        return ResponseEntity.ok(createReturn);
    }
}