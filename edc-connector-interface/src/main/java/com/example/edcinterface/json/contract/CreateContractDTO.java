package com.example.edcinterface.json.contract;

import com.example.edcinterface.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreateContractDTO extends BaseDTO {

    @JsonProperty("@id")
    public String id;

    public String accessPolicyId;
    public String contractPolicyId;
    public List<AssetSelector> assetsSelector = new ArrayList<>();

    // https://eclipse-edc.github.io/documentation/for-adopters/control-plane/#contract-definitions
    public static class AssetSelector {
        @JsonProperty("@type")
        public String type = "https://w3id.org/edc/v0.0.1/ns/Criterion";

        public String operandLeft;
        public String operator;
        public Object operandRight;
    }
}

/*
{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@id": "1",
  "accessPolicyId": "aPolicy",
  "contractPolicyId": "aPolicy",
  "assetsSelector": []
}

 */