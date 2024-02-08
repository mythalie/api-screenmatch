package br.com.project.screenmatch.controller;

import br.com.project.screenmatch.domain.dto.FavoriteSeriesCreateRequest;
import br.com.project.screenmatch.domain.dto.FavoriteSeriesResponse;
import br.com.project.screenmatch.domain.dto.FavoriteSeriesUpdateRequest;
import br.com.project.screenmatch.domain.dto.ResponseBase;
import br.com.project.screenmatch.domain.entity.FavoriteSeries;
import br.com.project.screenmatch.service.FavoriteSeriesService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(FavoriteSeriesController.class)
public class FavoriteSeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FavoriteSeriesService service;

    @ParameterizedTest
    @CsvSource({
            "1, 'Série 1'",
            "2, 'Série 2'"
    })
    void testGetFavoriteSeries(Long id, String title) throws Exception {
        List<FavoriteSeriesResponse> favoriteSeries = List.of(new FavoriteSeriesResponse(id, title));

        when(service.getAllFavoriteSeries()).thenReturn(favoriteSeries);

        mockMvc.perform(get("/favoriteSeries"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "Updated Title 1",
            "Updated Title 2"
    })
    public void testCreate(String title) throws Exception {
        FavoriteSeriesCreateRequest request = new FavoriteSeriesCreateRequest();
        request.setTitle(title);

        FavoriteSeries favoriteSeries = new FavoriteSeries();
        FavoriteSeriesResponse response = new FavoriteSeriesResponse(favoriteSeries);
        request.setTitle(title);

        when(service.create(request)).thenReturn(new ResponseBase<>(response));

        mockMvc.perform(MockMvcRequestBuilders.post("/favoriteSeries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"test\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "1, New Title 1",
            "2, New Title 2",
            "3, New Title 3"
    })
    void testEditTitle(Long id, String newTitle) throws Exception {
        // Cria um objeto FavoriteSeriesResponse simulado com o id e o novo título fornecidos
        FavoriteSeriesResponse mockedResponse = new FavoriteSeriesResponse(id, newTitle);

        /*
        any(Long.class) indica que você espera que o método editTitle seja chamado com qualquer valor de tipo Long.
        Isso significa que, quando o método editTitle é chamado durante o teste, não importa qual é o valor de id, o mockito aceitará e
        prosseguirá com a simulação da chamada do método.

        any(FavoriteSeriesUpdateRequest.class) é semelhante, mas para o parâmetro updateRequest, indicando que qualquer instância de
        FavoriteSeriesUpdateRequest será aceita quando o método editTitle for chamado.
         */
        when(service.editTitle(any(Long.class), any(FavoriteSeriesUpdateRequest.class))).thenReturn(new ResponseBase<>(mockedResponse));

        // Cria o corpo da requisição no formato JSON com o novo título
        String requestBody = "{\"title\":\"" + newTitle + "\"}";

        // Cria um builder de requisição simulado para o método PUT no endpoint /favoriteSeries/{id}
        MockHttpServletRequestBuilder requestBuilder = put("/favoriteSeries/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        // Executa a requisição simulada e verifica se a resposta está Ok (status 200)
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2"
    })
    void testDelete(Long id) throws Exception {
        FavoriteSeriesResponse removedSeries = new FavoriteSeriesResponse(id, "Removed Series");
        when(service.delete(id)).thenReturn(new ResponseBase<>(removedSeries));

        mockMvc.perform(MockMvcRequestBuilders.delete("/favoriteSeries/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
