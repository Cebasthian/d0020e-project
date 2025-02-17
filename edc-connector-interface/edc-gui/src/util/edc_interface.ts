import { EdcMetadata } from "../types/edc";
import { http } from "./http";

export async function getConnectors(): Promise<EdcMetadata[]> {
    return (await http.get("/edc-consumer/connectors")).json();
}

export async function getAssets() {
    return (await http.get("/edc-provider/assets")).json()
}

export async function createAsset(name: string, address: string) {
    return (await http.post("/edc-provider/assets/create", {
        name,
        baseUrl: address,
    })).json()
}