package com.example.consumer;

import com.example.json.BaseDTO;
import com.example.json.BaseResponse;
import com.example.json.asset.StartTransferDTO;
import com.example.json.catalog.RequestCatalogDTO;
import com.example.json.catalog.RequestCatalogResponse;
import com.example.json.contract.NegotiateContractDTO;
import com.example.json.transfer.EndpointDataReference;
import com.example.json.transfer.TransferStatus;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
public class EdcConsumer {

    @Autowired
    private HttpRequester httpRequester;

    public RequestCatalogResponse fetchCatalog(String targetConnector) {
        String url = "/v3/catalog/request";

        RequestCatalogDTO dto = new RequestCatalogDTO(targetConnector);

        return httpRequester.post(url, dto).body(RequestCatalogResponse.class);
    }

    /**
     * @deprecated Will probably change return value to something more explicit.
     * @return
     */
    @Deprecated
    public Object negotiateContract(NegotiateContractDTO dto) {
        String url = "/v3/contractnegotiations";

        return httpRequester.post(url, dto).body(Object.class);
    }

    /**
     * @deprecated Will probably change return value to something more explicit.
     * @return
     */
    @Deprecated
    public Object checkNegotiationStatus(String uuid) {
        String url = "/v3/contractnegotiations/" + uuid;

        return httpRequester.get(url).body(Object.class);
    }

    /**
     * @deprecated Will probably change return value to something more explicit.
     * @return
     */
    @Deprecated
    public Object beginTransfer(StartTransferDTO dto) {
        String url = "/v3/transferprocesses";

        return httpRequester.post(url, dto).body(Object.class);
    }

    /**
     * @deprecated Will probably change return value to something more explicit.
     * @return
     */
    @Deprecated
    public TransferStatus checkTransferStatus(String uuid) {
        String url = "/v3/transferprocesses/" + uuid;

        return httpRequester.get(url).body(TransferStatus.class);
    }

    public Object retrieveData(String transferId) {
        TransferStatus status = checkTransferStatus(transferId);

        if(!Objects.equals(status.state, "STARTED")) {
            return "[EdcConsumer#retrieveData] Status is not STARTED";
        }

        String checkUrl = "/v3/edrs/" + transferId + "/dataaddress";
        EndpointDataReference data = httpRequester.get(checkUrl).body(EndpointDataReference.class);

        if(data == null) {
            return "[EdcConsumer#retrieveData] Data was null";
        }

        return httpRequester.retrieveData(data.endpoint, data.authorization).body(Object.class);
    }

}
