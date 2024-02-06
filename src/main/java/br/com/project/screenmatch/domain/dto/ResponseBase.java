package br.com.project.screenmatch.domain.dto;

import lombok.Getter;

@Getter
public class ResponseBase<T> {
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
