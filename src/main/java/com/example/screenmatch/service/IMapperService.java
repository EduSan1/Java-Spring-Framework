package com.example.screenmatch.service;

public interface IMapperService {

    <T> T getData(String json, Class<T> classType);
}
