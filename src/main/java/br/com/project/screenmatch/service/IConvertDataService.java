package br.com.project.screenmatch.service;

public interface IConvertDataService {
    <T> T getData(String json, Class<T> tClass);
}
