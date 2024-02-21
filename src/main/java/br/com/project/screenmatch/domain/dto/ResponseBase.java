package br.com.project.screenmatch.domain.dto;

import lombok.Getter;

@Getter
public class ResponseBase<T> {

    /*
    Essa classe encapsula uma resposta básica que pode ser retornada por um serviço ou método em uma aplicação.
    Ela fornece flexibilidade para incluir um objeto de resposta de qualquer tipo T, juntamente com uma mensagem opcional para fornecer contexto adicional
    sobre a resposta. Isso pode ser útil em situações onde métodos precisam retornar mais do que apenas o objeto de resposta, como por exemplo,
    incluir mensagens de sucesso, erro ou outras informações pertinentes.
     */

    private final String message;
    private final T object;

    public ResponseBase(T obj) {
        object = obj;
        message = null;
    }

    public ResponseBase(T obj, String msg) {
        object = obj;
        message = msg;
    }
}
