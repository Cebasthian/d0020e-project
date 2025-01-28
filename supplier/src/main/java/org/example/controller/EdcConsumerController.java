package org.example.controller;

import com.example.consumer.EdcConsumer;
import com.example.json.transfer.StartTransferDTO;
import com.example.json.catalog.RequestCatalogResponse;
import com.example.json.contract.ContractStatus;
import com.example.json.contract.NegotiateContractDTO;
import com.example.json.transfer.TransferStatus;
import com.example.json.util.CreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/edc-consumer")
public class EdcConsumerController {

    @Autowired
    private EdcConsumer edcConsumer;

    @PostMapping("/catalog/get")
    public RequestCatalogResponse requestCatalog(@RequestBody GetCatalogDTO body) {
        return edcConsumer.fetchCatalog(body.targetConnector);
    }

    @PostMapping("/contract/negotiate")
    public CreateResponse negotiateContract(@RequestBody NegotiateDTO body) {
        NegotiateContractDTO dto = new NegotiateContractDTO();
        dto.counterPartyAddress = body.targetConnector;
        dto.policy.id = body.policy.id;
        dto.policy.assigner = body.policy.assigner;
        dto.policy.target = body.policy.targetAsset;

//        return dto;
        return edcConsumer.negotiateContract(dto);
    }

    @GetMapping("/contract/status/{negotiationId}")
    public ContractStatus checkStatus(@PathVariable String negotiationId) {
        return edcConsumer.checkNegotiationStatus(negotiationId);
    }

    @PostMapping("/transfer/begin")
    public CreateResponse beginTransfer(@RequestBody TransferDTO body) {
        StartTransferDTO dto = new StartTransferDTO();
        dto.connectorId = body.connectorId;
        dto.counterPartyAddress = body.counterPartyAddress;
        dto.contractId = body.contractId;
//        dto.assetId = body.assetId;
        return edcConsumer.beginTransfer(dto);
    }

    @GetMapping("/transfer/status/{transferId}")
    public TransferStatus checkTransferStatus(@PathVariable String transferId) {
        return edcConsumer.checkTransferStatus(transferId);
    }

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

    public static class TransferDTO {
        public String connectorId;
        public String counterPartyAddress;
        public String contractId;
    }
}
