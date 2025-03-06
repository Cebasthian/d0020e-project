/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { OdrlPermission } from "../types/odrl"
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

function removeNamespaceShallow<T>(json: T): T {
    if(Array.isArray(json)) {
        return json.map(e => removeNamespaceShallow(e as T))
    }

    const ret: any = {}
    const keys = Object.keys(json)
    for(let i = 0; i < keys.length; i++) {
        const key = keys[i]
        const k = splitNamespace(key)
        // let body = json[key]
        // if(body !== null && typeof body === "object") {
        //     body = removeNamespace(body)
        // } else if(typeof body === "string") {
        //     body = splitNamespace(body)
        // }
        ret[k] = json[key];
    }
    return ret
}

function splitNamespace(key: string) {
    if(isUrl(key)) return key;

    const arr = key.split(":")
    if(arr.length === 2) return arr[1]
    return key;
}

/**
 * returns shallow copy
 * 
 */
function fixPolicyRule(rule: OdrlPermission) {
    const ret = {...rule}
    if(Array.isArray(ret.constraint)) {
        ret.constraint = ret.constraint.map(e => fixConstraint(e))
    } else {
        ret.constraint = fixConstraint(ret.constraint)
    }
    return ret;
}

function fixConstraint<T>(constraint: T): T {
    const ret: T = {}
    const keys = Object.keys(constraint);

    for(let i = 0; i < keys.length; i++) {
        const key = keys[i];

        const value = constraint[key];
        let body;
        if(typeof value === "string") {
            body = value;
        }
        if(value !== null && typeof value === "object") {
            if(value["@id"] && typeof value["@id"] === "string") {
                body = value["@id"]
            }
        }

        if(key === "operator") {
            body = "odrl:"+body;
        }

        ret[key] = body;
    }
    return ret;
}

function removeNamespaceExtended<T>(json: T): T {
    if(Array.isArray(json)) {
        return json.map(e => removeNamespaceExtended(e as T))
    }

    const ret: any = {}
    const keys = Object.keys(json)

    if(keys.length === 1 && keys[0] === "@id") {
        return splitNamespace(json[keys[0]])
    }

    for(let i = 0; i < keys.length; i++) {
        const key = keys[i]
        const k = splitNamespace(key)
        let body = json[key]
        if(body !== null && typeof body === "object") {
            body = removeNamespaceExtended(body)
        } else if(typeof body === "string") {
            body = splitNamespace(body)
        }
        ret[k] = body;
    }
    return ret
}

export const jsonLd = {
    removeNamespace,
    removeNamespaceShallow,
    splitNamespace,
    fixConstraint,
    fixPolicyRule,
    removeNamespaceExtended
}