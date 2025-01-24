package com.example.json.transfer;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferStatus extends BaseDTO {
    @JsonProperty("@id")
    public String id;

    @JsonProperty("@type")
    public String type;

    public String state;
    public Long stateTimestamp;

    @JsonProperty("type")
    public String transferType;

    // TODO: complete this DTO
}

/*
{
  "@id": "982977a3-c479-4b41-89af-29bd4d1561c7",
  "@type": "TransferProcess",
  "state": "STARTED",
  "stateTimestamp": 1737724204627,
  "type": "CONSUMER",
  "callbackAddresses": [],
  "correlationId": "d808affa-6186-4851-b7c7-07c8088d7074",
  "assetId": "myAsset1",
  "contractId": "487b4216-3252-44c8-b4e2-5301d14a5861",
  "transferType": "HttpData-PULL",
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
    "edc": "https://w3id.org/edc/v0.0.1/ns/",
    "odrl": "http://www.w3.org/ns/odrl/2/"
  }
}

 */