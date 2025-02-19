/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { isUrl } from "./regex"

/**
 * Removes all json_ld namespace formatting. E.g. "dcat:dataset" => "dataset".
 * @param json A referential copy.
 */
function removeNamespace<T>(json: T): T {
    if(Array.isArray(json)) {
        return json.map(e => removeNamespace(e as T))
    }

    const ret: any = {}
    const keys = Object.keys(json)
    for(let i = 0; i < keys.length; i++) {
        const key = keys[i]
        const k = splitNamespace(key)
        let body = json[key]
        if(body !== null && typeof body === "object") {
            body = removeNamespace(body)
        } else if(typeof body === "string") {
            body = splitNamespace(body)
        }
        ret[k] = body;
    }
    return ret
}

function splitNamespace(key: string) {
    if(isUrl(key)) return key;

    const arr = key.split(":")
    if(arr.length === 2) return arr[1]
    return key;
}

export const jsonLd = {
    removeNamespace
}