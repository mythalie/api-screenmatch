package br.com.project.screenmatch.service;

import br.com.project.screenmatch.domain.dto.*;
import br.com.project.screenmatch.domain.entity.Serie;
import br.com.project.screenmatch.domain.entity.ShareSerie;
import br.com.project.screenmatch.exception.SeriesNotFoundException;
import br.com.project.screenmatch.repository.SerieRepository;
import br.com.project.screenmatch.repository.ShareSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShareSerieService {

    @Autowired
    private ShareSerieRepository repository;

    @Autowired
    private SerieRepository serieRepository;

    public List<ShareSerieResponse> getAllShareSerie() {
        return convertData(repository.findAll());
    }
    public ResponseBase<ShareSerieResponse> create(ShareSerieCreateRequest createRequest) {
        ShareSerie shareSerie = new ShareSerie();
        shareSerie.setRecipientEmail(createRequest.getRecipientEmail());
        shareSerie.setMessage(createRequest.getMessage());

        Long serieId = createRequest.getSerieId();
        Serie serie = serieRepository.findById(serieId)
                .orElseThrow(SeriesNotFoundException::new);

        shareSerie.setSerie(serie);

        ShareSerie shareSerieSave = repository.save(shareSerie);
        return new ResponseBase<>(new ShareSerieResponse(shareSerieSave));
    }

    private List<ShareSerieResponse> convertData(List<ShareSerie> shareSerie) {
        return shareSerie.stream()
                .map(this::convertToShareSerieResponse)
                .collect(Collectors.toList());
    }

    private ShareSerieResponse convertToShareSerieResponse(ShareSerie shareSerie) {
        return new ShareSerieResponse(shareSerie);
    }
}
