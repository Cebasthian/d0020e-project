package com.example.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse extends BaseDTO{

    @JsonProperty("@id")
    public String id;

    public Integer createdAt;

}
