package com.example.edcinterface.controller;

import com.example.edcinterface.consumer.EdcConsumer;
import com.example.edcinterface.json.BaseDTO;
import com.example.edcinterface.json.catalog.RequestCatalogResponse;
import com.example.edcinterface.json.contract.ContractStatus;
import com.example.edcinterface.json.contract.NegotiateContractDTO;
import com.example.edcinterface.json.odrl.Policy;
import com.example.edcinterface.json.odrl.Rule;
import com.example.edcinterface.json.transfer.StartTransferDTO;
import com.example.edcinterface.json.transfer.TransferStatus;
import com.example.edcinterface.json.util.CreateResponse;
import com.example.edcinterface.json.util.EmptyQuerySpec;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RequestMapping("/edc-consumer")
@Tag(name = "Edc Consumer", description = "Endpoints related to consume assets in an edc dataspace.")
public class EdcConsumerController {

    @Autowired
    private EdcConsumer edcConsumer;


    @Operation(summary = "Get connectors", description = "Fetch the metadata of all active connectors. Note that these are hardcoded for demonstration purposes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found connectors"),
    })
    @GetMapping("/connectors")
    public Object getConnectors() {
        return edcConsumer.getConnectors();
    }


    @Operation(summary = "Get catalog", description = "Fetch catalog from a target connector.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found catalog"),
    })
    @PostMapping("/catalog/get")
//    public Object requestCatalog(@RequestBody GetCatalogDTO body) {
    public RequestCatalogResponse requestCatalog(@RequestBody GetCatalogDTO body) {
        return edcConsumer.fetchCatalog(body.targetConnector);
    }


    @Operation(summary = "Negotiate contract", description = "Begin a contract negotiation related to an asset.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contract initialized"),
    })
    @PostMapping("/contract/negotiate")
    public CreateResponse negotiateContract(@RequestBody NegotiateDTO body) {
        NegotiateContractDTO dto = new NegotiateContractDTO();

        dto.counterPartyAddress = body.targetConnector;
        dto.policy.policyId = body.policy.id;
        dto.policy.targetConnectorId = body.policy.assigner;
        dto.policy.assetId = body.policy.targetAsset;

        return edcConsumer.negotiateContract(dto);
    }

    @PostMapping("/contract/negotiate/v2")
    public CreateResponse negotiateContractV2(@RequestBody NegotiateV2DTO body) {
        NegotiateContractDTO dto = new NegotiateContractDTO();

        dto.counterPartyAddress = body.targetConnector;
        dto.policy.policyId = body.id;
        dto.policy.targetConnectorId = body.assigner;
        dto.policy.assetId = body.targetAsset;
        dto.policy.permission = body.permission;

        return edcConsumer.negotiateContract(dto);
    }

    @GetMapping("/contract/negotiations")
    public Object getNegotiations() {
        return edcConsumer.getNegotiations(new EmptyQuerySpec());
    }


    @Operation(summary = "Negotiation status", description = "Check the status on a contract negotiation.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Negotiation found"),
    })
    @GetMapping("/contract/status/{negotiationId}")
    public ContractStatus checkStatus(@PathVariable String negotiationId) {
        return edcConsumer.checkNegotiationStatus(negotiationId);
    }

    @GetMapping("/contract/agreement/{negotiationId}")
    public Object getAgreement(@PathVariable String negotiationId) {
        return edcConsumer.getAgreement(negotiationId);
    }


    @Operation(summary = "Begin data transfer", description = "Start a consumer pull data transfer.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer initialized"),
    })
    @PostMapping("/transfer/begin")
    public CreateResponse beginTransfer(@RequestBody TransferDTO body) {
        StartTransferDTO dto = new StartTransferDTO();
        dto.connectorId = body.connectorId;
        dto.counterPartyAddress = body.counterPartyAddress;
        dto.contractId = body.contractId;
//        dto.assetId = body.assetId;
        return edcConsumer.beginTransfer(dto);
    }

    @GetMapping("/transfer")
    public Object getTransfers() {
        return edcConsumer.getTransfers(new EmptyQuerySpec());
    }


    @Operation(summary = "Transfer status", description = "Check the status on a transfer process.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer process found"),
    })
    @GetMapping("/transfer/status/{transferId}")
    public TransferStatus checkTransferStatus(@PathVariable String transferId) {
        return edcConsumer.checkTransferStatus(transferId);
    }


    @Operation(summary = "Retrieve asset", description = "Fetch the actual data attached to the asset.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Asset retrieved"),
    })
    @GetMapping("/transfer/retrieve/{transferId}")
    public Object retrieveAsset(@PathVariable String transferId) {

        return edcConsumer.retrieveData(transferId);
    }


    public static class GetCatalogDTO {
        public String targetConnector;
    }

    public static class NegotiateDTO {
        public String targetConnector;
        public Policy policy;
        public static class Policy {
            public String id;
            public String assigner;
            public String targetAsset;
        }
    }

    public static class NegotiateV2DTO {
        public String targetConnector;
        public String id;
        public String assigner;
        public String targetAsset;

        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        public List<Object> permission;
    }

    public static class TransferDTO {
        public String connectorId;
        public String counterPartyAddress;
        public String contractId;
    }
}
