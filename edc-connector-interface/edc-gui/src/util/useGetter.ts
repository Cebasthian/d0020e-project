import useSWR from "swr";
import { SpringErrorMessage } from "../types/spring";
import { jsonLd } from "./json_ld";

export const fetcher = async (url: string) => {
    const res = await fetch(url)

    const json = await res.json();

    if(!res.ok) {
        const error = new HttpError(json, res.status)
        throw error;
    }

    return json;
}

class HttpError extends Error {
    info: SpringErrorMessage
    status: number
    
    constructor(info: SpringErrorMessage, status: number) {
        super()
        this.info = info;
        this.status = status;
    }

}

export function useGetter<T>(url: string, fallbackData: T) {
    const obj = useSWR<T>(url, fetcher, {fallbackData: fallbackData})
    const {data, mutate} = obj

    const refresh = () => mutate();

    return {
        data: jsonLd.removeNamespace(data) || fallbackData,
        refresh
    }
}