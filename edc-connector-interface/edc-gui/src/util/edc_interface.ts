import { EdcAgreeement, EdcCatalog, EdcMetadata, EdcNegotiation } from "../types/edc";
import { OdrlHasPolicy } from "../types/odrl";
import { http } from "./http";

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







/*  CONSUMING  */

export async function getConnectors(): Promise<EdcMetadata[]> {
    return (await http.get("/edc-consumer/connectors")).json();
}

export async function getCatalog(targetConnectorAddress: string): Promise<EdcCatalog> {
    return (await http.post("/edc-consumer/catalog/get", {targetConnector: targetConnectorAddress})).json()
}

export async function negotiateContract(policy: OdrlHasPolicy, targetConnectorAddress: string, participantId: string, assetId: string) {
    return (await http.post("/edc-consumer/contract/negotiate", {
        targetConnector: targetConnectorAddress,
        policy: {
            id: policy["@id"],
            assigner: participantId,
            targetAsset: assetId
        }
    })).json()
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