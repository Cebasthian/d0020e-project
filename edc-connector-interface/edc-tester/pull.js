async function PullAsset() {
    async function post(endpoint, body) {   
        const res = await fetch("http://localhost:8083/edc-consumer"+endpoint, {
            method: "post",
            body: JSON.stringify(body),
            headers: {
                "Content-Type": "application/json"
            }
        })
        console.log("")
        console.log(`====| ${endpoint} |====`)
        console.log("Status:", res.status)
        console.log("Status Text:", res.statusText)
        const json = await res.json();
        console.log("JSON:", json)
        return json;
    }

    async function get(endpoint) {
        const res = await fetch("http://localhost:8083/edc-consumer"+endpoint, {
            method: "get",
            headers: {
                "Content-Type": "application/json"
            }
        })
        console.log("")
        console.log(`====| ${endpoint} |====`)
        console.log("Status:", res.status)
        console.log("Status Text:", res.statusText)
        const json = await res.json();
        console.log("JSON:", json)
        return json;
    }


    const catalog = await post("/catalog/get", {
        targetConnector: "http://localhost:19194/protocol"
    })
    const dataset = catalog["dcat:dataset"][0]
    const assetId = dataset["@id"]
    const policyId = dataset["odrl:hasPolicy"]["@id"]

    const negotiation = await post("/contract/negotiate", {
        targetConnector: "http://localhost:19194/protocol",
        policy: {
            id: policyId,
            assigner: catalog["dspace:participantId"],
            targetAsset: assetId
        }
    })

    await delay(5000)

    const status = await get(`/contract/status/${negotiation["@id"]}`)
    const agreementId = status["contractAgreementId"]

    const transfer = await post("/transfer/begin", {
        connectorId: status["counterPartyId"],
        counterPartyAddress: status["counterPartyAddress"],
        contractId: agreementId,
        assetId: assetId
    })

    await delay(1000);

    const asset = await get(`/transfer/retrieve/${transfer["@id"]}`)

    console.log("\n\n=====================")
    console.log(asset)
    console.log("=====================")

}

PullAsset()

function delay(ms) {
    return new Promise((resolve) => {
        console.log("\nSleeping for " + ms + " ms")
        setTimeout(() => {resolve()}, ms)
    })
}