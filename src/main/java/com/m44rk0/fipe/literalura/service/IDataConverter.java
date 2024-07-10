package com.m44rk0.fipe.literalura.service;

public interface IDataConverter {
    <T> T convertData(String json, Class<T> classe);
}
