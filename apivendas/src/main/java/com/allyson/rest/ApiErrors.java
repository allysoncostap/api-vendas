package com.allyson.rest;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
    this.errors = Arrays.asList();
    }
}
