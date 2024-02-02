package br.com.project.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class QueryChatGPT {
    public static String getTranslation(String texto) {
        OpenAiService service = new OpenAiService("Cole aqui sua chave da OpenAI");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("Traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(requisicao);
        return response.getChoices().get(0).getText();
    }
}
