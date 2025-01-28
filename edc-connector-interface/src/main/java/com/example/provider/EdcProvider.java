package com.example.provider;

import com.example.json.asset.CreateAssetDTO;
import com.example.json.contract.CreateContractDTO;
import com.example.json.policy.CreatePolicyDTO;
import com.example.json.util.CreateResponse;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class EdcProvider {

    @Autowired
    private HttpRequester httpRequester;


    public CreateResponse createAsset(String id, String name, CreateAssetDTO.DataAddress dataAddress) {
        String url = "/v3/assets";

        CreateAssetDTO dto = new CreateAssetDTO();
        // TODO: instead of providing id, we generate it using UUID-v4
        dto.id = id;
        dto.properties.put("name", name);
        dto.properties.put("contenttype", "application/json");
        dto.dataAddress = dataAddress;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }

    // TODO: Add so we can modify the request. For example permission, prohibitions, obligations.

    /**
     * @deprecated This will need to be updated, method signature will be different once fully implemented.
     * @param id
     * @return
     */
    @Deprecated
    public CreateResponse createPolicy(String id) {
        String url = "/v3/policydefinitions";

        CreatePolicyDTO dto = new CreatePolicyDTO();
        dto.id = id;

        System.out.println(id);

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }

    // TODO: Add so we can set assetsSelector (maybe with overloaded method because an empty assetsSelector means it applies to all assets)
    /**
     * @deprecated Method signature will be updated once fully implemented.
     * @param id
     * @param accessPolicyId
     * @param contractPolicyId
     * @return
     */
    @Deprecated
    public CreateResponse createContract(String id, String accessPolicyId, String contractPolicyId) {
        String url = "/v3/contractdefinitions";

        CreateContractDTO dto = new CreateContractDTO();
        dto.id = id;
        dto.accessPolicyId = accessPolicyId;
        dto.contractPolicyId = contractPolicyId;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(CreateResponse.class);
    }
}
