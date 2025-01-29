package org.example.recycler.controllers;

import com.example.json.asset.CreateAssetDTO;
import com.example.json.odrl.Policy;
import com.example.json.util.CreateResponse;
import com.example.provider.EdcProvider;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Tag(name = "connector endpoints")
@RestController
@RequestMapping("/edc-provider")
@CrossOrigin
public class EdcProviderController {

    @Autowired
    private EdcProvider edcProvider;

    @PostMapping("/assets/create")
    public CreateResponse createAsset(@RequestBody NewAssetDTO body) {
        CreateAssetDTO.DataAddress dataAddress = new CreateAssetDTO.DataAddress();
        dataAddress.name = body.name;
        dataAddress.proxyPath = "false";
        dataAddress.baseUrl = body.baseUrl;

        return edcProvider.createAsset(body.name, dataAddress);
    }

    @PostMapping("/policies/create")
    public CreateResponse createPolicy(@RequestBody NewPolicyDTO body) {
        return edcProvider.createPolicy(body.id, body.policy);
    }

    @PostMapping("/contracts/create")
    public CreateResponse createContract(@RequestBody NewContractDTO body) {
        return edcProvider.createContract(body.id, body.accessPolicyId, body.contractPolicyId, Collections.emptyList());
    }

    @Hidden
    public static class NewAssetDTO {
        public String name;
        public String baseUrl;
    }

    @Hidden
    public static class NewContractDTO {
        public String id;
        public String accessPolicyId;
        public String contractPolicyId;
    }

    @Hidden
    public static class NewPolicyDTO {
        public String id;
        public Policy policy;
    }


}
