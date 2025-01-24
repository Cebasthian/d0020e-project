package org.example.recycler.controllers;

import com.example.provider.AssetManager;
import com.example.provider.ContractManager;
import com.example.json.asset.CreateAssetDTO;
import com.example.provider.PolicyManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.recycler.dto.NewAssetDTO;
import org.example.recycler.dto.NewContractDTO;
import org.example.recycler.dto.NewPolicyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "connector endpoints")
@RestController
@RequestMapping("/edc-provider")
@CrossOrigin
public class EdcProviderController {

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private PolicyManager policyManager;

    @Autowired
    private ContractManager contractManager;

    @PostMapping("/assets/create")
    public Object createAsset(@RequestBody NewAssetDTO body) {
        CreateAssetDTO.DataAddress dataAddress = new CreateAssetDTO.DataAddress();
        dataAddress.name = body.name;
        dataAddress.proxyPath = "false";
        dataAddress.baseUrl = body.baseUrl;

        return assetManager.createAsset(body.id, body.name, dataAddress);
    }

    @PostMapping("/policies/create")
    public Object createPolicy(@RequestBody NewPolicyDTO body) {
        return policyManager.createPolicy(body.id);
    }

    @PostMapping("/contracts/create")
    public Object createContract(@RequestBody NewContractDTO body) {
        return contractManager.createContract(body.id, body.accessPolicyId, body.contractPolicyId);
    }



}
