package com.example.dto.catalog;

import com.example.dto.BaseDTO;
import com.example.dto.util.QuerySpec;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestCatalogDTO extends BaseDTO {


    @JsonProperty("@type")
    public String type = "CatalogRequest";

    public String protocol = "dataspace-protocol-http";
    public String counterPartyAddress;
    public String counterPartyId;
    public List<String> additionalScopes;
    public QuerySpec querySpec;

    public RequestCatalogDTO(String counterPartyAddress) {
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