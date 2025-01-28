const TARGET_CONNECTOR = "http://localhost:12004/protocol"

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
        console.log("Body:", body)
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
        targetConnector: TARGET_CONNECTOR
    })
    const dataset = catalog["dcat:dataset"][0]
    const assetId = dataset["@id"]
    const policyId = dataset["odrl:hasPolicy"]["@id"]

    const negotiation = await post("/contract/negotiate", {
        targetConnector: TARGET_CONNECTOR,
        policy: {
            id: policyId,
            assigner: catalog["dspace:participantId"],
            targetAsset: assetId
        }
    })

    const status = await startPolling(async () => {
        return await get(`/contract/status/${negotiation["@id"]}`)
    }, (status) => status["state"] === "FINALIZED")

    const transfer = await post("/transfer/begin", {
        connectorId: status["counterPartyId"],
        counterPartyAddress: status["counterPartyAddress"],
        contractId: status["contractAgreementId"],
        assetId: assetId
    })

    await startPolling(async () => {
        return await get(`/transfer/status/${transfer["@id"]}`)
    }, (status) => status["state"] === "STARTED")

    const asset = await get(`/transfer/retrieve/${transfer["@id"]}`)

    console.log("\n\n==| Asset |==")
    console.log(JSON.stringify(asset, null, 2))

}

PullAsset()

function delay(ms) {
    return new Promise((resolve) => {
        console.log("\nSleeping for " + ms + " ms")
        setTimeout(() => {resolve()}, ms)
    })
}

async function startPolling(func, checkFunction) {
    let loop = true;
    let ret;
    while(loop) {
        const data = await func();
        if(checkFunction(data)) {
            loop = false;
            ret = data;
        } else {
            await delay(1000)
        }
    }
    return ret;   
}