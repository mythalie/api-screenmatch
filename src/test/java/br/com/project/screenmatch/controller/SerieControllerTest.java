package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.SerieResponse;
import br.com.project.screenmatch.model.Category;
import br.com.project.screenmatch.service.SerieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(SerieController.class)
public class SerieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SerieService service;

    @Test
    void getId() throws Exception {
        // Define um ID fictício para ser utilizado no teste.
        Long id = 1L;
        // Cria uma instância de SerieResponse com dados fictícios para simular a resposta do serviço.
        SerieResponse mockResponse = new SerieResponse(
                id,
                "Title",
                10,
                4.5,
                Category.DRAMA,
                "Actor1, Actor2",
                "poster.jpg",
                "Plot"
        );
        // Configura o comportamento do mock do serviço para retornar a instância mockResponse quando o método getSerieById é chamado com o ID fornecido.
        when(service.getSerieById(id)).thenReturn(Optional.of(mockResponse));

        // Simula uma requisição GET para o endpoint "/series/{id}" e verifica se o status retornado é OK (200).
        mockMvc.perform(get("/series/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Série 1', 10, 4.5, DRAMA, 'Ator 1', 'poster1.jpg', 'Plot 1'",
            "2, 'Série 2', 8, 4.4, ACAO, 'Ator 2', 'poster2.jpg', 'Plot 2'",
            "3, 'Série 3', 6, 4.3, ROMANCE, 'Ator 3', 'poster3.jpg', 'Plot 3'",
            "4, 'Série 4', 12, 4.2, CRIME, 'Ator 4', 'poster4.jpg', 'Plot 4'",
            "5, 'Série 5', 9, 4.1, COMEDIA, 'Ator 5', 'poster5.jpg', 'Plot 5'"
    })
    void getSeries(Long id, String title, Integer totalSeasons, Double rating, Category genre,
                   String actors, String poster, String plot) throws Exception {

        List<SerieResponse> series = List.of(new SerieResponse(id, title, totalSeasons, rating, genre, actors, poster, plot));

        // Configurando o mock de SerieService para retornar uma página contendo todas as séries fornecida como entrada para o teste.
        when(service.getAllSeries(Pageable.unpaged())).thenReturn(new PageImpl<>(series));

        // Simula uma requisição GET para o endpoint "/series" e verifica se o status retornado é OK (200).
        mockMvc.perform(get("/series"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Série 1', 10, 4.5, DRAMA, 'Ator 1', 'poster1.jpg', 'Plot 1'",
            "2, 'Série 2', 8, 4.4, ACAO, 'Ator 2', 'poster2.jpg', 'Plot 2'",
            "3, 'Série 3', 6, 4.3, ROMANCE, 'Ator 3', 'poster3.jpg', 'Plot 3'",
            "4, 'Série 4', 12, 4.2, CRIME, 'Ator 4', 'poster4.jpg', 'Plot 4'",
            "5, 'Série 5', 9, 4.1, COMEDIA, 'Ator 5', 'poster5.jpg', 'Plot 5'"
    })
    void testGetTop5Series(Long id, String title, Integer totalSeasons, Double rating, Category genre,
                           String actors, String poster, String plot) throws Exception {

        // Cria uma lista de SerieResponse contendo apenas a série específica fornecida como entrada para o teste.
        List<SerieResponse> top5Series = List.of(new SerieResponse(id, title, totalSeasons, rating, genre, actors, poster, plot));

        // Configura o mock de SerieService para retornar a lista de top 5 séries quando o método getTop5Series é chamado.
        when(service.getTop5Series()).thenReturn(top5Series);

        // Simula uma requisição GET para o endpoint "/series/top5" e verifica se o status retornado é OK (200).
        mockMvc.perform(get("/series/top5"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testInvalidId() throws Exception {
        Long id = 99L;
        // Configura o mock do serviço para retornar um Optional vazio para o ID fornecido.
        when(service.getSerieById(id)).thenReturn(Optional.empty());

        // Simula uma requisição GET para o endpoint "/series/{id}" com o ID fornecido.
        mockMvc.perform(get("/series/{id}", id))
                // Verifica se o status retornado é NOT_FOUND (404), pois o recurso não foi encontrado (Optional vazio).
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
