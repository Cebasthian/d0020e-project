async function SetupAsset() {
    async function post(endpoint, body) {   
        const res = await fetch("http://localhost:8081/edc-provider"+endpoint, {
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
        console.log("JSON:", await res.json())
    }
    
    await post("/assets/create", {
        name: "All instructions",
        id: "myAsset1",
        baseUrl: "http://localhost:8081/instructions/get-entries"
    })
    
    await post("/policies/create", {
        id: "myPolicy1"
    })
    
    await post("/contracts/create", {
        id: "myContract1",
        accessPolicyId: "myPolicy1",
        contractPolicyId: "myPolicy1"
    })
}

SetupAsset()
