package com.example.edcinterface.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperBaseDTO {
    @JsonProperty("@context")
    public Context context = new Context();

    public static class Context {
        @JsonProperty("@vocab")
        public String vocab = "https://w3id.org/edc/v0.0.1/ns/";
    }
}
