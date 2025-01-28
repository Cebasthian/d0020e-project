package com.example.json.contract;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NegotiateContractDTO extends BaseDTO {
    @JsonProperty("@type")
    public String type = "ContractRequest";

    public String counterPartyAddress;
    public String protocol = "dataspace-protocol-http";

    public Policy policy = new Policy();

    public static class Policy {
        @JsonProperty("@context")
        public String context = "http://www.w3.org/ns/odrl.jsonld";

        @JsonProperty("@type")
        public String type = "Offer";

        @JsonProperty("@id")
        public String id;

        public String assigner;

        /* TODO
        "permission": [],
        "prohibition": [],
        "obligation": [],
         */

        public String target;
    }

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
