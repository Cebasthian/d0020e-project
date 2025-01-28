package com.example.json.policy;

import com.example.json.BaseDTO;
import com.example.json.contract.CreateContractDTO;
import com.example.json.odrl.Policy;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePolicyDTO extends BaseDTO {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type = "PolicyDefinition";

    public Policy policy = new Policy();

    public CreatePolicyDTO() {
        policy.type = "Set";
    }

//    // https://eclipse-edc.github.io/documentation/for-adopters/control-plane/#policies-and-policy-definitions
//    public static class Policy {
//
//
//        public List<Object> permission;
//        public List<Object> prohibition;
//        public List<Object> obligation;
//
//
//    }
//
//
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