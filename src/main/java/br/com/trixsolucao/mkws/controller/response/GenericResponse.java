/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trixsolucao.mkws.controller.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class GenericResponse<T> extends ModelResponse {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "@class")
    private T responseData;

    public GenericResponse() {
        super(0, "");
    }

    public GenericResponse(int returnCode, String returnMessage, T responseData) {
        super(returnCode, returnMessage);
        this.responseData = responseData;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

}

