package com.example.json.contract;

import com.example.json.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ContractStatus extends BaseDTO {
    @JsonProperty("@type")
    public String type;

    @JsonProperty("@id")
    public String id;

    @JsonProperty("type")
    public String connectorType;

    public String protocol;
    public String state;

    public String counterPartyId;
    public String counterPartyAddress;

    public String contractAgreementId;

    public List<Object> callbackAddresses;
    public Long createdAt;
}
