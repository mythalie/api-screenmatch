package br.com.project.screenmatch.configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumption {
    public String getData(String endereco) {
        //O HttpClient é a classe principal para realizar operações HTTP.
        HttpClient client = HttpClient.newHttpClient();

        //Um novo objeto HttpRequest é criado usando o HttpRequest.newBuilder().
        // O método uri é usado para especificar a URI (Uniform Resource Identifier) do recurso ao qual você deseja fazer a requisição.
        // O URI é fornecido como uma string endereco e convertido para uma URI usando URI.create(). O método build() finaliza a construção do pedido.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        //Aqui, a requisição é enviada usando o método send do HttpClient. A resposta é armazenada em um objeto HttpResponse<String>
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //O corpo da resposta é obtido usando o método body() do objeto HttpResponse.
        String json = response.body();

        //A string que representa o corpo da resposta é retornada pelo método.
        return json;
    }
}
