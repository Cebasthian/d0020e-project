package com.example.edcinterface.provider;

import com.example.edcinterface.json.BaseDTO;
import com.example.edcinterface.json.asset.CreateAssetDTO;
import com.example.edcinterface.json.contract.CreateContractDTO;
import com.example.edcinterface.json.odrl.Policy;
import com.example.edcinterface.json.policy.CreatePolicyDTO;
import com.example.edcinterface.json.util.CreateResponse;
import com.example.edcinterface.json.util.QuerySpec;
import com.example.edcinterface.util.HttpRequester;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class EdcProvider {

    @Autowired
    private HttpRequester httpRequester;


    /**
     * Creates a new asset and attaches data to it. Note: Fetching the data is retroactive which means
     * that it is possible to attach data that doesn't exist yet.
     * @param name Name of the asset.
     * @param dataAddress A pointer to where the EDC connector can fetch the data attached to the asset.
     * @return EDC IdResponse json object
     */
    public CreateResponse createAsset(String name, CreateAssetDTO.DataAddress dataAddress) {
        String url = "/v3/assets";

        CreateAssetDTO dto = new CreateAssetDTO();
        dto.id = UUID.randomUUID().toString();
        dto.properties.put("name", name);
        dto.properties.put("contenttype", "application/json");
        dto.dataAddress = dataAddress;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }

    public Object getAssets(BaseDTO querySpec) {
        String url = "/v3/assets/request";

        return httpRequester.post(url, querySpec).body(Object.class);
    }

    /**
     * Creates a new policy on the EDC connector.
     * @param id The id associated with the policy. Used when creating contracts.
     * @param policy Set to null to use default values.
     * @return EDC IdResponse json object
     */
    public CreateResponse createPolicy(String id, @Nullable Policy policy) {
        String url = "/v3/policydefinitions";

        CreatePolicyDTO dto = new CreatePolicyDTO();
        dto.id = id;

        if(policy != null) {
            dto.policy = policy;
        }

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }

    /**
     * Creates a new contract based on policies.
     * @param id Contract id.
     * @param accessPolicyId The internal policy that the connector enforces.
     * @param contractPolicyId The public policy that other connectors need to comply with.
     * @param assetSelectors List of selectors for the assets that this contract applies to.
     * @return EDC IdResponse json object.
     */
    public CreateResponse createContract(String id, String accessPolicyId, String contractPolicyId, List<CreateContractDTO.AssetSelector> assetSelectors) {
        String url = "/v3/contractdefinitions";

        CreateContractDTO dto = new CreateContractDTO();
        dto.id = id;
        dto.accessPolicyId = accessPolicyId;
        dto.contractPolicyId = contractPolicyId;
        dto.assetsSelector = assetSelectors;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }
}
