import { EdcAgreeement, EdcCatalog, EdcMetadata, EdcNegotiation, EdcTransfer } from "../types/edc";
import { OdrlConstraint, OdrlHasPolicy } from "../types/odrl";
import { http } from "./http";
import { jsonLd } from "./json_ld";

/*  PROVIDING  */

export async function getAssets() {
    return (await http.get("/edc-provider/assets")).json()
}

export async function createAsset(name: string, address: string) {
    return (await http.post("/edc-provider/assets/create", {
        name,
        baseUrl: address,
    })).json()
}

export async function createPolicy(id: string, policy: OdrlConstraint | null) {
    if(policy === null) {
        const body = {id}
        return (await http.post("/edc-provider/policies/create", body)).json()
    }
    
    const body: {id: string, policy: Omit<OdrlHasPolicy, "@id">} = {
        id: id,
        policy: {
            "@type": "Set",
            "@context": "http://www.w3.org/ns/odrl.jsonld",
            // "@id": id,
            permission: [{
                action: "use",
                constraint: policy
            }]
        }
    }

    return (await http.post("/edc-provider/policies/create", body)).json()
}


export async function createContract(body: {
    "@id": string,
    accessPolicyId: string,
    contractPolicyId: string,
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    assetsSelector: any[]
}) {

    return (await http.post("/edc-provider/contracts/create/v2", body)).json()

}





/*  CONSUMING  */

export async function getConnectors(): Promise<EdcMetadata[]> {
    return (await http.get("/edc-consumer/connectors")).json();
}

export async function getCatalog(targetConnectorAddress: string): Promise<EdcCatalog> {
    return (await http.post("/edc-consumer/catalog/get", {targetConnector: targetConnectorAddress})).json()
}

export async function negotiateContract(policy: OdrlHasPolicy, targetConnectorAddress: string, participantId: string, assetId: string) {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const body: any = {
        targetConnector: targetConnectorAddress,
        id: policy["@id"],
        assigner: participantId,
        targetAsset: assetId,
        permission: undefined //Array.isArray(policy.permission)[jsonLd.fixPolicyRule(policy.permission)],
    }

    const perm = jsonLd.removeNamespaceExtended(policy).permission;
    body.permission = perm;

    // if(perm !== undefined) {
    //     if(Array.isArray(perm)) {
    //         body.permission = perm.map(e => jsonLd.fixPolicyRule(e))
    //     } else {
    //         body.permission = jsonLd.fixPolicyRule(perm)
    //     }
    // }

    console.log(body)
    return (await http.post("/edc-consumer/contract/negotiate/v2", body)).json()
}

export async function pollNegotiationState(negotiationId: string, stateCallback?: (state: string) => void) {
    return http.poll(
        `/edc-consumer/contract/status/${negotiationId}`, 
        (data: EdcNegotiation) => data.state === "FINALIZED",
        {callback: (json) => {
            if(stateCallback) stateCallback(json.state)
        }, rate: 100, maxTries: 100}
    )
}

export async function getAgreement(agreementId: string): Promise<EdcAgreeement> {
    return (await http.get(`/edc-consumer/contract/agreement/${agreementId}`)).json()
} 


export async function beginTransfer(body: {
    connectorId: string,
    counterPartyAddress: string,
    contractId: string
}) {
    return (await http.post("/edc-consumer/transfer/begin", body)).json()
}

export async function pollTransferState(transferID: string, stateCallback?: (json: EdcTransfer) => void) {
    return http.poll(
        `/edc-consumer/transfer/status/${transferID}`, 
        (data: EdcTransfer) => data.state === "STARTED",
        {callback: (json) => {
            if(stateCallback) stateCallback(json)
        }, rate: 100, maxTries: 100}
    )
}


export async function retrieveData(transferId: string) {
    return (await http.get(`/edc-consumer/transfer/retrieve/${transferId}`)).json()
}