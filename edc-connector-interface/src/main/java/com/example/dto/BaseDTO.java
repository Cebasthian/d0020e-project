package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDTO {

    @JsonProperty("@context")
    public Context context = new Context();

    class Context {
        @JsonProperty("@vocab")
        public String vocab = "https://w3id.org/edc/v0.0.1/ns/";
    }
}

/*

{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
 */