const TARGET_MODULE = "/edc-consumer"

async function post(endpoint, body) {   
    const res = await fetch(TARGET_MODULE+endpoint, {
        method: "post",
        body: JSON.stringify(body),
        headers: {
            "Content-Type": "application/json"
        }
    })
    const json = await res.json();
    console.log(`
    ====| POST ${endpoint} |====
    Req Body: %o
    Status: ${res.status}
    Status Text: ${res.statusText}
    Json: %o
    
`, body, json)
    return json;
}

async function get(endpoint) {   
    const res = await fetch(TARGET_MODULE+endpoint, {
        method: "get",
        headers: {
            "Content-Type": "application/json"
        }
    })
    const json = await res.json();
    console.log(`
    ====| GET ${endpoint} |====
    Status: ${res.status}
    Status Text: ${res.statusText}
    Json: %o
    
`, json)
    return json;
}

/* CONNECTOR METADATA */

async function getMetadata() {
    await get("/connectors")
}

document.getElementById("get-metadata").addEventListener("click", async () => {
    await getMetadata()
})





/* CATALOG */

async function getCatalog(target) {
    const catalog = await post("/catalog/get", {
        targetConnector: target
    })
    const dataset = catalog["dcat:dataset"][0]
    const assigner = catalog["dspace:participantId"]
    const assetId = dataset["@id"]
    const policyId = dataset["odrl:hasPolicy"]["@id"]
    console.log(`
Data:
    PolicyID: ${policyId}
    ConnectorID: ${assigner}
    AssetID: ${assetId}

`)
}

document.getElementById("get-catalog").addEventListener("click", async () => {
    const target = document.getElementById("target-connector-address").value
    await getCatalog(target)
})










/* NEGOTIATE CONTRACT */

async function negotiate(targetConnector, policyId, assigner, targetAsset) {
    const negotiation = await post("/contract/negotiate", {
        targetConnector: targetConnector,
        policy: {
            id: policyId,
            assigner: assigner,
            targetAsset: targetAsset
        }
    })

    const id = negotiation["@id"]
    console.log(`
Data:
    NegotiationID: ${id}

`)
}

document.getElementById("negotiate").addEventListener("click", async () => {
    const target = document.getElementById("target-connector-address").value
    const policyId = document.getElementById("negotiate-policy-id")
    const connectorId = document.getElementById("negotiate-target-id")
    const assetId = document.getElementById("negotiate-asset-id")
    await negotiate(target, policyId.value, connectorId.value, assetId.value)
    policyId.value = ""
    connectorId.value = ""
    assetId.value = ""
})

async function checkNegotiationStatus(id) {
    const data = await get(`/contract/status/${id}`)
    console.log(`
Data:
    State: ${data["state"]}
    ConnectorId: ${data["counterPartyId"]}
    CounterPartyAddress: ${data["counterPartyAddress"]}
    AgreementID: ${data["contractAgreementId"]}

`)
}

document.getElementById("check-negotiation-status").addEventListener("click", async () => {
    const id = document.getElementById("negotation-id").value;
    await checkNegotiationStatus(id)
})







/* TRANSFER PROCESS */

async function beginTransfer(connectorId, counterPartyAddress, contractId) {
    const transfer = await post("/transfer/begin", {
        connectorId: connectorId,
        counterPartyAddress: counterPartyAddress,
        contractId: contractId
    })
    console.log(`
Data:
    TransferID: ${transfer["@id"]}

`)
}

document.getElementById("start-transfer").addEventListener("click", async () => {
    const connectorId = document.getElementById("transfer-connector-id")
    const address = document.getElementById("transfer-connector-address")
    const agreementId = document.getElementById("transfer-agreement-id")
    await beginTransfer(connectorId.value, address.value, agreementId.value)
    connectorId.value = ""
    address.value = ""
    agreementId.value = ""
})

async function checkTransferStatus(transferId) {
    const data = await get(`/transfer/status/${transferId}`)
    console.log(`
Data:
    State: ${data["state"]}

`)
}

document.getElementById("check-transfer-status").addEventListener("click", async () => {
    const transferId = document.getElementById("transfer-id").value;
    await checkTransferStatus(transferId);
})

async function retrieveData(transferId) {
    const asset = await get(`/transfer/retrieve/${transferId}`)
    console.log(`
    ==| Asset |==
    Data: %o

`, asset)
}

document.getElementById("retrieve-data").addEventListener("click", async () => {
    const transferId = document.getElementById("transfer-id").value;
    await retrieveData(transferId);
})