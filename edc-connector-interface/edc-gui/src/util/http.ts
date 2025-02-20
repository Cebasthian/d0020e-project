async function get(url: string, options?: RequestInit) {
    return fetch(url, {
        method: "get",
        headers: {
            "Content-Type": "application/json"
        },
        ...options,
    })
}

function post(url: string, body: unknown) {
    return fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: body ? JSON.stringify(body) : undefined,
    })
}

async function put(url: string, body: unknown) {
    return fetch(url, {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: body ? JSON.stringify(body) : undefined,
    })
}

async function del(url: string, body?: unknown) {
    return fetch(url, {
        method: "delete",
        headers: {
            "Content-Type": "application/json"
        },
        body: body ? JSON.stringify(body) : undefined,
    })
}

/**
 * Polls an endpoint until a sufficient answer is returned. request -> callback -> validator -> delay(rate) -> restart.
 * @param url
 * @param validator A function that when called, responds whether the polling should continue. 
 * @param options Rate is the delay between requests, Callback runs with the response data before the validator.
 * @returns The final response after the validation functions returns true.
 */
async function poll<T>(url: string, validator: (json: T) => boolean, options?: {rate?: number, callback?: (json: T) => void, maxTries?: number}) {
    let cont = true;
    let json;
    let tries = 0;
    const maxTries = options?.maxTries || 1000;
    while(cont) {
        tries++;
        if(tries > maxTries) {
            cont = false;
            throw new Error("Polling timeout")
        }

        json = await (await get(url, {cache: "no-store"})).json()
        if(options?.callback) options.callback(json)
        cont = !validator(json)
        if(cont) {
            await delay(options?.rate || 100)
        }
    }
    return json as T;
}

export function delay(ms: number) {
    return new Promise<void>((resolve) => {
        setTimeout(() => {resolve()}, ms)
    })
}

export const http = {
    get,
    post,
    put,
    delete: del,
    poll
}