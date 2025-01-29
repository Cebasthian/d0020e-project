package com.example.consumer;

import com.example.json.transfer.StartTransferDTO;
import com.example.json.catalog.RequestCatalogDTO;
import com.example.json.catalog.RequestCatalogResponse;
import com.example.json.contract.ContractStatus;
import com.example.json.contract.NegotiateContractDTO;
import com.example.json.transfer.EndpointDataReference;
import com.example.json.transfer.TransferStatus;
import com.example.json.util.CreateResponse;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EdcConsumer {

    @Autowired
    private HttpRequester httpRequester;

    /**
     * Gets the catalog of all datasets (assets) from a specific connector.
     * @param targetConnector Address of the target connector, <b>http://localhost:11004/protocol<b/>
     * @return A catalog containing datasets.
     */
    public RequestCatalogResponse fetchCatalog(String targetConnector) {
        String url = "/v3/catalog/request";

        RequestCatalogDTO dto = new RequestCatalogDTO(targetConnector);

        return httpRequester.post(url, dto).body(RequestCatalogResponse.class);
    }

    /**
     * Starts a negotiation regarding an asset.
     * @param dto The body of the request.
     * @return EDC IdResponse containing negotiation id.
     */
    public CreateResponse negotiateContract(NegotiateContractDTO dto) {
        String url = "/v3/contractnegotiations";

        return httpRequester.post(url, dto).body(CreateResponse.class);
    }

    /**
     * Checks the status of an ongoing negotiation
     * @param negotiationId The id of the negotiation.
     * @return State object containing field <i>state</i> that equals FINALIZED when completed.
     * When finalized, also contains the contract id.
     */
    public ContractStatus checkNegotiationStatus(String negotiationId) {
        String url = "/v3/contractnegotiations/" + negotiationId;

        return httpRequester.get(url).body(ContractStatus.class);
    }

    /**
     * Starts the transfer of data based on a contract negotiation.
     * @param dto The body of the request.
     * @return EDC IdResponse containing transfer id.
     */
    public CreateResponse beginTransfer(StartTransferDTO dto) {
        String url = "/v3/transferprocesses";

        return httpRequester.post(url, dto).body(CreateResponse.class);
    }

    /**
     * Checks the status of an ongoing transfer process.
     * @param transferId The id of the transfer.
     * @return State object containing field <i>state</i> that equals STARTED when ready.
     */
    public TransferStatus checkTransferStatus(String transferId) {
        String url = "/v3/transferprocesses/" + transferId;

        return httpRequester.get(url).body(TransferStatus.class);
    }

    /**
     * Checks status of the transfer and if ready, retrieves the data from the connector.
     * @param transferId The id of the transfer.
     * @return The data attached to the asset requested.
     */
    public Object retrieveData(String transferId) {
        TransferStatus status = checkTransferStatus(transferId);

        if(!Objects.equals(status.state, "STARTED")) {
            throw new RuntimeException("Transfer process not done! Use #checkTransferStatus first.");
        }

        String checkUrl = "/v3/edrs/" + transferId + "/dataaddress";
        EndpointDataReference data = httpRequester.get(checkUrl).body(EndpointDataReference.class);

        if(data == null) {
            throw new RuntimeException("EndpointDataReference was null (for some reason)");
        }

        return httpRequester.retrieveData(data.endpoint, data.authorization).body(Object.class);
    }

}
