package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.ResponseBase;
import br.com.project.screenmatch.domain.dto.ShareSerieCreateRequest;
import br.com.project.screenmatch.domain.dto.ShareSerieResponse;
import br.com.project.screenmatch.domain.entity.ShareSerie;
import br.com.project.screenmatch.service.ShareSerieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ShareSerieController.class)
public class ShareSerieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShareSerieService service;

    @Test
    void testGetShareSerie() throws Exception {
        List<ShareSerieResponse> shareSerieList = new ArrayList<>();
        ShareSerie shareSerie = new ShareSerie();
        shareSerieList.add(new ShareSerieResponse(shareSerie));

        // Estamos dizendo que quando o método getAllShareSerie() do serviço for chamado, ele deve retornar a lista shareSerieList que criamos anteriormente.
        when(service.getAllShareSerie()).thenReturn(shareSerieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/shareSeries") // Este método cria uma solicitação GET para o endpoint /shareSeries.
                        .accept(MediaType.APPLICATION_JSON)) // Aqui estamos definindo que aceitamos apenas respostas no formato JSON.
                .andExpect(MockMvcResultMatchers.status().isOk()); // Aqui estamos verificando se o status da resposta é 200 (OK).
    }

    @ParameterizedTest // Indica que o método é um teste parametrizado, que ele será executado várias vezes com diferentes conjuntos de parâmetros.
    @CsvSource({
            "1, test@example.com, Test message",
            "2, another@example.com, Another message"
    }) //  Esta anotação fornece os conjuntos de parâmetros para o teste parametrizado.
    void testCreateShareSerie(Long serieId, String recipientEmail, String message) throws Exception {
        ShareSerieCreateRequest request = new ShareSerieCreateRequest();
        request.setSerieId(serieId);
        request.setRecipientEmail(recipientEmail);
        request.setMessage(message);

        ShareSerie shareSerie = new ShareSerie();
        ShareSerieResponse response = new ShareSerieResponse(shareSerie);
        response.setId(1L);
        response.setRecipientEmail(recipientEmail);
        response.setMessage(message);

        when(service.create(request)).thenReturn(new ResponseBase<>(response));

        mockMvc.perform(MockMvcRequestBuilders.post("/shareSeries/share")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"serieId\": 1, \"recipientEmail\": \"test@example.com\", \"message\": \"Test message\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
