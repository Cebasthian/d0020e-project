package com.example.json.contract;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreateContractDTO extends BaseDTO {

    @JsonProperty("@id")
    public String id;

    public String accessPolicyId;
    public String contractPolicyId;
    public List<Object> assetsSelector = new ArrayList<>();
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