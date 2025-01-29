package com.example.json.odrl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


// https://eclipse-edc.github.io/documentation/for-adopters/control-plane/#policies-and-policy-definitions
public class Policy {

    @JsonProperty("@context")
    public String context = "http://www.w3.org/ns/odrl.jsonld";

    @JsonProperty("@type")
    public String type;

    @JsonProperty("@id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;

    public List<Rule> permission = new ArrayList<>();
    public List<Rule> prohibitions = new ArrayList<>();
    public List<Rule> obligation = new ArrayList<>();

    public static class Offer extends Policy {
        @JsonProperty("@type")
        public String type = "Offer";

        @JsonProperty("@id")
        public String policyId;

        @JsonProperty("assigner")
        public String targetConnectorId;

        @JsonProperty("target")
        public String assetId;
    }
}
