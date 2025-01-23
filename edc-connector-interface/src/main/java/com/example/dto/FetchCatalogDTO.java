package com.example.dto;

public class FetchCatalogDTO extends BaseDTO {


    public String protocol = "dataspace-protocol-http";
    public String counterPartyAddress;

    public FetchCatalogDTO(String counterPartyAddress) {
        this.counterPartyAddress = counterPartyAddress;
    }

}


/*
{
  "@context": {
    "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
  },
  "counterPartyAddress": "http://localhost:19194/protocol",
  "protocol": "dataspace-protocol-http"
}

 */