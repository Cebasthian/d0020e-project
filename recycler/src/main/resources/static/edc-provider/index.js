const TARGET_MODULE = "http://localhost:8081/edc-provider"
const ASSET_URL = "http://localhost:8081/instructions/get-entries"

async function post(endpoint, body) {   
    const res = await fetch(TARGET_MODULE+endpoint, {
        method: "post",
        body: JSON.stringify(body),
        headers: {
            "Content-Type": "application/json"
        }
    })
    console.log(`
    ====| ${endpoint} |====
    Req Body: %o
    Status: ${res.status}
    Status Text: ${res.statusText}
    Json: %o
    
`, body, await res.json())
    // console.log(`====| ${endpoint} |====`)
    // console.log("Body:", body)
    // console.log("Status:", res.status)
    // console.log("Status Text:", res.statusText)
    // console.log("JSON:", await res.json())
}

async function createAsset(name, url) {   
    await post("/assets/create", {
        name: name,
        baseUrl: url
    })
}

async function createPolicy(id) {   
    await post("/policies/create", {
        id: id
    })
}

async function createContract(contractId, accessPolicyId, contractPolicyId) {
    await post("/contracts/create", {
        id: contractId,
        accessPolicyId: accessPolicyId,
        contractPolicyId: contractPolicyId
    })
}

document.getElementById("example").addEventListener("click", () => {
    document.getElementById("asset-name").value = "Instructions asset";
    document.getElementById("asset-url").value = "http://localhost:8081/instructions/get-entries";

    document.getElementById("policy-id").value = "myPolicy1";

    document.getElementById("contract-id").value = "myContract1";
    document.getElementById("usage-policy-id").value = "myPolicy1";
    document.getElementById("contract-policy-id").value = "myPolicy1";
})



document.getElementById("create-asset").addEventListener("click", async () => {
    const name = document.getElementById("asset-name");
    const url = document.getElementById("asset-url");
    await createAsset(name.value, url.value)
    name.value = ""
    url.value = ""
})

document.getElementById("create-policy").addEventListener("click", async () => {
    const id = document.getElementById("policy-id");
    await createPolicy(id.value)
    id.value = ""
})

document.getElementById("create-contract").addEventListener("click", async () => {
    const id = document.getElementById("contract-id");
    const policyId = document.getElementById("usage-policy-id");
    const contractPolicyId = document.getElementById("contract-policy-id");
    await createContract(id.value, policyId.value, contractPolicyId.value)
    id.value = ""
    policyId.value = ""
    contractPolicyId.value = ""
})