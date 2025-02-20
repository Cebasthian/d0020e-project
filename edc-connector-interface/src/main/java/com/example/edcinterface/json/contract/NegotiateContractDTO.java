package com.example.edcinterface.json.contract;

import com.example.edcinterface.json.BaseDTO;
import com.example.edcinterface.json.SuperBaseDTO;
import com.example.edcinterface.json.odrl.Policy;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NegotiateContractDTO extends SuperBaseDTO {
    @JsonProperty("@type")
    public String type = "ContractRequest";

    public String counterPartyAddress;
    public String protocol = "dataspace-protocol-http";

    public Policy.Offer policy = new Policy.Offer();

    /* TODO
    "callbackAddresses": [
        {
          "transactional": false,
          "uri": "http://callback/url",
          "events": [
            "contract.negotiation",
            "transfer.process"
          ],
          "authKey": "auth-key",
          "authCodeId": "auth-code-id"
        }
      ]
     */


}

/*
{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "@type": "ContractRequest",
  "counterPartyAddress": "http://localhost:19194/protocol",
  "protocol": "dataspace-protocol-http",
  "policy": {
    "@context": "http://www.w3.org/ns/odrl.jsonld",
    "@id": "hej-samuel",
    "@type": "Offer",
    "assigner": "provider",
    "target": "assetId"
  }
}

 */
