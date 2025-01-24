package com.example.provider;

import com.example.json.contract.CreateContractDTO;
import com.example.util.HttpRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ContractManager {
    @Autowired
    private HttpRequester httpRequester;

    // TODO: Add so we can set assetsSelector (maybe with overloaded method because an empty assetsSelector means it applies to all assets)
    /**
     * @deprecated Method signature will be updated once fully implemented.
     * @param id
     * @param accessPolicyId
     * @param contractPolicyId
     * @return
     */
    @Deprecated
    public Object createContract(String id, String accessPolicyId, String contractPolicyId) {
        String url = "/v3/contractdefinitions";

        CreateContractDTO dto = new CreateContractDTO();
        dto.id = id;
        dto.accessPolicyId = accessPolicyId;
        dto.contractPolicyId = contractPolicyId;

        RestClient.ResponseSpec res = httpRequester.post(url, dto);
        return res.body(Object.class);
    }
}

