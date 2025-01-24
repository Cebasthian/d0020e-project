package com.example.json.policy;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreatePolicyDTO extends BaseDTO {
    @JsonProperty("@id")
    public String id;

    public Policy policy = new Policy();

    public static class Policy {
        @JsonProperty("@context")
        public String context = "http://www.w3.org/ns/odrl.jsonld";

        @JsonProperty("@type")
        public String type = "Set";

        public List<Object> permission;
        public List<Object> prohibition;
        public List<Object> obligation;

    }
}

/*
{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
    "odrl": "http://www.w3.org/ns/odrl/2/"
  },
  "@id": "aPolicy",
  "policy": {
    "@context": "http://www.w3.org/ns/odrl.jsonld",
    "@type": "Set",
    "permission": [],
    "prohibition": [],
    "obligation": []
  }
}

 */