package com.example.json.odrl;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Rule> permission;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Rule> prohibitions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Rule> obligation;

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
