package com.example.json.transfer;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StartTransferDTO extends BaseDTO {
    @JsonProperty("@type")
    public String type = "TransferRequestDto";

    public String connectorId;
    public String counterPartyAddress;
    public String contractId;
    public String protocol = "dataspace-protocol-http";
    public String transferType = "HttpData-PULL";
}

/*
{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@type": "TransferRequestDto",
  "connectorId": "provider",
  "counterPartyAddress": "http://localhost:19194/protocol",
  "contractId": "908c54ac-e512-453d-ad9a-a6d4c8f34fb3",
  "assetId": "assetId",
  "protocol": "dataspace-protocol-http",
  "transferType": "HttpData-PULL"
}

 */
